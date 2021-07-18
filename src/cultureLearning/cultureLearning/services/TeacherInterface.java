package cultureLearning.cultureLearning.services;

import java.util.List;

import cultureLearning.cultureLearning.domain.Teacher;

public interface TeacherInterface {
	public void registerTeacher(Teacher u);
	public void deleteTeacher(Teacher u);
	public List<Teacher>findAllTeacher();
	public List<Teacher> listOfLearner();
	
	public void update(Teacher u);	
	
	public Teacher findById(String nid);
	public void forgotPassword( Teacher Teacher,String npassword,String email);
	public Teacher TeacherExist(String email,String password);
	public Teacher updatePassword(String email, String oldPassword, String password, String cpassword);
	public Teacher getByEmail(String email);
	public void search(Teacher Teacher);
}
