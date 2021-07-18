package cultureLearning.cultureLearning.services;

import java.util.List;

import cultureLearning.cultureLearning.domain.Subscription;
import cultureLearning.cultureLearning.domain.SubscriptionType;

public interface SubscriptionTypeInterface {
public void saveSubscription( SubscriptionType s);
public void deleteSubscription(SubscriptionType s);
public void updateSubscription(SubscriptionType s);
public List<SubscriptionType>listofSubscription();
public List<SubscriptionType>getSubscription();
public SubscriptionType findById(String id);
}
