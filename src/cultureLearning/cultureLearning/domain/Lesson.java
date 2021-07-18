package cultureLearning.cultureLearning.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;



@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "BaseType", discriminatorType=DiscriminatorType.STRING)
public  class Lesson  {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	private int lessonId;
	private String lessonContent;
	@Column(columnDefinition = "TEXT")
	private String fileUpload;
	@ManyToOne
	private Course course;
	
	public Lesson(int lessonId, String lessonContent, String fileUpload,Course course) {
		super();
		this.lessonId = lessonId;
		this.lessonContent = lessonContent;
		this.course = course;
		this.fileUpload=fileUpload;
	}
	public Lesson() {
		super();
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public String getLessonContent() {
		return lessonContent;
	}
	public void setLessonContent(String lessonContent) {
		this.lessonContent = lessonContent;
	}
//	@Override
//	public String toString() {
//		return "Lesson [lessonId=" + lessonId + ", lessonContent=" + lessonContent + ", course=" + course + "]";
//	}
	public String getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}
	
	
	
}
