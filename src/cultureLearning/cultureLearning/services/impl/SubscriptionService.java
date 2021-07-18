package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cultureLearning.cultureLearning.domain.Subscription;

import cultureLearning.cultureLearning.services.SubscriptionInterface;
@Service("sub")
public class SubscriptionService implements SubscriptionServiceInterface {
@Autowired
	private SubscriptionInterface dao;
@Transactional
@Override
public void saveSubscription(Subscription s) {
	// TODO Auto-generated method stub
	dao.saveSubscription(s);
}
@Transactional
@Override
public void deleteSubscription(Subscription s) {
	// TODO Auto-generated method stub
dao.deleteSubscription(s);	
}
@Transactional
@Override
public void updateSubscription(Subscription s) {
	// TODO Auto-generated method stub
	dao.updateSubscription(s);
}
@Transactional
@Override
public List<Subscription> listofSubscription() {
	// TODO Auto-generated method stub
	return dao.listofSubscription();
}
@Transactional
@Override
public List<Subscription> getSubscription() {
	// TODO Auto-generated method stub
	return dao.getSubscription();
}
@Transactional
@Override
public Subscription findById(String id) {
	// TODO Auto-generated method stub
	return dao.findById(id);
}
	
}
