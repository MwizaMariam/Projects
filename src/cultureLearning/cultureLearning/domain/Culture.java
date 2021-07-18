package cultureLearning.cultureLearning.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Culture extends Course{

	
	private static final long serialVersionUID = 1L;
//@OneToMany
//private List<Lesson>ListOfLessons;

//public List<Lesson> getListOfLessons() {
//	return ListOfLessons;
//}
//public void setListOfLessons(List<Lesson> listOfLessons) {
//	ListOfLessons = listOfLessons;
//}
public static long getSerialversionuid() {
	return serialVersionUID;
}

}
