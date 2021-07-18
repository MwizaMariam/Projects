package cultureLearning.cultureLearning.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cultureLearning.cultureLearning.domain.Subscription;
import cultureLearning.cultureLearning.domain.SubscriptionType;
@Repository
public class SubscriptionTypeDao implements SubscriptionTypeInterface {

	@Autowired
	private SessionFactory session;
	@SuppressWarnings("unchecked")
	@Override
	public List<SubscriptionType> listofSubscription() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from SubscriptionType").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubscriptionType> getSubscription() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from SubscriptionType where user='user_userid' && course='course_coursecode' && startdate<=today && enddate<=today").list();
	}

	@Override
	public void saveSubscription(SubscriptionType s) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(s);
	}

	@Override
	public void deleteSubscription(SubscriptionType s) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(s);
	}

	@Override
	public void updateSubscription(SubscriptionType s) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(s);
	}

	@Override
	public SubscriptionType findById(String id) {
		// TODO Auto-generated method stub
		return (SubscriptionType) session.getCurrentSession().get(SubscriptionType.class, id);
		//return (SubscriptionType) session.getCurrentSession().createCriteria(SubscriptionType.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

}
