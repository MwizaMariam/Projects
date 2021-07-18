package cultureLearning.cultureLearning.services.impl;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Initiator;
import cultureLearning.cultureLearning.domain.User;
import cultureLearning.cultureLearning.services.UserInterface;
@SuppressWarnings("deprecation")
@Service("Service")

public class UserServiceImplementation implements UserServiceInterface,Initiator {
@Autowired
	private UserInterface ui;
private String npassword;
private String email;
	@Transactional
	public void registerUser(User u) {
		// TODO Auto-generated method stub
			ui.registerUser(u);
	}
	@Transactional
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		ui.deleteUser(u);
	}
	@Transactional
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return ui.findAllUser();
	}
	@Transactional
	public void update(User u) {
		// TODO Auto-generated method stub
		ui.update(u);
	}@Transactional
	public User findById(String nid) {
		// TODO Auto-generated method stub
		return ui.findById(nid);
	}
	@Transactional
	public User userEx(String email,String password) {
		return ui.userExist(email, password);
	}
	@Transactional
    public void logout() {
        Session sess = Sessions.getCurrent();
        sess.removeAttribute("userCredential");
        
    }@Transactional
	public void forgetPassword(User user,String npassword,String email) {
    	
		ui.forgotPassword(user,  npassword,email);
    }
    @Transactional
    public void search(User user) {
    	ui.search(user);
    }
	
	 @Transactional
	 public User updatePassword(String email, String oldPassword,String password,String cpassword) {
		return ui.updatePassword(email, oldPassword, password, cpassword);
	 }
	 
	 	@Transactional
	 	public User getByEmail(String email) {
	 	return ui.getByEmail(email);
	 	
	 	
}
	public UserInterface getUi() {
		return ui;
	}
	public void setUi(UserInterface ui) {
		this.ui = ui;
	}
	public String getNpassword() {
		return npassword;
	}
	public void setNpassword(String npassword) {
		this.npassword = npassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void doInit(Page arg0, Map<String, Object> arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Transactional
	public List<User> listOfLearner() {
		// TODO Auto-generated method stub
		return ui.listOfLearner();
	}
	@Override
	public void activateTrail(User user) {
		User u =  ui.findById(user.getNid());
		LocalDate d = LocalDate.now();
		d = d.plusDays(2);
		java.sql.Date expiryDate = java.sql.Date.valueOf(d);
		u.setExpiryDate(expiryDate);
		ui.update(u);
	}
	public boolean isStarted(User user) {
		return user.getExpiryDate()!=null;
	}
	public boolean isExpired(User user) {
		return isStarted(user)&&user.getExpiryDate().before(new java.util.Date());
	}
	@Override
	public boolean canViewCourse(User user) {
		return !isExpired(user);
	}
	@Override
	public boolean canDownload(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	@Transactional
	public List<User> listOfTeacher() {
		// TODO Auto-generated method stub
		return ui.listOfTeacher();
	}
	 
}

