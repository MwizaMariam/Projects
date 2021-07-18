package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.SubscriptionType;


public interface SubscriptionTypeServiceInterface {
	public void saveSubscriptionType( SubscriptionType s);
	public void deleteSubscriptionType(SubscriptionType s);
	public void updateSubscriptionType(SubscriptionType s);
	public List<SubscriptionType>listofSubscriptionType();
	public List<SubscriptionType>getSubscriptionType();
	public SubscriptionType findById(String id);
}
