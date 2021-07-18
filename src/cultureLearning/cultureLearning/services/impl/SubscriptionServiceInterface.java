package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.Subscription;

public interface SubscriptionServiceInterface {
	public void saveSubscription( Subscription s);
	public void deleteSubscription(Subscription s);
	public void updateSubscription(Subscription s);
	public List<Subscription>listofSubscription();
	public List<Subscription>getSubscription();
	public Subscription findById(String id);
}
