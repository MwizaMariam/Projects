package cultureLearning.cultureLearning.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cultureLearning.cultureLearning.domain.Culture;
import cultureLearning.cultureLearning.domain.Culture;

@Repository
public class CultureDao implements CultureInterface {

	@Autowired
	private SessionFactory session;
	@Override
	public void registerCulture(Culture u) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(u);
	}

	@Override
	public void deleteCulture(Culture u) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(u);;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Culture> findAllCulture() {
		// TODO Auto-generated method stub
		return session.openSession().createQuery(" from Culture").list();
	}

	@Override
	public void update(Culture u) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(u);;
	}

	@Override
	public Culture findById(String id) {
		// TODO Auto-generated method stub
		return (Culture) session.getCurrentSession().createCriteria(Culture.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	@Override
	public List<Culture> ViewCourse() {
		// TODO Auto-generated method stub
		return null;
	}

}
