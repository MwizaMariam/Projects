package cultureLearning.cultureLearning.services;

import java.util.List;

import cultureLearning.cultureLearning.domain.Student;

public interface StudentInterfaceDao {
	public void registerStudent(Student u);
	public void deleteStudent(Student u);
	public List<Student>findAllStudent();
	public List<Student> listOfLearner();
	
	public void update(Student u);	
	
	public Student findById(String nid);
	public void forgotPassword( Student Student,String npassword,String email);
	public Student StudentExist(String email,String password);
	public Student updatePassword(String email, String oldPassword, String password, String cpassword);
	public Student getByEmail(String email);
	public void search(Student Student);
}
