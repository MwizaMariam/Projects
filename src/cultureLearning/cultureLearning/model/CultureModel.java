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
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;
import cultureLearning.cultureLearning.services.impl.CultureServiceImplementation;
import cultureLearning.cultureLearning.services.impl.GenericStoryService;


@VariableResolver(DelegatingVariableResolver.class)
public class CultureModel {
	@Autowired
private Culture culture;
	@WireVariable("culture")
	private CultureServiceImplementation cservice;
	@Autowired
	private Course course;
	@WireVariable("Course")
	private CourseServiceImplementation courseService;
	@Autowired
	private ListModelList<Course> listOfCourse;

	@Autowired
private ListModelList<Culture>listOfCulture;

@Init
public void initialization() {
	
	culture=new Culture();
	course=new Course();
	
	listOfCourse=new ListModelList<Course>(courseService.findAllCourse());
	listOfCulture=new ListModelList<Culture>(cservice.findAllCulture());
}
	
	@Command("saveCulture")
	@NotifyChange("listOfLanguage")
	public void createLanguage() {
		try {
			
			cservice.registerCulture(culture);
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
			cservice.update(culture);
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
			cservice.deleteCulture(culture);
			Clients.showNotification("byagenze!!");
		} catch (Exception e) {
			// TODO: handle exception
			Clients.showNotification("ntibyagenze neza mwongere mugerageze!!" +e.getMessage());
		}
	}
	//@Command("saveCulture")
	//@NotifyChange("listOfCulture")
	//public void createCulture() {
//		try {
//			cultureService.create(culture);
//			Clients.showNotification("byoherejwe neza");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	@Command("deleteCulture")
//	@NotifyChange("listOfCulture")
//	public void deleteCulture(@BindingParam("de")Culture culture) {
//		try {
//			cultureService.delete(culture);
//			Clients.showNotification(" byagenze neza!!");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	//@Command("updateCulture")
	//@NotifyChange("listOfCulture")
	//public void updateCulture(@BindingParam("up")Culture culture) {
//		try {
//			cultureService.update(culture);
//			Clients.showNotification("byahinduwe neza!!!");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	
//	public Culture getCulture() {
//		return culture;
//	}
//	public void setCulture(Culture culture) {
//		this.culture = culture;
//	}
//	
//	public GenericStoryService<Culture> getCultureService() {
//		return cultureService;
//	}
//	public void setCultureService(GenericStoryService<Culture> cultureService) {
//		this.cultureService = cultureService;
//	}
//	public ListModelList<Culture> getListOfCulture() {
//		return listOfCulture;
//	}
//	public void setListOfCulture(ListModelList<Culture> listOfCulture) {
//		this.listOfCulture = listOfCulture;
//	}
//	public ListModelList<Culture> getListofTitle() {
//		return listofTitle;
//	}
//	public void setListofTitle(ListModelList<Culture> listofTitle) {
//		this.listofTitle = listofTitle;
//	}
//	public ListModelList<Culture> getListC() {
//		return listC;
//	}
//	public void setListC(ListModelList<Culture> listC) {
//		this.listC = listC;
//	}
//	
	public Culture getCulture() {
		return culture;
	}
	public void setCulture(Culture culture) {
		this.culture = culture;
	}
	public CultureServiceImplementation getCservice() {
		return cservice;
	}
	public void setCservice(CultureServiceImplementation cservice) {
		this.cservice = cservice;
	}
	public ListModelList<Culture> getListOfCulture() {
		return listOfCulture;
	}
	public void setListOfCulture(ListModelList<Culture> listOfCulture) {
		this.listOfCulture = listOfCulture;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public CourseServiceImplementation getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseServiceImplementation courseService) {
		this.courseService = courseService;
	}

	public ListModelList<Course> getListOfCourse() {
		return listOfCourse;
	}

	public void setListOfCourse(ListModelList<Course> listOfCourse) {
		this.listOfCourse = listOfCourse;
	}
	

}
