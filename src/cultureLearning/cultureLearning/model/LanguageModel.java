package cultureLearning.cultureLearning.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;

import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.Culture;
import cultureLearning.cultureLearning.domain.Language;
import cultureLearning.cultureLearning.services.CultureDao;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;
import cultureLearning.cultureLearning.services.impl.CultureServiceImplementation;
import cultureLearning.cultureLearning.services.impl.LanguageServiceImplementation;

@VariableResolver(DelegatingVariableResolver.class)
public class LanguageModel {
	@Autowired
private Language language;
	@WireVariable("Course")
	private CourseServiceImplementation courseService;
	private Culture culture;
	@WireVariable("culture")
	private CultureServiceImplementation cService;
	@Autowired
	private Course course;
@WireVariable("language")
private LanguageServiceImplementation lservice;

private ListModelList<Language>listOfLanguage;
private ListModelList<Course>listOfCourses;
private ListModelList<Culture>listOfCulture;

@Init
public void initialization() {
	language=new Language();
	culture=new Culture();
	course=new Course();
	listOfLanguage=new ListModelList<Language>(lservice.findAllLanguage());
	listOfCourses=new ListModelList<Course>(courseService.findAllCourse());
	listOfCulture=new ListModelList<Culture>(cService.findAllCulture());
}
@Command("saveLanguage")
@NotifyChange("listOfLanguage")
public void createLanguage() {
	try {
//		Course c=courseService.findById(course.getCourseCode());
//		language.setCourseCode(c);
		lservice.registerLanguage(language);;
		Clients.showNotification("byagenze neza!!");
	} catch (Exception e) {
		// TODO: handle exception
		Clients.showNotification(e.getMessage());
		e.printStackTrace();
	}
}
@Command("updateLanguage")
public void update(@BindingParam("up")Language language) {
	try {
		lservice.update(language);;
		Clients.showNotification("byagenze neza!!!");
	} catch (Exception e) {
		// TODO: handle exception
		Clients.showNotification(e.getMessage());
		e.printStackTrace();
	}
}
@Command("deleteLanguage")
public void delete(@BindingParam("de")Language language) {
	try {
		lservice.deleteLanguage(language);;
		Clients.showNotification("byagenze!!");
	} catch (Exception e) {
		// TODO: handle exception
		Clients.showNotification("ntibyagenze neza mwongere mugerageze!!" +e.getMessage());
	}
}
public Language getLanguage() {
	return language;
}
public void setLanguage(Language language) {
	this.language = language;
}
public LanguageServiceImplementation getLservice() {
	return lservice;
}
public void setLservice(LanguageServiceImplementation lservice) {
	this.lservice = lservice;
}
public ListModelList<Language> getListOfLanguage() {
	return listOfLanguage;
}
public void setListOfLanguage(ListModelList<Language> listOfLanguage) {
	this.listOfLanguage = listOfLanguage;
}
public Course getCourse() {
	return course;
}
public void setCourse(Course course) {
	this.course = course;
}

public ListModelList<Course> getListOfCourses() {
	return listOfCourses;
}
public void setListOfCourses(ListModelList<Course> listOfCourses) {
	this.listOfCourses = listOfCourses;
}
public CourseServiceImplementation getCourseService() {
	return courseService;
}
public void setCourseService(CourseServiceImplementation courseService) {
	this.courseService = courseService;
}


	

}
