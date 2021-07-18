package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.Address;
import cultureLearning.cultureLearning.domain.User;

public interface UserServiceInterface {
	public void registerUser(User u);
	public void deleteUser(User u);
	public List<User>findAllUser();
	public void update(User u);	
	public User findById(String  nid);
	public List<User> listOfLearner();
	public List<User> listOfTeacher();
	public void activateTrail(User user);
	public boolean canViewCourse(User user);
	public boolean canDownload(User user);
}
