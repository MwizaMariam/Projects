package cultureLearning.cultureLearning.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cultureLearning.cultureLearning.domain.Subscription;

@Repository
public class SubscriptionDao  implements SubscriptionInterface{
@Autowired
	private SessionFactory session;
	
	
	@Override
	public void saveSubscription(Subscription s) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(s);
	}

	@Override
	public void deleteSubscription(Subscription s) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(s);;
	}

	@Override
	public void updateSubscription(Subscription s) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(s);
	}

	@Override
	public List<Subscription> listofSubscription() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Subscription").list();
	}

	@Override
	public List<Subscription> getSubscription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscription findById(String id) {
		// TODO Auto-generated method stub
		 return (Subscription) session.getCurrentSession().createCriteria(Subscription.class).add(Restrictions.eq("id", id)).uniqueResult();
		
	}

}
