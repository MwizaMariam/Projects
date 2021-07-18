package cultureLearning.cultureLearning.services;

import java.util.List;

import cultureLearning.cultureLearning.domain.Admin;

public interface AdminInterface {
	public void registerAdmin(Admin u);
	public void deleteAdmin(Admin u);
	public List<Admin>findAllAdmin();
	public List<Admin> listOfLearner();
	
	public void update(Admin u);	
	
	public Admin findById(String nid);
	public void forgotPassword( Admin admin,String npassword,String email);
	public Admin AdminExist(String email,String password);
	public Admin updatePassword(String email, String oldPassword, String password, String cpassword);
	public Admin getByEmail(String email);
	public void search(Admin admin);
}
