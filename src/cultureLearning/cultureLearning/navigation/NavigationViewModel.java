package cultureLearning.cultureLearning.navigation;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.Culture;
import cultureLearning.cultureLearning.domain.Language;
import cultureLearning.cultureLearning.domain.Subscription;
import cultureLearning.cultureLearning.domain.User;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class NavigationViewModel  extends SelectorComposer<Component>{
	private static final long SerialVersionUID=1L;
	private static NavigationPage currentPage;
	private static NavigationViewModel model;
	private Map<String, Map<String, NavigationPage>> pageMap;
	@WireVariable("Course")
	private CourseServiceImplementation courseService;
	
private java.util.Set<String> categories = new HashSet<String>();
	
	private List<String> ll=new ArrayList();
	
	@Init
	public void init() {
		initPageMap();
		if(currentPage!=null) {System.out.println(currentPage.getIncludeUri());}
		currentPage = pageMap.get("Ahabanza").get("Murakaza Neza");
	}

	@Command
	public void navigatePage(@BindingParam("target") NavigationPage targetPage) {
		model = this;
		BindUtils.postNotifyChange(null, null, currentPage, "selected");
		currentPage = targetPage;
		BindUtils.postNotifyChange(null, null, this, "currentPage");
	}
	
	public NavigationPage getCurrentPage() {
		return currentPage;
	}

	public Map<String, Map<String, NavigationPage>> getPageMap() {
		return pageMap;
	}

	private void initPageMap() {
		pageMap = new LinkedHashMap<String, Map<String, NavigationPage>>();
		addPage("Ongeramo ibigize Isomo","Ongera Isomo","/courseClient/Course.zul");
		
		List<Course> courses = courseService.findAllCourse();
		
		for(Course course : courses) {
			
				categories.add(course.getLevel().getLevel());
				  addPage(course.getLevel().getLevel(), course.getChapter(), "/courseClient/ContentForm.zul?id="+course.getCourseCode());
				 addPage("Ongeramo Ikiciro", "Kureba byiciro", "/courseClient/ListOfLevelForm.zul", "active");
					
					addPage("Ongeramo Ikiciro", "Kongeramo ikiciro cy'isomo", "/courseClient/LevelForm.zul", "active");
					
					categories.add(course.getLevel().getLevel());
					  addPage(course.getLevel().getLevel(),course.getChapter(),"/courseClient/ContentForm.zul?id="+course.getCourseCode());
				  }
				 
		addPage("Ahantu", "ongera ahantu", "/location/Location.zul");
		addPage("Ahantu", "Kureba ahantu", "/location/Display.zul");
		
		
		addPage("Ahabanza", "Murakaza Neza", "/courseClient/yoyo.zul", "active");
		  addPage("Amateka", "Abami b'Urwanda", "/hist/King.zul");
		 
		addPage("Setingi", " Guhindura ijamboBanga ", "/users/ChangePassword.zul", "active");
		
		
		
	
		addPage("Abakoresha urubuga", "Abiga bose", "/users/LearnerList.zul");
		addPage("Abakoresha urubuga", "Abigisha bose", "/users/TeacheList.zul");
		
		  addPage("Abakoresha urubuga", " Abakoresha urubuga bose ", "/users/Display.zul",
		  "active");
		  addPage("Abakoresha urubuga", "Ongeramo Umwarimu", "/users/Teacher.zul");
		
		addPage("Raporo", "Incuro isomo ryasuwe", "/report/ViewCaurseReport.zul");
		addPage("Raporo", "Raporo y'Imari", "/report/AboutPayment.zul");
		addPage("Ongeramo videwo", "Kongeramo Umuyoboro wa videwo", "/video/createVideoLink.zul", "active");
		 addPage("Kureba Ibitekerezo", "kureba ibitekerezo ","/comment/DisplayComment.zul","active"); 
		 addPage("Ongeramo videwo", "Kureba  videwo zose", "/video/DisplayVideo.zul", "active");
		 addPage("Kureba Ibitekerezo", "gutanga ibitekerezo ","/comment/Comment.zul","active"); 
		
		
	}
	
	

	private void addPage(String title, String subTitle, String includeUri) {
		addPage(title, subTitle, includeUri, null);
	}
	
	
	
	private void addPage(String title, String subTitle, String includeUri, String data) {
		String folder = "/pages";
		Map<String, NavigationPage> subPageMap = pageMap.get(title);
		if(subPageMap == null) {
			subPageMap = new LinkedHashMap<String, NavigationPage>();
			pageMap.put(title, subPageMap);
		}
		NavigationPage navigationPage = new NavigationPage(title, subTitle,
				folder + includeUri , data) {
			@Override
			public boolean isSelected() {
				return currentPage == this;
			}
		};
		subPageMap.put(subTitle, navigationPage);
	}
	
	@Command("navigate")
	public void navigate(@BindingParam("url") String url) {
		
		BindUtils.postNotifyChange(null, null, currentPage, "selected");
//		currentPage = pageMap.get("Home").get("Ikaze");
		setCurrentPage(new NavigationPage(null,null,url,null) {
			
			@Override
			public boolean isSelected() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		System.out.println(model.getCurrentPage().getIncludeUri());
		System.out.println(currentPage.getIncludeUri());
		BindUtils.postNotifyChange(null, null, this, "currentPage");
		BindUtils.postNotifyChange(null, null, model, "currentPage");
	}

	public static long getSerialversionuid() {
		return SerialVersionUID;
	}

	public void setCurrentPage(NavigationPage currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageMap(Map<String, Map<String, NavigationPage>> pageMap) {
		this.pageMap = pageMap;
	}
	
	public CourseServiceImplementation getCourseService() {
		return courseService;
	}
	
	public void setCourseService(CourseServiceImplementation courseService) {
		this.courseService = courseService;
	}
	public java.util.Set<String> getCategories() {
		return categories;
	}
	public void setCategories(java.util.Set<String> categories) {
		this.categories = categories;
	}
	
	@Command("translate")
	public void translate() {
		System.out.println("SSSS");
		NavigationPage targetPage = new NavigationPage(null,null,"/pages/users/ChangePassword.zul",null) {
			
			@Override
			public boolean isSelected() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		navigatePage(targetPage);
	}
}
