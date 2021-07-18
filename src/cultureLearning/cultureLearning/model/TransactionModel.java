package cultureLearning.cultureLearning.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;

import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.PaymentChannel;
import cultureLearning.cultureLearning.domain.Subscription;
import cultureLearning.cultureLearning.domain.SubscriptionType;
import cultureLearning.cultureLearning.domain.Transaction;
import cultureLearning.cultureLearning.domain.TransactionStage;
import cultureLearning.cultureLearning.domain.User;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;
import cultureLearning.cultureLearning.services.impl.PaymentHelper;
import cultureLearning.cultureLearning.services.impl.PdfBuilder;
import cultureLearning.cultureLearning.services.impl.PaymentHelper.PaymentData;
import cultureLearning.cultureLearning.services.impl.SubscriptionService;
import cultureLearning.cultureLearning.services.impl.SubscriptionTypeServiceImplementation;
import cultureLearning.cultureLearning.services.impl.TransactionServiceImplementation;
import cultureLearning.cultureLearning.services.impl.UserServiceImplementation;

@VariableResolver(DelegatingVariableResolver.class)
public class TransactionModel {
	@Autowired
	private Subscription subscription;
	@Autowired
	private User user;
	@Autowired
	private Course course;
	@Autowired
	private Transaction transaction;
	@Autowired
	private SubscriptionType subtype;

	private ListModelList<Subscription> listOfAllSubscribers;
	private ListModelList<Subscription> getSubscriber;
	@WireVariable("SubscriptionType")
	private SubscriptionTypeServiceImplementation subService;
	@WireVariable("sub")
	private SubscriptionService subscriptionService;
	@WireVariable("transaction")
	private TransactionServiceImplementation transactinService;
	@WireVariable("Course")
	private CourseServiceImplementation courseService;
	@WireVariable("Service")
	private UserServiceImplementation userService;
	private ListModelList<SubscriptionType>listOfSubType;
	private ListModelList<Transaction>listOfAllTransaction;
	
	@Init
	public void init() {
		transaction=new  Transaction();
		listOfAllTransaction=new ListModelList<Transaction>(transactinService.listofTransaction());
		subtype=new SubscriptionType();
		String subId = Executions.getCurrent().getParameter("subId");
		if(subId!=null) {
			subtype = subService.findById(subId);
			if(subtype==null) {
				subtype = new SubscriptionType();
			}
		}
		listOfSubType=new ListModelList<SubscriptionType>(subService.listofSubscriptionType());
		String transactionId = Executions.getCurrent().getParameter("transaction_id");
		String status = Executions.getCurrent().getParameter("status");
		String txRef = Executions.getCurrent().getParameter("tx_ref");
		System.out.println("T: "+transactionId+" S: "+status+" TX: "+txRef);
		if(status!=null) {
			if(status.equals("successful")&&transactionId!=null&txRef!=null) {
				// Checking ko yishyuye nyabyo
				PaymentData paymentData = null;
				try {
					paymentData = PaymentHelper.getPaymentData(transactionId);
				}catch(Exception e) {
					e.printStackTrace();
					paymentFailed(e.getMessage());
					return;
				}
				// No gu chekcing transaction
				Transaction transaction = transactinService.getTransactionById(Integer.parseInt(txRef.substring(3)));
				if(transaction==null) {
					paymentFailed("Transaction is null ID: "+Integer.parseInt(txRef.substring(3)));
					return;
				}
				User user = transaction.getUser();
				if(user==null) {
					paymentFailed("User is null");
					return;
				}
				if(transaction.getStage()!=null&&transaction.getStage().equals(TransactionStage.PAID)) {
					paymentFailed("Transaction already paid");
					return;
				}
				Subscription subscription = new Subscription();
				subscription.setStartDate(new Date());
				subscription.setEndDate(java.sql.Date.valueOf(LocalDate.now().plusMonths(6)));
				subscription.setUser(user);
				subscription.setSubscription(transaction.getSubscriptiontype());
				subscriptionService.saveSubscription(subscription);
				transaction.setSubscription(subscription);
				transaction.setStage(TransactionStage.PAID);
				transaction.setChannel(PaymentChannel.FLUTTERWAVE);
				transaction.setExternalTransactionId(paymentData.getTransactionId());
				transaction.setPayload(paymentData.getPayload());
				transactinService.updateTransaction(transaction);
				User loggedUser = (User)Sessions.getCurrent().getAttribute("s_user");
				if(loggedUser!=null) {
					loggedUser = userService.getByEmail(loggedUser.getEmail());
				Sessions.getCurrent().setAttribute("s_user", loggedUser);
				}
				
			} else {
				paymentFailed("From flutterwave");
			}
		}
	}
	
	private void paymentFailed(String  comment) {
		System.out.println(comment);
		Executions.getCurrent().sendRedirect("PaymentFail.zul");
	}
	
	@Command("pay")
	public void savePayment() {
		User user = (User)Sessions.getCurrent().getAttribute("s_user");
		
		if(subtype==null||subtype.getSubscriptionTypeId()==null) {
			System.out.println("Subtype is null");
			return;
		}
		SubscriptionType subscriptionType = subService.findById(subtype.getSubscriptionTypeId());
		if(subscriptionType==null||user==null) {
			System.out.println("User or Subscription is null User:"+user+" Subscrpition:"+subscription);
			return;
		}
		Double amount = subtype.getPrice();
		Transaction transaction = new Transaction();
		transaction.setPrice(amount);
		transaction.setUser(user);
		transaction.setSubscriptiontype(subtype);
		transactinService.saveTransaction(transaction);
		try{
			//if(transaction!=null) {
			System.out.println(transaction.getTransactionId());
			String url = PaymentHelper.payWithMoMo("TX_"+transaction.getTransactionId()+"", null, amount, user.getEmail(), user.getFname(), "http://localhost:8080/Inter/Succespayment.zul");
			Executions.sendRedirect(url);
			//Executions.sendRedirect("/Succespayment.zul");
			Clients.showNotification("You have succesful paid");
			//}
			
		}catch(Exception e) {
			e.printStackTrace();
			//Clients.showNotification("Internal error", "error", null, "code", 0, true);
			Clients.showNotification("You have fail to pay");
		}
	}
	
	
	@Command("transactionReport")
	public void printReport() {
		try {
			List<Transaction> c = transactinService.listofTransaction();
			PdfBuilder pdf = new PdfBuilder();
			pdf.addTitle("Lisiti y'uko kwishyura byagenze");
			pdf.init(6);
			pdf.addTableHeader(new String[]{"transaction Id", "Stage", "Amazina y'Uwishyuye","Ikiciro y'ishyuriye","Umuyoboro wakoreshejwe","external transactionId" });
			for(Transaction trans : c) {
				pdf.addTableCell(trans.getTransactionId()+"");
				pdf.addTableCell(trans.getStage()+"");
				pdf.addTableCell(trans.getUser().getFname()+" "+trans.getUser().getLname());
				pdf.addTableCell(trans.getSubscriptiontype()+"");
				pdf.addTableCell(trans.getChannel()+"");
				pdf.addTableCell(trans.getExternalTransactionId()+"");
				
			}
			pdf.run();
			Clients.showNotification("byagenze neza");
		}catch(Exception e) {
			e.printStackTrace();
			Clients.showNotification(e.getMessage());
		}
	}
	 	
	
	
	
	
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public ListModelList<Subscription> getListOfAllSubscribers() {
		return listOfAllSubscribers;
	}
	public void setListOfAllSubscribers(ListModelList<Subscription> listOfAllSubscribers) {
		this.listOfAllSubscribers = listOfAllSubscribers;
	}
	public ListModelList<Subscription> getGetSubscriber() {
		return getSubscriber;
	}
	public void setGetSubscriber(ListModelList<Subscription> getSubscriber) {
		this.getSubscriber = getSubscriber;
	}
	public SubscriptionTypeServiceImplementation getSubService() {
		return subService;
	}
	public void setSubService(SubscriptionTypeServiceImplementation subService) {
		this.subService = subService;
	}
	public TransactionServiceImplementation getTransactinService() {
		return transactinService;
	}
	public void setTransactinService(TransactionServiceImplementation transactinService) {
		this.transactinService = transactinService;
	}
	public CourseServiceImplementation getCourseService() {
		return courseService;
	}
	public void setCourseService(CourseServiceImplementation courseService) {
		this.courseService = courseService;
	}
	public UserServiceImplementation getUserService() {
		return userService;
	}
	public void setUserService(UserServiceImplementation userService) {
		this.userService = userService;
	}

	public SubscriptionType getSubtype() {
		return subtype;
	}

	public void setSubtype(SubscriptionType subtype) {
		this.subtype = subtype;
	}

	public SubscriptionService getSubscriptionService() {
		return subscriptionService;
	}

	public void setSubscriptionService(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	public ListModelList<SubscriptionType> getListOfSubType() {
		return listOfSubType;
	}

	public void setListOfSubType(ListModelList<SubscriptionType> listOfSubType) {
		this.listOfSubType = listOfSubType;
	}

	public ListModelList<Transaction> getListOfAllTransaction() {
		return listOfAllTransaction;
	}

	public void setListOfAllTransaction(ListModelList<Transaction> listOfAllTransaction) {
		this.listOfAllTransaction = listOfAllTransaction;
	}
	
	
	
}
