package cultureLearning.cultureLearning.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import cultureLearning.cultureLearning.domain.Address;
import cultureLearning.cultureLearning.domain.Student;
import cultureLearning.cultureLearning.domain.Student;
import cultureLearning.cultureLearning.encryptionClass.Bcrypt;
import cultureLearning.cultureLearning.exception.CountryNotSelectedException;
import cultureLearning.cultureLearning.services.impl.AddressServiceImplementation;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;
import cultureLearning.cultureLearning.services.impl.NotificationService;
import cultureLearning.cultureLearning.services.impl.PasswordProtection;
import cultureLearning.cultureLearning.services.impl.PdfBuilder;
import cultureLearning.cultureLearning.services.impl.StudentService;

import cultureLearning.cultureLearning.services.impl.TranslationService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class StudentModel {
	
	@WireVariable("student")
	private StudentService service;
	@WireVariable("address")
	private AddressServiceImplementation address;
	@Autowired
	private Student student;
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
	private ListModelList<Student>listofStudents;
	private ListModelList<Address>listofCountries;
	private ListModelList<Student>listofLearner;
	
	
	
	private List<Student>numberOfStudents=new ArrayList<Student>();

	
	
	
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
	student=new Student();
	listofStudents=new ListModelList<Student>(service.findAllStudent());
	add=new Address();
	//listofLearner=new ListModelList<Student>(service.listOfLearner());
	listofCountries=new ListModelList<Address>(address.findAllAddress());
	Student u = (Student)Sessions.getCurrent().getAttribute("s_Student");
	if(u!=null) {
		student.setEmail(u.getEmail());
	}
	
}


@Command("save")
@NotifyChange("listofStudents")
public void addStudent() {
	try {
		System.out.println(add.getCode());
		if(add.getCode()==null) {
	throw  new CountryNotSelectedException("Hitamo Igihugu ");
		}
		else {
			
		
		 Address ad=address.findById(add.getCode());
		 
		student.setAddress(ad);
		String passwordHashed=PasswordProtection.hashPassword(student.getPassword());
		student.setPassword(passwordHashed);
		service.registerStudent(student);
		
		Clients.showNotification("Gufungura konti byagenze neza!!");
	Executions.sendRedirect("/Login.zul");
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		Clients.showNotification(e.getMessage());
		}
	}
@Command("adminAccount")
@NotifyChange("listofStudents")
public void addAdmin() {
	try {
		
		System.out.println(add.getCode());
		 Address ad=address.findById(add.getCode());
		 
		student.setAddress(ad);
		String passwordHashed=PasswordProtection.hashPassword(student.getPassword());
		student.setPassword(passwordHashed);
		System.out.println(student);
		service.registerStudent(student);
		
		Clients.showNotification("Gufungura konti byagenze neza!");
	//Executions.sendRedirect("/Login.zul");
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		Clients.showNotification(e.getMessage());
		}
	}
	



@Command("num")
@NotifyChange("listofStudents")
public int display() {
	int size=listofStudents.size();
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


public ListModelList<Student> getListofStudents() {
	return listofStudents;
}
public void setListofStudents(ListModelList<Student> listofStudents) {
	this.listofStudents = listofStudents;
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
		List<Student> l = service.findAllStudent();
		PdfBuilder pdf = new PdfBuilder();
		pdf.addTitle("Lisiti y'abiga bose");
		pdf.init(4);
		pdf.addTableHeader(new String[]{"amazina", "imeri", "irangamuntu"," Address"});
		for(Student Student : l) {
			pdf.addTableCell(Student.getFname()+" "+Student.getLname());
			//pdf.addTableCell(Student.getLname());
			pdf.addTableCell(Student.getEmail());
			pdf.addTableCell(Student.getNid());
			 pdf.addTableCell(Student.getAddress().getName());
			 //pdf.addTableCell(Student.getType());
		}
		pdf.run();
		Clients.showNotification("byagenze neza");
	}catch(Exception e) {
		Clients.showNotification(e.getMessage());
	}
}
@Command("printButtonAll")
public void printReportAllStudents() {
	try {
		List<Student> l = service.findAllStudent();
		PdfBuilder pdf = new PdfBuilder();
		pdf.addTitle("Lisiti y'abakoresha urubuga bose");
		pdf.init(6);
		pdf.addTableHeader(new String[]{"izina rya mbere", "izina rya nyuma", "imeri", "irangamuntu"," Address","ubwoko bwa abakoresha"});
		for(Student Student : l) {
			pdf.addTableCell(Student.getFname());
			pdf.addTableCell(Student.getLname());
			pdf.addTableCell(Student.getEmail());
			pdf.addTableCell(Student.getNid());
			 pdf.addTableCell(Student.getAddress().getName()); 
			 //pdf.addTableCell(Student.getType());
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
public ListModelList<Student> getListofLearner() {
	return listofLearner;
}
public void setListofLearner(ListModelList<Student> listofLearner) {
	this.listofLearner = listofLearner;
}
public StudentService getService() {
	return service;
}
public void setService(StudentService service) {
	this.service = service;
}
public Student getStudent() {
	return student;
}
public void setStudent(Student student) {
	this.student = student;
}
public List<Student> getNumberOfStudents() {
	return numberOfStudents;
}
public void setNumberOfStudents(List<Student> numberOfStudents) {
	this.numberOfStudents = numberOfStudents;
}



}
