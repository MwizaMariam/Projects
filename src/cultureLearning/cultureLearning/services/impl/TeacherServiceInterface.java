package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.User;

public interface TeacherServiceInterface {
	public void registerUser(User u);
	public void deleteUser(User u);
	public List<User>findAllUser();
	public void update(User u);	
	public User findById(String  nid);
	public List<User> listOfLearner();
}
