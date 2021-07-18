package cultureLearning.cultureLearning.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Language extends Course{


	private static final long serialVersionUID = 1L;
//	@OneToMany
//	private List<Lesson>listOfLesson;
//	
//	public List<Lesson> getListOfLesson() {
//		return listOfLesson;
//	}
//	public void setListOfLesson(List<Lesson> listOfLesson) {
//		this.listOfLesson = listOfLesson;
	//}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
