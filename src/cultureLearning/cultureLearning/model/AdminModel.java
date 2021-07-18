package cultureLearning.cultureLearning.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import cultureLearning.cultureLearning.domain.Address;
import cultureLearning.cultureLearning.domain.Admin;
import cultureLearning.cultureLearning.encryptionClass.Bcrypt;

import cultureLearning.cultureLearning.services.impl.AddressServiceImplementation;
import cultureLearning.cultureLearning.services.impl.AdminServiceImplementation;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;
import cultureLearning.cultureLearning.services.impl.NotificationService;
import cultureLearning.cultureLearning.services.impl.PasswordProtection;
import cultureLearning.cultureLearning.services.impl.PdfBuilder;

import cultureLearning.cultureLearning.services.impl.TranslationService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AdminModel {

	@WireVariable("admin")
	private AdminServiceImplementation service;
	@WireVariable("address")
	private AddressServiceImplementation address;
	@Autowired
	private Admin admin;
	@Autowired
	private  Address add;
	private String translationText="Igisubizo";
	private String translationResult="Igisubizo";
	@WireVariable("TranslationService")
	private TranslationService translator;
	@WireVariable("NotificationService")
	private NotificationService notifier;
	
	
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
	@Autowired
	private ListModelList<Admin>listofAdmins;
	private ListModelList<Address>listofCountries;
	private ListModelList<Admin>listofLearner;
	
	
	
	private List<Admin>numberOfAdmins=new ArrayList<Admin>();

	
	
	
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
	admin=new Admin();
	listofAdmins=new ListModelList<Admin>(service.findAllAdmin());
	add=new Address();
	
	listofCountries=new ListModelList<Address>(address.findAllAddress());
	Admin u = (Admin)Sessions.getCurrent().getAttribute("s_Admin");
	if(u!=null) {
		admin.setEmail(u.getEmail());
	}
	
}



@Command("adminAccount")
@NotifyChange("listofAdmins")
public void addAdmin() {
	try {
		
		System.out.println(add.getCode());
		 Address ad=address.findById(add.getCode());
		 
		admin.setAddress(ad);
		String passwordHashed=PasswordProtection.hashPassword(admin.getPassword());
		admin.setPassword(passwordHashed);
		System.out.println(admin);
		service.registerAdmin(admin);
		
		Clients.showNotification("Gufungura konti byagenze neza!");
	//Executions.sendRedirect("/Login.zul");
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		Clients.showNotification(e.getMessage());
		}
	}
	



@Command("num")
@NotifyChange("listofAdmins")
public int display() {
	int size=listofAdmins.size();
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


public ListModelList<Admin> getListofAdmins() {
	return listofAdmins;
}
public void setListofAdmins(ListModelList<Admin> listofAdmins) {
	this.listofAdmins = listofAdmins;
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
@NotifyChange({"translationResult"})
public void translate() {
	System.out.println("Translating");
	if(translationText==null||translationText=="") return;
	System.out.println("Translating");
	try {
		this.translationResult = translator.translateKinyarwandaToEnglish(translationText);
	}catch(Exception e) {
		e.printStackTrace();
		this.translationResult = "Ntibishoboye kuboneka";
	}
}

@Command("printButton")
public void printReport() {
	try {
		List<Admin> l = service.findAllAdmin();
		PdfBuilder pdf = new PdfBuilder();
		pdf.addTitle("Lisiti y'abiga bose");
		pdf.init(4);
		pdf.addTableHeader(new String[]{"amazina", "imeri", "irangamuntu"," Address"});
		for(Admin Admin : l) {
			pdf.addTableCell(Admin.getFname()+" "+Admin.getLname());
			//pdf.addTableCell(Admin.getLname());
			pdf.addTableCell(Admin.getEmail());
			pdf.addTableCell(Admin.getNid());
			 pdf.addTableCell(Admin.getAddress().getName());
			 //pdf.addTableCell(Admin.getType());
		}
		pdf.run();
		Clients.showNotification("byagenze neza");
	}catch(Exception e) {
		Clients.showNotification(e.getMessage());
	}
}
@Command("printButtonAll")
public void printReportAllAdmins() {
	try {
		List<Admin> l = service.findAllAdmin();
		PdfBuilder pdf = new PdfBuilder();
		pdf.addTitle("Lisiti y'abakoresha urubuga bose");
		pdf.init(6);
		pdf.addTableHeader(new String[]{"izina rya mbere", "izina rya nyuma", "imeri", "irangamuntu"," Address","ubwoko bwa abakoresha"});
		for(Admin Admin : l) {
			pdf.addTableCell(Admin.getFname());
			pdf.addTableCell(Admin.getLname());
			pdf.addTableCell(Admin.getEmail());
			pdf.addTableCell(Admin.getNid());
			 pdf.addTableCell(Admin.getAddress().getName()); 
			 //pdf.addTableCell(Admin.getType());
		}
		pdf.run();
		Clients.showNotification("byagenze neza");
	}catch(Exception e) {
		Clients.showNotification(e.getMessage());
	}
}

public NotificationService getNotifier() {
	return notifier;
}

public void setNotifier(NotificationService notifier) {
	this.notifier = notifier;
}
public ListModelList<Admin> getListofLearner() {
	return listofLearner;
}
public void setListofLearner(ListModelList<Admin> listofLearner) {
	this.listofLearner = listofLearner;
}


public AdminServiceImplementation getService() {
	return service;
}
public void setService(AdminServiceImplementation service) {
	this.service = service;
}
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
}
public List<Admin> getNumberOfAdmins() {
	return numberOfAdmins;
}
public void setNumberOfAdmins(List<Admin> numberOfAdmins) {
	this.numberOfAdmins = numberOfAdmins;
}


}
