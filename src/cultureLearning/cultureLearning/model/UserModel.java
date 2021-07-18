package cultureLearning.cultureLearning.model;

import cultureLearning.cultureLearning.domain.Address;
import cultureLearning.cultureLearning.domain.Admin;
import cultureLearning.cultureLearning.domain.Student;
import cultureLearning.cultureLearning.domain.Teacher;
import cultureLearning.cultureLearning.domain.User;
import cultureLearning.cultureLearning.encryptionClass.Bcrypt;
import cultureLearning.cultureLearning.exception.InvalidMailException;
import cultureLearning.cultureLearning.services.impl.AddressServiceImplementation;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;
import cultureLearning.cultureLearning.services.impl.NotificationService;
import cultureLearning.cultureLearning.services.impl.PasswordProtection;
import cultureLearning.cultureLearning.services.impl.PdfBuilder;

import cultureLearning.cultureLearning.services.impl.TranslationService;
import cultureLearning.cultureLearning.services.impl.UserServiceImplementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserModel {
	@WireVariable("Service")
	private UserServiceImplementation service;
	@WireVariable("address")
	private AddressServiceImplementation address;
	@Autowired
	private User user;
	@Autowired
	private Address add;
	private String translationText = "Igisubizo";
	private String translationResult = "Igisubizo";
	@WireVariable("TranslationService")
	private TranslationService translator;
	@WireVariable("NotificationService")
	private NotificationService notifier;
	@Temporal(TemporalType.DATE)
	private Date date = new Date();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Autowired
	private Bcrypt bcrypt;
	private String code;
	private String password;
	private String npassword;
	private String oldPassword;
	private String cpassword;
	private String email;
	private String salt;
	private ListModelList<User> listofUsers;
	private ListModelList<Address> listofCountries;
	private ListModelList<User> listofLearner;
	private ListModelList<User> listofTeacher;

//	private List<User>numberOfUsers=new ArrayList<User>();

	@WireVariable("Course")
	private CourseServiceImplementation courseService;

	public CourseServiceImplementation getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseServiceImplementation courseService) {
		this.courseService = courseService;
	}

	@Init()
	public void initialization() {
		user = new User();
		listofUsers = new ListModelList<User>(service.findAllUser());
		add = new Address();
		listofLearner = new ListModelList<User>(service.listOfLearner());
		listofTeacher = new ListModelList<User>(service.listOfTeacher());
		listofCountries = new ListModelList<Address>(address.findAllAddress());
		User u = (User) Sessions.getCurrent().getAttribute("s_user");
		if (u != null) {
			user.setEmail(u.getEmail());
		}

	}

	@Command("save")
	@NotifyChange("listofUsers")
	public void addUser() {
		try {
			System.out.println(add.getCode());
			Address ad = address.findById(add.getCode());

			user.setAddress(ad);
			String passwordHashed = PasswordProtection.hashPassword(user.getPassword());
			user.setPassword(passwordHashed);
			service.registerUser(user);

			Clients.showNotification("Gufungura konti byagenze neza!!");
			Executions.sendRedirect("/Login.zul");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Clients.showNotification(e.getMessage());
		}
	}

	@Command("login")
	@NotifyChange("listofUsers")
	public void loginfunction() {
		try {

			if (user.getEmail().length() > 0 && user.getPassword().length() > 0) {

				User sessionUser = service.userEx(user.getEmail(), user.getPassword());
				if (null != sessionUser) {
					Sessions.getCurrent().setAttribute("s_user", sessionUser);
					if (sessionUser instanceof Admin) {
						Clients.showNotification("Murakaza neza ");
						Executions.sendRedirect("/Admin.zul");
					} else if (sessionUser instanceof Student) {
						Clients.showNotification("Murakaza neza ");
						Executions.sendRedirect("/Learner.zul");
					}

					else if (sessionUser instanceof Teacher) {
						Clients.showNotification("Murakaza neza ");
						Executions.sendRedirect("/TeacherDashboard.zul");
					} else {
						Clients.showNotification("Mwihangane ntabwo tubashije kumenya uwo uriwe!");
						Executions.sendRedirect("User.zul");
					}

				} else {
					Clients.showNotification("imeri cyangwa ijambobanga byanditse nabi!", "error", null, code, 0, true);
				}
			} else {
				Clients.showNotification("Wanditse nabi imeri yawe", "error", null, code, 0, true);

			}
		} catch (InvalidMailException e) {
			Clients.showNotification(e.getMessage(), "error", null, code, 0, true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Clients.showNotification("Internal Error", "error", null, code, 0, true);
		}
	}

	@Command("changePassword")
	@NotifyChange("listofUsers")
	public void changepassword() {
		try {
			service.updatePassword(user.getEmail(), oldPassword, npassword, cpassword);
			// service.forgetPassword(user, npassword, email);
			Clients.showNotification("  byagenze neza!!!");
		} catch (Exception e) {
			// TODO: handle exception
			Clients.showNotification("guhindura password ntibyagenze neza!mmwongere mugerageza" + e.getMessage());
			e.printStackTrace();
		}

	}

	@Command("forgetPassword")
	@NotifyChange("listofUsers")
	public void forgotpassword() {
		try {
			User u = service.getByEmail(getEmail());
			if (u == null) {
				Clients.showNotification("Iyo imeri ntibaho!mushyiremo iyindi ");
				return;
			}
			String password = getAlphaNumericString(6);
			String passwordHashed = PasswordProtection.hashPassword(password);

			u.setPassword(passwordHashed);
			service.update(u);
			notifier.sendEmail(u.getEmail(), "Reset Password", "Ijambobanga ryawe niri: " + password);
			Clients.showNotification("byagenze neza!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Clients.showNotification("ntibyagenze neza mwongere mugerageze");
		}
	}

	String getAlphaNumericString(int n) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	@Command
	public String resetLearnerPassword(String email) {
		try {
			service.getByEmail(email);
			String randomPassword = RandomStringUtils.randomAlphanumeric(10);
			user.setPassword(randomPassword);
			service.update(user);
			Clients.showNotification("ijambo banga ryawe rwahinduwe !reba imeri yawe. ");
			return randomPassword;

		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			return "guhindura ijambobanga ntibyakunze mwongere mugerageza";

		}

	}

	@Command("search")
	@NotifyChange("listofUsers")
	public void search() {
		try {
			service.search(user);
			/* Clients.showNotification("found "); */
			System.out.println(email);
		} catch (Exception e) {
			// TODO: handle exception
			Clients.showNotification(e.getMessage());
			e.printStackTrace();
		}
	}

	@Command("logout")
	public void doLogout() {
		// service.logout();
		System.out.println("Logout");
		Executions.sendRedirect("/index.zul");
	}

	@Command("update")
	@NotifyChange("listofUsers")
	public void updateUser(@BindingParam("up") User user) {
		try {
			service.update(user);
			Clients.showNotification("byahinduwe neza!");
		} catch (Exception e) {
			// TODO: handle exception
			Clients.showNotification(" ivugururwa ntibyagenze neza!mugerageze" + e.getMessage());
			e.printStackTrace();
		}

	}

	@Command("learner")
	public void displayLearner() {
		service.listOfLearner();
	}

	@Command("delete")
	@NotifyChange("listofUsers")
	public void deleteUser(@BindingParam("de") User user) {
		try {

			service.deleteUser(user);
			Clients.showNotification("byagenze neza!!!!");
		} catch (Exception e) {
			// TODO: handle exception
			Clients.showNotification("Gusiba ntibyagenze neza mwongere mugerageze" + e.getMessage());
		}

	}

	@Command("num")
	@NotifyChange("listofUsers")
	public int display() {
		int size = listofUsers.size();
		System.out.println(size);
		return size;

	}

	public AddressServiceImplementation getAddress() {
		return address;
	}

	public void setAddress(AddressServiceImplementation address) {
		this.address = address;
	}

	public Address getAdd() {
		return add;
	}

	public void setAdd(Address add) {
		this.add = add;
	}

	public ListModelList<Address> getListofCuntries() {
		return listofCountries;
	}

	public void setListofCuntries(ListModelList<Address> listofCuntries) {
		this.listofCountries = listofCuntries;
	}

	public UserServiceImplementation getService() {
		return service;
	}

	public void setService(UserServiceImplementation service) {
		this.service = service;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ListModelList<User> getListofUsers() {
		return listofUsers;
	}

	public void setListofUsers(ListModelList<User> listofUsers) {
		this.listofUsers = listofUsers;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ListModelList<Address> getListofCountries() {
		return listofCountries;
	}

	public void setListofCountries(ListModelList<Address> listofCountries) {
		this.listofCountries = listofCountries;
	}

	public Bcrypt getBcrypt() {
		return bcrypt;
	}

	public TranslationService getTranslator() {
		return translator;
	}

	public void setTranslator(TranslationService translator) {
		this.translator = translator;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getTranslationText() {
		return translationText;
	}

	public void setTranslationResult(String translationResult) {
		this.translationResult = translationResult;
	}

	public void setBcrypt(Bcrypt bcrypt) {
		this.bcrypt = bcrypt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getNpassword() {
		return npassword;
	}

	public void setNpassword(String npassword) {
		this.npassword = npassword;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setTranslationText(String text) {
		this.translationText = text;
	}

	public String getTranslationResult() {
		return translationResult;
	}

	@Command("translate")
	@NotifyChange({ "translationResult" })
	public void translate() {
		System.out.println("Translating");
		if (translationText == null || translationText == "")
			return;
		System.out.println("Translating");
		try {
			this.translationResult = translator.translateKinyarwandaToEnglish(translationText);
		} catch (Exception e) {
			e.printStackTrace();
			this.translationResult = "Ntibishoboye kuboneka";
		}
	}

	@Command("printButton")
	public void printReport() {
		try {
			List<User> l = service.listOfLearner();
			PdfBuilder pdf = new PdfBuilder();
			pdf.addTitle("Lisiti y'abiga bose");
			pdf.init(5);
			pdf.addTableHeader(new String[] { "amazina", "email", "telephoni", "irangamuntu", " Aho akomoka" });
			for (User user : l) {
				pdf.addTableCell(user.getFname() + " " + user.getLname());
				// pdf.addTableCell(user.getLname());
				pdf.addTableCell(user.getEmail());
				pdf.addTableCell(user.getPhone());
				pdf.addTableCell(user.getNid());
				pdf.addTableCell(user.getAddress().getName());
				// pdf.addTableCell(user.getType());
			}
			pdf.run();
			Clients.showNotification("byagenze neza");
		} catch (Exception e) {
			Clients.showNotification(e.getMessage());
		}
	}

	@Command("printReport")
	public void printReportTeacher() {
		try {
			List<User> l = service.listOfTeacher();
			PdfBuilder pdf = new PdfBuilder();
			pdf.addTitle("Lisiti y'abigisha  bose");
			pdf.init(5);
			pdf.addTableHeader(new String[] { "amazina", "imeri","telephone", "irangamuntu", " Igihugu Akomokamo" });
			for (User user : l) {
				pdf.addTableCell(user.getFname() + " " + user.getLname());
				// pdf.addTableCell(user.getLname());
				pdf.addTableCell(user.getEmail());
				pdf.addTableCell(user.getPhone());
				pdf.addTableCell(user.getNid());
				pdf.addTableCell(user.getAddress().getName());
				// pdf.addTableCell(user.getType());
			}
			pdf.run();
			Clients.showNotification("byagenze neza");
		} catch (Exception e) {
			Clients.showNotification(e.getMessage());
		}
	}

	@Command("printButtonAll")
	public void printReportAllUsers() {
		try {
			List<User> l = service.findAllUser();
			PdfBuilder pdf = new PdfBuilder();
			pdf.addTitle("Lisiti y'abakoresha urubuga bose");
			pdf.init(6);
			pdf.addTableHeader(new String[] { "izina rya mbere", "izina rya nyuma", "email", "irangamuntu", " Address",
					"ubwoko bwa abakoresha" });
			for (User user : l) {
				pdf.addTableCell(user.getFname());
				pdf.addTableCell(user.getLname());
				pdf.addTableCell(user.getEmail());
				pdf.addTableCell(user.getNid());
				pdf.addTableCell(user.getAddress().getName());
				// pdf.addTableCell(user.getType());
			}
			pdf.run();
			Clients.showNotification("byagenze neza");
		} catch (Exception e) {
			Clients.showNotification(e.getMessage());
		}
	}

	public NotificationService getNotifier() {
		return notifier;
	}

	public void setNotifier(NotificationService notifier) {
		this.notifier = notifier;
	}

	public ListModelList<User> getListofLearner() {
		return listofLearner;
	}

	public void setListofLearner(ListModelList<User> listofLearner) {
		this.listofLearner = listofLearner;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ListModelList<User> getListofTeacher() {
		return listofTeacher;
	}

	public void setListofTeacher(ListModelList<User> listofTeacher) {
		this.listofTeacher = listofTeacher;
	}

}
