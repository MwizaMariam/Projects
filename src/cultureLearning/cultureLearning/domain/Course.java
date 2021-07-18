package cultureLearning.cultureLearning.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	private  String courseCode;

	
	private  String Chapter ;
	@OneToMany
	private List<Lesson>listOfLesson;
	
	
	private int visit;
	
	
@OneToOne
	private SubscriptionType level;
	
	
	
public String getChapter() {
		return Chapter;
	}

	public void setChapter(String chapter) {
		Chapter = chapter;
	}



	public int getVisit() {
		return visit;
	}
	
	public void setVisit(int visit) {
		this.visit = visit;
	}
	
	
	  public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	

	@Override
	public String toString() {
		return "Course [courseCode=" + courseCode + ", Chapter=" + Chapter + ", listOfLesson="
				+ listOfLesson + ", visit=" + visit + ", accountType=" + level + "]";
	}

	public List<Lesson> getListOfLesson() {
		return listOfLesson;
	}

	public void setListOfLesson(List<Lesson> listOfLesson) {
		this.listOfLesson = listOfLesson;
	}

	
	public SubscriptionType getLevel() {
		return level;
	}

	public void setLevel(SubscriptionType level) {
		this.level = level;
	}

	 
		  
		  
	  
	
}
