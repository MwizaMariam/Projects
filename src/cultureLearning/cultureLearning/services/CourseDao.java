package cultureLearning.cultureLearning.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.util.Clients;

import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.Lesson;
@Repository
public class CourseDao implements CourseInterface {

	
	@Autowired
	private SessionFactory session;
	
	
	public void registerCourse(Course u) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(u);
		
	}

	
	public void deleteCourse(Course u) {
		session.getCurrentSession().delete(u);;
		
	}

	@SuppressWarnings("unchecked")
	
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("FROM Course").list();
		//return null;
	}

	
	public void update(Course u) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(u);

	}

	
	public Course findById(String id) {
		// TODO Auto-generated method stub
		try {
			//return (Lesson) session.getCurrentSession().get(Lesson.class, id);
			return (Course) session.getCurrentSession().createCriteria(Course.class).add(Restrictions.eq("id", id)).uniqueResult();
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage()) ;
			
		}
		
	}

	
	@SuppressWarnings("rawtypes")
	public List getVisits() {
		SQLQuery q = session.getCurrentSession().createSQLQuery("SELECT chapter, visit FROM  course ORDER BY visit DESC as tempcourses ");
		//"(SELECT title, visit FROM courses WHERE TYPE='QuestionBased') UNION (SELECT title, visit FROM lesson WHERE basetype = 'ContentBased') ORDER BY visit DESC");
		q.addScalar("chapter", StringType.INSTANCE);
		q.addScalar("visit", IntegerType.INSTANCE);
		//return q.list();
		return new ArrayList<>();
	
	}

	@SuppressWarnings("unchecked")

	public List<Course> ViewCourse() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("FROM Course l ORDER BY l.visit DESC").list();
	}


	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Course> listOfCourseType() { // TODO Auto-generated
	 * method stub return session.getCurrentSession().
	 * createQuery("from Course where accounttype='BEGINNER'").list(); }
	 */

}