package cultureLearning.cultureLearning.navigation;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.Culture;
import cultureLearning.cultureLearning.domain.Language;
import cultureLearning.cultureLearning.domain.Subscription;
import cultureLearning.cultureLearning.domain.User;
import cultureLearning.cultureLearning.domain.Video;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;
import cultureLearning.cultureLearning.services.impl.UserServiceImplementation;
import cultureLearning.cultureLearning.services.impl.VideoService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class NavigationViewModel2 extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	private static NavigationPage currentPage;
	private static NavigationViewModel2 model;

	@WireVariable("Course")
	private CourseServiceImplementation courseService;
	private Map<String, Map<String, NavigationPage>> pageMap;
	static private Map<String, Object> authorizations;
	@WireVariable("Service")
	private UserServiceImplementation userService;
	@WireVariable("video")
	private VideoService videoService;

	private java.util.Set<String> categories = new HashSet<String>();
	private java.util.Set<String> videoCategory = new HashSet<String>();
	private List<String> ll = new ArrayList();

	@Init
	public void init() {
		initPageMap();
		if (currentPage != null) {
			System.out.println(currentPage.getIncludeUri());
		}

		currentPage = pageMap.get("Ahabanza").get("Murakaza Neza");
	}

	@Command
	public void navigatePage(@BindingParam("target") NavigationPage targetPage) {
		if (!isAuthorized(targetPage.getSubTitle())) {
			targetPage = new NavigationPage(null, null,
					"/PayingForm.zul?subId=" + authorizations.get(targetPage.getSubTitle()), null) {

				@Override
				public boolean isSelected() {
					// TODO Auto-generated method stub
					return false;
				}
			};

		}
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

		addPage("Ahabanza", "Murakaza Neza", "/courseClient/yoyo.zul");

		addPage("Amateka", "Abami b'Urwanda", "/hist/King.zul");

		addPage("Location", "display", "/location/Display.zul", "active");

		addPage("Ibitekerezo", "tanga ibitekerezo", "/comment/Comment.zul", "inactive");
		addPage("Ibitekerezo", "kureba ibitekerezo ", "/comment/DisplayComment.zul", "active");

		addPage("Twandikire", "Twandikire", "/comment/ContactUs.zul", "inactive");
		addPage("Abo turi bo", "Amavu n'amavuko", "/comment/AboutUs.zul", "inactive");

		List<Course> courses = courseService.findAllCourse();

		for (Course course : courses) {
			categories.add(course.getLevel().getLevel());
			addPage(course.getLevel().getLevel(), course.getChapter(),
					"/courseClient/beginner2/Ingombajwi.zul?id=" + course.getCourseCode() + "&a=", "active",
					course.getLevel().getSubscriptionTypeId());

			categories.add(course.getLevel().getLevel());
			addPage(course.getLevel().getLevel(), course.getChapter(),
					"/courseClient/beginner2/Ingombajwi.zul?id=" + course.getCourseCode() + "&a=", "active",
					course.getLevel().getSubscriptionTypeId());

		}

		List<Video> videos = videoService.listVideo();
		for (Video video : videos) {

			addPage("Videwo", video.getTitle(), "/video/DisplayVideo2.zul?id=" + video.getId());
			addPage("Videwo", "umuco wa kinyarwanda", "/users/V.zul", "inactive");
		}

	}

	private void addPage(String title, String subTitle, String includeUri) {
		addPage(title, subTitle, includeUri, null);
	}

	private void addPage(String title, String subTitle, String includeUri, String data, String permission) {
		if (authorizations == null)
			authorizations = new LinkedHashMap<>();
		authorizations.put(subTitle, permission);
		addPage(title, subTitle, includeUri, data);
	}

	private void addPage(String title, String subTitle, String includeUri, String data) {
		String folder = "/pages";
		Map<String, NavigationPage> subPageMap = pageMap.get(title);
		if (subPageMap == null) {
			subPageMap = new LinkedHashMap<String, NavigationPage>();
			pageMap.put(title, subPageMap);
		}
		NavigationPage navigationPage = new NavigationPage(title, subTitle, folder + includeUri // + "?random=" +
																								// Math.random()
				, data) {
			@Override
			public boolean isSelected() {
				return currentPage == this;
			}
		};
		subPageMap.put(subTitle, navigationPage);
	}

	private boolean isAuthorized(String url) {
		if (authorizations == null)
			authorizations = new LinkedHashMap<>();
		System.out.println("SAAAAAAAAAAAAA");
		Object permission = authorizations.get(url);
		System.out.println("ADF" + permission + url);
		System.out.println(Arrays.toString(authorizations.keySet().toArray()));
		if (permission == null) {
			return true;
		}
		User user = userService.getByEmail(((User) Sessions.getCurrent().getAttribute("s_user")).getEmail());
		Subscription sub = user.getActiveSubscription(permission + "");
		if (sub != null) {
			if (permission.equals(sub.getSubscription().getSubscriptionTypeId())) {
				return true;
			}
		}
		return false;
	}

	@Command("navigate")
	public void navigate(@BindingParam("url") String url) {

		BindUtils.postNotifyChange(null, null, currentPage, "selected");
//		currentPage = pageMap.get("Home").get("Ikaze");
		setCurrentPage(new NavigationPage(null, null, url, null) {

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

	public void setCurrentPage(NavigationPage currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageMap(Map<String, Map<String, NavigationPage>> pageMap) {
		this.pageMap = pageMap;
	}

	public void setCourseService(CourseServiceImplementation courseService) {
		this.courseService = courseService;
	}

	public CourseServiceImplementation getCourseService() {
		return courseService;
	}

	public static NavigationViewModel2 getModel() {
		return model;
	}

	public static void setModel(NavigationViewModel2 model) {
		NavigationViewModel2.model = model;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		NavigationPage targetPage = new NavigationPage(null, null, "/pages/users/ChangePassword.zul", null) {

			@Override
			public boolean isSelected() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		navigatePage(targetPage);
	}

	public java.util.Set<String> getVideoCategory() {
		return videoCategory;
	}

	public void setVideoCategory(java.util.Set<String> videoCategory) {
		this.videoCategory = videoCategory;
	}

	public static Map<String, Object> getAuthorizations() {
		return authorizations;
	}

	public static void setAuthorizations(Map<String, Object> authorizations) {
		NavigationViewModel2.authorizations = authorizations;
	}

	public UserServiceImplementation getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImplementation userService) {
		this.userService = userService;
	}

	public VideoService getVideoService() {
		return videoService;
	}

	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}

	public List<String> getLl() {
		return ll;
	}

	public void setLl(List<String> ll) {
		this.ll = ll;
	}

}
