package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.services.CourseInterface;
import cultureLearning.cultureLearning.services.CourseDao;
@Service("Course")
public class CourseServiceImplementation implements CourseServiceInterface {
	
	@Autowired
	private CourseDao dao;
	@Transactional
	public void registerCourse(Course u) {
		// TODO Auto-generated method stub
dao.registerCourse(u);
	}
	@Transactional
	
	public void deleteCourse(Course u) {
		// TODO Auto-generated method stub
		dao.deleteCourse(u);
	}
@Transactional
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return dao.findAllCourse();
	}
@Transactional
	public void update(Course u) {
		// TODO Auto-generated method stub
dao.update(u);
	}

@Transactional
public Course findById(String id) {
	// TODO Auto-generated method stub
	return dao.findById(id);
	
}
public CourseInterface getDao() {
	return dao;
}
public void setDao(CourseDao dao) {
	this.dao = dao;
}


@SuppressWarnings("rawtypes")
@Transactional 
public List getVisits() {
	return dao.getVisits();
}
@Override
@Transactional
public List<Course> viewCourse() {
	// TODO Auto-generated method stub
	return dao.ViewCourse();
}
	/*
	 * @Override
	 * 
	 * @Transactional public List<Course> listOfCourseType() { // TODO
	 * Auto-generated method stub return dao.listOfCourseType(); }
	 */

}

