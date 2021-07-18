package cultureLearning.cultureLearning.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cultureLearning.cultureLearning.domain.Language;
@Repository
public class LanguageDao implements Languageinterface {

	@Autowired
	private SessionFactory session;
	
	public void registerLanguage(Language u) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(u);
	}

	
	public void deleteLanguage(Language u) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(u);
	}

	
	@SuppressWarnings("unchecked")
	public List<Language> findAllLanguage() {
		// TODO Auto-generated method stub
		return session.openSession().createQuery(" from Language").list();
	}

	
	public void update(Language u) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(u);;
	}

	
	public Language findById(String id) {
		// TODO Auto-generated method stub
		return (Language) session.getCurrentSession().createCriteria(Language.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	
	public List<Language> ViewCourse() {
		// TODO Auto-generated method stub
		return null;
	}

}
