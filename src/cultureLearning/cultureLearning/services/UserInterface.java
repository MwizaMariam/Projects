package cultureLearning.cultureLearning.services;

import java.util.List;


import cultureLearning.cultureLearning.domain.User;

public interface UserInterface {

	public void registerUser(User u);
	public void deleteUser(User u);
	public List<User>findAllUser();
	public List<User> listOfLearner();
	public List<User> listOfTeacher();
	public void update(User u);	
	
	public User findById(String nid);
	public void forgotPassword( User user,String npassword,String email);
	public User userExist(String email,String password);
	public User updatePassword(String email, String oldPassword, String password, String cpassword);
	public User getByEmail(String email);
	public void search(User user);
}
