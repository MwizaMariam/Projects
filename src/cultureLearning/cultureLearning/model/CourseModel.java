package cultureLearning.cultureLearning.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;

import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;


import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.Culture;
import cultureLearning.cultureLearning.domain.Language;

import cultureLearning.cultureLearning.domain.SubscriptionType;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;

import cultureLearning.cultureLearning.services.impl.LessonServiceImplementation;
import cultureLearning.cultureLearning.services.impl.SubscriptionTypeServiceImplementation;




@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)

public class CourseModel  extends SelectorComposer<Component>{
	@WireVariable("Course")
	private CourseServiceImplementation service;
	@WireVariable("Lesson")
	private LessonServiceImplementation lessonService;
	@Autowired
	private Course course;
	private List<Course> listCourses;
	@Autowired
	private ListModelList<Course>listOfCourses;
	@Autowired
	private ListModelList<Course> listOfCourseType;
	@Autowired
	private SubscriptionType subscription;
	private ListModelList<SubscriptionType>listOfSubscriptionType;
	@WireVariable("SubscriptionType")
	private SubscriptionTypeServiceImplementation subservice;
	private boolean isUpdateMode = false;
	@Wire
    private Window win;
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public CourseServiceImplementation getService() {
		return service;
	}
	
	public void setService(CourseServiceImplementation service) {
		this.service = service;
	}
	
	public List<Course> getListCourses() {
		return listCourses;
	}
	
	public void setListCourses(List<Course> listCourses) {
		this.listCourses = listCourses;
	}
	
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	@Init
	public void init() {
		listCourses = service.findAllCourse();
		subscription=new SubscriptionType();
		/* listOfCourseType=new ListModelList<Course>(service.listOfCourseType()); */
		
		listOfSubscriptionType= new ListModelList<SubscriptionType>(subservice.listofSubscriptionType());
		if(listCourses.size()%10==0) {
			course = new Culture();
		} else {
			course = new Language() ;
		}
		listOfCourses=new ListModelList<Course>(service.getVisits());
		
	}
	
	@Command("saveCourse")
	@NotifyChange("listCourses")
	public void saveCourse() {
		if(!isUpdateMode) {
			SubscriptionType type=subservice.findById(subscription.getSubscriptionTypeId());
			course.setLevel(type);
			service.registerCourse(course);
			course = new Course();
			Clients.showNotification("Isomo ryongewemo neza");
		} else {
			service.update(course);
			Clients.showNotification("byahinduwe neza");
			course = new Course();
			isUpdateMode = false;
		}
	}
	@Command("updateCourse")
	@NotifyChange("listCourses")
	public void updateCourse(@BindingParam("up") Course c) {
		try {
			service.update(c);
			Clients.showNotification("byavuguruwe neza");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Clients.showNotification("ntibyavuguruwe neza"+e.getMessage());
			
		}
	}
//	@Command("loadCourse")
//	@NotifyChange("course")
//	public void loadCourse(@BindingParam("id") Integer id) {
//		course = service.findById(id);
//		
//		isUpdateMode = true;
//	}
//	
	@Command("deleteCourse") 
	 public void deleteContent(@BindingParam("de") Course c) 
{ 
		   try {

	 service.deleteCourse(c);
	  Clients.showNotification("Isomo ryasibitse neza!");
	    
		   }catch (Exception e) { 
	  // TODO: handle exception // 
			   e.printStackTrace(); //
	  Clients.showNotification("ntiryasibitse!"+e.getMessage()); 
	  }
	  
	  }
	 

	
	  public ListModelList<Course> getListOfCourses() { return listOfCourses; }
	  
	  public void setListOfCourses(ListModelList<Course> listOfCourses) {
	  this.listOfCourses = listOfCourses; }
	 

	public boolean isUpdateMode() {
		return isUpdateMode;
	}

	public void setUpdateMode(boolean isUpdateMode) {
		this.isUpdateMode = isUpdateMode;
	}
	
	
	
public ListModelList<SubscriptionType> getListOfSubscriptionType() {
		return listOfSubscriptionType;
	}

	public void setListOfSubscriptionType(ListModelList<SubscriptionType> listOfSubscriptionType) {
		this.listOfSubscriptionType = listOfSubscriptionType;
	}

	//	public ListModelList<Object> getCourses(){
//		List<Object> l = new ArrayList<Object>();
//		List<Course> lc = service.findAllCourse();
//		for(Course c:lc) {
//			if(c.isQuestionBased()) {
//				l.add(c);
//			}
//		}
//		List<Lesson> ll = lessonService.findAllLesson();
//		for(Lesson le:ll) {
//			if(le instanceof ContentBasedLesson) {
//				l.add(le);
//			}
//		}
//		return new ListModelList<Object>(l);
//	}
	/*
	 * @Listen("onSelect = listbox") public void updateMessage() { Set<Course>
	 * selectedCourse = ((ListModelList<Course>)listOfCourseType).getSelection();
	 * int size = selectedCourse.size();
	 * 
	 * 
	 * showNotify(size > 0 ? size + " Course selected: " + selectedCourse :
	 * "no course selected", win); }
	 */
	/*
	 * private void showNotify(String msg,Component ref){
	 * Clients.showNotification(msg,"info",ref,"top_right",2000); }
	 */
	public LessonServiceImplementation getLessonService() {
		return lessonService;
	}
	
	public void setLessonService(LessonServiceImplementation lessonService) {
		this.lessonService = lessonService;
	}

	public ListModelList<Course> getListOfCourseType() {
		return listOfCourseType;
	}

	public void setListOfCourseType(ListModelList<Course> listOfCourseType) {
		this.listOfCourseType = listOfCourseType;
	}

	public Window getWin() {
		return win;
	}

	public void setWin(Window win) {
		this.win = win;
	}

	public SubscriptionType getSubscription() {
		return subscription;
	}

	public void setSubscription(SubscriptionType subscription) {
		this.subscription = subscription;
	}

	public SubscriptionTypeServiceImplementation getSubservice() {
		return subservice;
	}

	public void setSubservice(SubscriptionTypeServiceImplementation subservice) {
		this.subservice = subservice;
	}

	
	   
	
}
