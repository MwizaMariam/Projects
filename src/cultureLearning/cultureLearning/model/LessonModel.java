package cultureLearning.cultureLearning.model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.io.Files;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.Culture;
import cultureLearning.cultureLearning.domain.Language;
import cultureLearning.cultureLearning.domain.Lesson;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;
import cultureLearning.cultureLearning.services.impl.CultureServiceImplementation;
import cultureLearning.cultureLearning.services.impl.LanguageServiceImplementation;
import cultureLearning.cultureLearning.services.impl.LessonServiceImplementation;

import cultureLearning.cultureLearning.services.impl.PdfBuilder;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LessonModel {
	@WireVariable("Lesson")
	private LessonServiceImplementation service;

	@WireVariable("Course")
	private CourseServiceImplementation courseService;
	private List<Lesson> listLessons;
	@Autowired

	private Course course;
	@WireVariable("Lesson")
	private LessonServiceImplementation leService;
	@Autowired
	private Lesson lesson;

	private ListModelList<Lesson> listofContentlesson;
	private ListModelList<Course> listOfCourses;

	private String coursecode;;

	private ListModelList<Language> listOfLanguage;

	private ListModelList<Culture> listOfCulture;
	@WireVariable("language")
	private LanguageServiceImplementation ls;
	@WireVariable("culture")

	private CultureServiceImplementation cs;
	@Autowired
	private SessionFactory session;

	private boolean isUpdateMode = false;
	private ListModelList<Lesson> listOfContentsInChapter;
	private ListModelList<Course> listOfContent;
	private ListModelList<Lesson> listOfingombajwi;
	private ListModelList<Lesson> listOfitondeYinyugutiZikinyarwanda;
	private ListModelList<Lesson> listOfibihekane;
	private ListModelList<Lesson> listOfinshinga;
	private ListModelList<Lesson> listOfinteruro;

	private String filePath;
	private boolean fileuploaded = false;
	AMedia fileContent;

	@Init
	public void init(@QueryParam("id") String id, @QueryParam("lessonId") Integer lessonId) {

		course = new Course();
		lesson = new Lesson();

		listOfLanguage = new ListModelList<Language>(ls.findAllLanguage());
		listOfCulture = new ListModelList<Culture>(cs.findAllCulture());
		listofContentlesson = new ListModelList<Lesson>(service.findAllLesson());
		listOfCourses = new ListModelList<Course>(courseService.findAllCourse());

		listOfingombajwi = new ListModelList<Lesson>(service.ingombajwi());

		if (id == null) {
			listLessons = service.findAllLesson();

			System.out.println("Emtpy");
		} else {
			course = courseService.findById(id);
			System.out.println(course);
			course.setVisit(course.getVisit() + 1);
			courseService.update(course);

		}
	}

	@Command("updateLesson")
	@NotifyChange("listLessons")
	public void updateLesson(@BindingParam("up") Lesson l) {
		try {
			service.update(l);
			Clients.showNotification("Ivugururwa ryagenze neza!");
		} catch (Exception e) {
			// TODO: handle exception
			Clients.showNotification("kuvugurwa ntibyagenze neza!");
			e.getMessage();
			e.printStackTrace();
		}
	}

	@Command("loadLesson")
	// @NotifyChange({"lesson"})
	public void loadLesson(@BindingParam("id") String id) {

		lesson = service.findById(id);
	}

	public ListModelList<Course> getListOfCourses() {
		return listOfCourses;
	}

	public LessonServiceImplementation getLeService() {
		return leService;
	}

	public void setLeService(LessonServiceImplementation leService) {
		this.leService = leService;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public void setListOfCourses(ListModelList<Course> listOfCourses) {
		this.listOfCourses = listOfCourses;
	}

	@Command("saveLesson")
	@NotifyChange("listLessons")
	public void saveLesson() {
		try {

			Course c = courseService.findById(course.getCourseCode());
			lesson.setCourse(c);
			service.registerLesson(lesson);
			Clients.showNotification("Byoherejwe neza");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
	}

	@Command("deleteContent")
	public void deleteContent(@BindingParam("de") Lesson l) {
		try {

			service.deleteLesson(l);
			Clients.showNotification("byagenze neza!");

		} catch (Exception e) {
			// TODO: handle exception //
			e.printStackTrace();
			Clients.showNotification("ntibyagenze neza!" + e.getMessage());
		}

	}

	@Command("printViewedCourse")
	public void printReport() {
		try {
			List<Course> c = courseService.viewCourse();
			PdfBuilder pdf = new PdfBuilder();
			pdf.addTitle("Lisiti y'amasomo bakunze kwiga cyane");
			pdf.init(3);
			pdf.addTableHeader(new String[] { "id", "izina ry'isomo", "incuro ryizwe", });
			for (Course course : c) {
				pdf.addTableCell(course.getCourseCode() + "");
				pdf.addTableCell(course.getChapter());
				pdf.addTableCell(course.getVisit() + "");

			}
			pdf.run();
			Clients.showNotification("byagenze neza");
		} catch (Exception e) {
			e.printStackTrace();
			Clients.showNotification(e.getMessage());
		}
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

	}

	@Command
	@NotifyChange("fileuploaded")
	public void onUploadPDF(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {

		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH); // Note: zero based!
			int day = now.get(Calendar.DAY_OF_MONTH);
			filePath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
			String yearPath = "\\" + "PDFs" + "\\" + year + "\\" + month + "\\" + day + "\\";
			filePath = filePath + yearPath;
			File baseDir = new File(filePath);
			if (!baseDir.exists()) {
				baseDir.mkdirs();

			}

			Files.copy(new File(filePath + media.getName()), media.getStreamData());
			lesson.setFileUpload(filePath + media.getName());
			Messagebox.show("File Sucessfully uploaded in the path [ ." + filePath + " ]");
			fileuploaded = true;
			filePath = filePath + media.getName();
		}
	}

	@Command
	@NotifyChange("fileContent")
	public void showPDF() throws IOException {
		File f = new File(filePath);
		Messagebox.show(" Ububiko bwawe ni:" + filePath);
		byte[] buffer = new byte[(int) f.length()];
		FileInputStream fs = new FileInputStream(f);
		fs.read(buffer);
		fs.close();
		ByteArrayInputStream is = new ByteArrayInputStream(buffer);
		fileContent = new AMedia("report", "pdf", "application/pdf", is);

	}

	@Command("downloadFile")
	public void downloadFile(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {

		try {

			UploadEvent upEvent = null;
			Object objUploadEvent = ctx.getTriggerEvent();

			if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
				upEvent = (UploadEvent) objUploadEvent;
			}
			if (upEvent != null) {
				Media media = upEvent.getMedia();
				filePath = filePath + media.getName();
				org.zkoss.zul.Filedownload.save(filePath, "application/pdf");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Clients.showNotification("not downloaded" + e.getMessage());
		}
		
		
	}
	
	@Command("downloadByPath")
	public void downloadFile(@BindingParam("path") String path) {
		try {
			if(!new File(path).exists()) throw new Exception("File doesnt exist"+path);
			Filedownload.save(new FileInputStream(path), "application/pdf", "report");
			Clients.showNotification("umwitozo wawe uri kwibika");
		} catch (Exception e) {
			e.printStackTrace();
			Clients.showNotification("not downloaded" + e.getMessage()+path);
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isFileuploaded() {
		return fileuploaded;
	}

	public void setFileuploaded(boolean fileuploaded) {
		this.fileuploaded = fileuploaded;
	}

	public AMedia getFileContent() {
		return fileContent;
	}

	public void setFileContent(AMedia fileContent) {
		this.fileContent = fileContent;
	}

	public LessonServiceImplementation getService() {
		return service;
	}

	public void setService(LessonServiceImplementation service) {
		this.service = service;
	}

	public CourseServiceImplementation getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseServiceImplementation courseService) {
		this.courseService = courseService;
	}

	public List<Lesson> getListLessons() {
		return listLessons;
	}

	public void setListLessons(List<Lesson> listLessons) {
		this.listLessons = listLessons;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ListModelList<Lesson> getListofContentlesson() {
		return listofContentlesson;
	}

	public void setListofContentlesson(ListModelList<Lesson> listofContentlesson) {
		this.listofContentlesson = listofContentlesson;
	}

	public ListModelList<Language> getListOfLanguage() {
		return listOfLanguage;
	}

	public void setListOfLanguage(ListModelList<Language> listOfLanguage) {
		this.listOfLanguage = listOfLanguage;
	}

	public ListModelList<Culture> getListOfCulture() {
		return listOfCulture;
	}

	public void setListOfCulture(ListModelList<Culture> listOfCulture) {
		this.listOfCulture = listOfCulture;
	}

	public LanguageServiceImplementation getLs() {
		return ls;
	}

	public void setLs(LanguageServiceImplementation ls) {
		this.ls = ls;
	}

	public CultureServiceImplementation getCs() {
		return cs;
	}

	public void setCs(CultureServiceImplementation cs) {
		this.cs = cs;
	}

	public boolean isUpdateMode() {
		return isUpdateMode;
	}

	public void setUpdateMode(boolean isUpdateMode) {
		this.isUpdateMode = isUpdateMode;
	}

	@Command("loadCourse")
	@NotifyChange("course")
	public void loadCourse(@BindingParam("id") String id) {
		lesson = leService.findById(id);

		isUpdateMode = true;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}

	public ListModelList<Course> getListOfContent() {
		return listOfContent;
	}

	public void setListOfContent(ListModelList<Course> listOfContent) {
		this.listOfContent = listOfContent;
	}

	public ListModelList<Lesson> getListOfContentsInChapter() {
		return listOfContentsInChapter;
	}

	public void setListOfContentsInChapter(ListModelList<Lesson> listOfContentsInChapter) {
		this.listOfContentsInChapter = listOfContentsInChapter;
	}

	public ListModelList<Lesson> getListOfingombajwi() {
		return listOfingombajwi;
	}

	public void setListOfingombajwi(ListModelList<Lesson> listOfingombajwi) {
		this.listOfingombajwi = listOfingombajwi;
	}

	public ListModelList<Lesson> getListOfitondeYinyugutiZikinyarwanda() {
		return listOfitondeYinyugutiZikinyarwanda;
	}

	public void setListOfitondeYinyugutiZikinyarwanda(ListModelList<Lesson> listOfitondeYinyugutiZikinyarwanda) {
		this.listOfitondeYinyugutiZikinyarwanda = listOfitondeYinyugutiZikinyarwanda;
	}

	public ListModelList<Lesson> getListOfibihekane() {
		return listOfibihekane;
	}

	public void setListOfibihekane(ListModelList<Lesson> listOfibihekane) {
		this.listOfibihekane = listOfibihekane;
	}

	public ListModelList<Lesson> getListOfinshinga() {
		return listOfinshinga;
	}

	public void setListOfinshinga(ListModelList<Lesson> listOfinshinga) {
		this.listOfinshinga = listOfinshinga;
	}

	public ListModelList<Lesson> getListOfinteruro() {
		return listOfinteruro;
	}

	public void setListOfinteruro(ListModelList<Lesson> listOfinteruro) {
		this.listOfinteruro = listOfinteruro;
	}

}
