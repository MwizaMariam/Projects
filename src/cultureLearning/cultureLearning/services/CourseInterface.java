package cultureLearning.cultureLearning.services;

import java.util.List;

import cultureLearning.cultureLearning.domain.Address;
import cultureLearning.cultureLearning.domain.Course;

public interface CourseInterface {
	public void registerCourse(Course u);
	public void deleteCourse(Course u);
	public List<Course>findAllCourse();
	public void update(Course u);
	public Course findById(String id);
	public List getVisits();
	public List <Course> ViewCourse();
	/* public List<Course>listOfCourseType(); */
}
