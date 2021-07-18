package cultureLearning.cultureLearning.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SubscriptionType {
	@Id
	private String subscriptionTypeId;
	private  Double price;
	private String level;
	@ManyToOne
	private User user;
	@OneToOne
	private Transaction transaction;
	@OneToOne
	private Course course;
	@OneToOne
	private Subscription subscription;
	
	
	public String getSubscriptionTypeId() {
		return subscriptionTypeId;
	}
	public void setSubscriptionTypeId(String subscriptionTypeId) {
		this.subscriptionTypeId = subscriptionTypeId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	@Override
	public String toString() {
		return getLevel() ;
	}
	
	
}
