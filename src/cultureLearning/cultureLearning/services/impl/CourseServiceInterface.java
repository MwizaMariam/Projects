package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.Course;

public interface CourseServiceInterface {
	public void registerCourse(Course u);
	public void deleteCourse(Course u);
	public List<Course>findAllCourse();
	public void update(Course u);
	public Course findById(String id);
	public List getVisits();
	public List<Course> viewCourse();
	/* public List<Course> listOfCourseType(); */
}
