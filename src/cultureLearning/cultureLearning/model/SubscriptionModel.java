package cultureLearning.cultureLearning.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.SessionScope;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;

import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.Subscription;
import cultureLearning.cultureLearning.domain.SubscriptionType;
import cultureLearning.cultureLearning.domain.Transaction;
import cultureLearning.cultureLearning.domain.User;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;
import cultureLearning.cultureLearning.services.impl.SubscriptionService;
import cultureLearning.cultureLearning.services.impl.SubscriptionTypeServiceImplementation;
import cultureLearning.cultureLearning.services.impl.TransactionServiceImplementation;
import cultureLearning.cultureLearning.services.impl.UserServiceImplementation;

@VariableResolver(DelegatingVariableResolver.class)
public class SubscriptionModel {

	@Autowired
	private SubscriptionType sub;
		@Autowired
	private User user;
		@Autowired
	private Course course;
		@Autowired
	private Transaction transaction;
		@Autowired
		private Subscription subscription;
		
	private ListModelList<SubscriptionType>listOfAllSubscribers;
	private ListModelList<SubscriptionType>getSubscriber; 
	@WireVariable("SubscriptionType")
	private SubscriptionTypeServiceImplementation subService;
	@WireVariable("transaction")
	private TransactionServiceImplementation transactinService;
	@WireVariable("Course")
	private CourseServiceImplementation courseService;
	@WireVariable("Service")
	private UserServiceImplementation userService;
	@WireVariable("sub")
	private SubscriptionService subscriptionService;
	private ListModelList<Subscription>listOfSubcription;
	

	@Init
	public void init() {
		sub=new SubscriptionType();
		course=new Course();
		subscription=new Subscription();
		listOfAllSubscribers=new ListModelList<SubscriptionType>(subService.listofSubscriptionType());
		listOfSubcription=new ListModelList<Subscription>(subscriptionService.listofSubscription());
	}

	@Command("save")
	public void saveSubscription() {
		try {
			SubscriptionType type=subService.findById(sub.getSubscriptionTypeId());
			User sessionUser=userService.getByEmail(user.getEmail());
			Sessions ss=(Sessions) Sessions.getCurrent().setAttribute("s_user", sessionUser);
			
			subscription.setSubscription(type);
			subscriptionService.saveSubscription(subscription);;
			Clients.showNotification("Ikiciro cyabitswe neza!");
			
		} catch (Exception e) {
			// TODO: handle exception
			Clients.showNotification("ntishoboye kuyibika!");
			e.getMessage();
			e.printStackTrace();
		}
	}
	@Command("delete")
	public void deleteSubscription(@BindingParam("de")Subscription sub) {
		try {
			subscriptionService.deleteSubscription(sub);
			Clients.showNotification("byasibitse neza!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@Command("update")
	public void updateSubscription(@BindingParam("up")Subscription sub) {
		try {
			subscriptionService.updateSubscription(sub);
			Clients.showNotification("byasibitse neza!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public SubscriptionType getSub() {
		return sub;
	}

	public void setSub(SubscriptionType sub) {
		this.sub = sub;
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

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public ListModelList<SubscriptionType> getListOfAllSubscribers() {
		return listOfAllSubscribers;
	}

	public void setListOfAllSubscribers(ListModelList<SubscriptionType> listOfAllSubscribers) {
		this.listOfAllSubscribers = listOfAllSubscribers;
	}

	public ListModelList<SubscriptionType> getGetSubscriber() {
		return getSubscriber;
	}

	public void setGetSubscriber(ListModelList<SubscriptionType> getSubscriber) {
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

	public SubscriptionService getSubscriptionService() {
		return subscriptionService;
	}

	public void setSubscriptionService(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	public ListModelList<Subscription> getListOfSubcription() {
		return listOfSubcription;
	}

	public void setListOfSubcription(ListModelList<Subscription> listOfSubcription) {
		this.listOfSubcription = listOfSubcription;
	}


}
