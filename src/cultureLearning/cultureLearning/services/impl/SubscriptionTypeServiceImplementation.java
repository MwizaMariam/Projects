package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cultureLearning.cultureLearning.domain.SubscriptionType;
import cultureLearning.cultureLearning.services.SubscriptionTypeInterface;

@Service("SubscriptionType")
public class SubscriptionTypeServiceImplementation implements SubscriptionTypeServiceInterface {
	@Autowired
	private SubscriptionTypeInterface dao;

	@Transactional
	@Override
	public void saveSubscriptionType(SubscriptionType s) {
		// TODO Auto-generated method stub
		dao.saveSubscription(s);
	}

	@Transactional
	@Override
	public void deleteSubscriptionType(SubscriptionType s) {
		// TODO Auto-generated method stub
		dao.deleteSubscription(s);
	}

	@Transactional
	@Override
	public void updateSubscriptionType(SubscriptionType s) {
		// TODO Auto-generated method stub
		dao.updateSubscription(s);
	}

	@Transactional
	@Override
	public List<SubscriptionType> listofSubscriptionType() {
		// TODO Auto-generated method stub
		return dao.listofSubscription();
	}

	@Transactional
	@Override
	public List<SubscriptionType> getSubscriptionType() {
		// TODO Auto-generated method stub
		return dao.getSubscription();
	}

	@Transactional
	@Override
	public SubscriptionType findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

}
