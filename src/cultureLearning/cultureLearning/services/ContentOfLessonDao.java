package cultureLearning.cultureLearning.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import cultureLearning.cultureLearning.domain.ContentOfLesson;
@Repository
public class ContentOfLessonDao implements ContentOflessonInterface {
	@Autowired
	private SessionFactory session;
	
	public void createContent(ContentOfLesson c) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(c);
	}

	public void deleteContent(ContentOfLesson c) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(c);;
	}

	public void updateContent(ContentOfLesson c) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(c);;
	}

	@SuppressWarnings("unchecked")
	public List<ContentOfLesson> findAllContent() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery(" from ContentOfLesson").list();
	}

	public ContentOfLesson findById(int id) {
		// TODO Auto-generated method stub
		return (ContentOfLesson)session.getCurrentSession().createCriteria(ContentOfLesson.class).add(Restrictions.eq("id", id) ).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<ContentOfLesson> findImiganiMigufi() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from ContentOfLesson where lesson_id='15' ").list();
	}

	@SuppressWarnings("unchecked")
	public List<ContentOfLesson> findImiganiMiremire() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from ContentOfLesson where lesson_id='8' ").list();
	}

	@SuppressWarnings("unchecked")
	public List<ContentOfLesson> findIbisakuzo() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from ContentOfLesson where lesson_id='42' ").list();
	}
}
