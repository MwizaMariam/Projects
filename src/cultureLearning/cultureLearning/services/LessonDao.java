package cultureLearning.cultureLearning.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.Executions;

import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.Lesson;
import cultureLearning.cultureLearning.services.impl.CourseServiceImplementation;

@Repository
public class LessonDao implements LessonInterface {

	@Autowired
	private SessionFactory session;
	private CourseServiceImplementation cs;
	
	public void registerLesson(Lesson u) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(u);
	}

	public void deleteLesson(Lesson u) {
		// TODO Auto-generated method stub
session.getCurrentSession().delete(u);
		
	}

	@SuppressWarnings("unchecked")
	public List<Lesson> findAllLesson() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Lesson").list();
	}

	public void update(Lesson u) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(u);
	}

	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}

	public Lesson findById(String id) {
		// TODO Auto-generated method stub

		try {
			// return (Lesson) session.getCurrentSession().get(Lesson.class, id);
			return (Lesson) session.getCurrentSession().createCriteria(Lesson.class).add(Restrictions.eq("id", id))
					.uniqueResult();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());

		}

	}

	@SuppressWarnings("unchecked")
	public List<Lesson> ViewCourse() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("FROM Course l ORDER BY l.visit DESC").list();
	}

	@Override
	public void deleteLessonById(String code) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(code);

	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Lesson> findLessonByChapter() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L1' ").list();
//				
        // Query student information based on the student's name

// define hql statement
                // Query student information based on the student's name

//		
//		  try{
//		  
//		   String query="select lessonContent from lesson c where c.course.chapter= ? "; 
//		   
//		return  (Course) session.getCurrentSession().createQuery(query).setParameter(
//		  0, chapter).uniqueResult();
//		  
//		  //session.getCurrentSession().createCriteria(Lesson.class,"lesson");
//		  //criteria.createAlias("lesson.Course", "course");
//		  //criteria.add(Restrictions.eq("course", course)); return criteria.list(); }
//		  }
//		  catch(Exception e)
//		  
//		  { 
//		  e.printStackTrace();}
//		  return null;
//		 
	//}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Lesson> ingombajwi() {
		// TODO Auto-generated method stub
		String id = Executions.getCurrent().getParameter("id");
		System.out.println("Id: "+id+"from Lesson  where course_coursecode='"+id+"' " );
		 return session.getCurrentSession().createQuery("from Lesson where course_coursecode=:coursecode ").setParameter("coursecode", id+"").list();
	}

	
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Lesson> itondeYinyugutiZikinyarwanda() {
	 * 
	 * String id = Executions.getCurrent().getParameter("id");
	 * System.out.println("Id: "+id+"from Lesson  where course_coursecode='"+id+"' "
	 * ); return session.getCurrentSession().
	 * createQuery("from Lesson  where course_coursecode= 'L3' ").list(); }
	 */
	 
//	@Override
//	public List<Lesson> ibihekane() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L5' ").list();
//	}
//
//	@Override
//	public List<Lesson> inshinga() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L6' ").list();
//	}
//
//	@Override
//	public List<Lesson> interuro() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L7' ").list();
//	}
//
//	@Override
//	public List<Lesson> indamukanyo() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L8' ").list();
//	}
//
//	@Override
//	public List<Lesson> amakuru() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L9' ").list();
//	}
//
//	@Override
//	public List<Lesson> Kwivuga() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L10' ").list();
//	}
//
//	@Override
//	public List<Lesson> Kuyoboza() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L11' ").list();
//	}
//
//	@Override
//	public List<Lesson> gusabaUbufasha() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L12' ").list();
//	}
//
//	@Override
//	public List<Lesson> amasano() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L13' ").list();
//	}
//
//	@Override
//	public List<Lesson> ibinyazina() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L14' ").list();
//	}
//
//	@Override
//	public List<Lesson> ibiceByumubiri() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L15' ").list();
//	}
//
//	@Override
//	public List<Lesson> ibiribwa() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L16' ").list();
//	}
//
//	@Override
//	public List<Lesson> kubara() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L7' ").list();
//	}
//
//	@Override
//	public List<Lesson> iminsiyicyumweru() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L18' ").list();
//	}
//
//	@Override
//	public List<Lesson> amasaha() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L19' ").list();
//	}
//
//	@Override
//	public List<Lesson> imyambaro() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L20' ").list();
//	}
//
//	@Override
//	public List<Lesson> inyamaswa() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L21' ").list();
//	}
//
//	@Override
//	public List<Lesson> imyuga() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L22' ").list();
//	}
//
//	@Override
//	public List<Lesson> urugwiroNubutabazi() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L23' ").list();
//	}
//
//	@Override
//	public List<Lesson> imyadagaduro() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L24' ").list();
//	}
//
//	@Override
//	public List<Lesson> murugo() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L24' ").list();
//	}
//
//	@Override
//	public List<Lesson> mubiro() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L25' ").list();
//	}
//
//	@Override
//	public List<Lesson> urugendo() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L26' ").list();
//	}
//
//	@Override
//	public List<Lesson> guhaha() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L27' ").list();
//	}
//
//	@Override
//	public List<Lesson> gutumiraAbantu() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L28' ").list();
//	}
//
//	@Override
//	public List<Lesson> intekozamazina() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L29' ").list();
//	}
//
//	@Override
//	public List<Lesson> guteka() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L30' ").list();
//	}
//
//	@Override
//	public List<Lesson> ibikoreshoByomugikoni() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L31' ").list();
//	}
//
//	@Override
//	public List<Lesson> ibyungo() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L32' ").list();
//	}
//
//	@Override
//	public List<Lesson> amagamboAbaza() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L33' ").list();
//	}
//
//	@Override
//	public List<Lesson> ibikoreshoByubwubatsi() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L34' ").list();
//	}
//
//	@Override
//	public List<Lesson> ntera() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L35' ").list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Lesson> inshingano() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L36' ").list();
//	}
//
//	@Override
//	public List<Lesson> amazinaYamabara() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L37' ").list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Lesson> ibiheByoMurwanda() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L38' ").list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Lesson> ubuyobozi() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L39' ").list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Lesson> gutwaraAbantuNibintu() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L40' ").list();
//	}
//
//	@Override
//	public List<Lesson> ingiro() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L41' ").list();
//	}
//
//	@Override
//	public List<Lesson> indango() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L42' ").list();
//	}
//
//	@Override
//	public List<Lesson> ubumweNubwinshi() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L43' ").list();
//	}
//
//	@Override
//	public List<Lesson> ikigombero() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L44' ").list();
//	}
//
//	@Override
//	public List<Lesson> imvugoIziguyeNitaziguye() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L45' ").list();
//	}
//
//	@Override
//	public List<Lesson> inzegoZubutegetsiMuRwanda() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L46' ").list();
//	}
//
//	@Override
//	public List<Lesson> ibyoUkundaNibyoUdakunda() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L47' ").list();
//	}
//
//	@Override
//	public List<Lesson> KubaraInkuru() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L48' ").list();
//	}
//
//	@Override
//	public List<Lesson> amarangamutima() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L49' ").list();
//	}
//
//	@Override
//	public List<Lesson> ingera() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L50' ").list();
//	}
//
//	@Override
//	public List<Lesson> inyigana() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L51' ").list();
//	}
//
//	@Override
//	public List<Lesson> impuzanyito() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L52' ").list();
//	}
//
//	@Override
//	public List<Lesson> imbusane() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L53' ").list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Lesson> ibaruwa() {
//		// TODO Auto-generated method stub
//		return session.getCurrentSession().createQuery("from Lesson  where course_coursecode= 'L54' ").list();
//	}
//	
	
}
