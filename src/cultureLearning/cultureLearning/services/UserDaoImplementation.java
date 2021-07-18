package cultureLearning.cultureLearning.services;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.Executions;
import cultureLearning.cultureLearning.domain.User;
import cultureLearning.cultureLearning.exception.DuplicateNid;
import cultureLearning.cultureLearning.exception.InvalidMailException;
import cultureLearning.cultureLearning.exception.PasswordNotMatch;
import cultureLearning.cultureLearning.services.impl.PasswordProtection;

@Repository
public class UserDaoImplementation implements UserInterface {
	@Autowired
	private SessionFactory session;
	private String npassword;
	private String email;
	public void registerUser(User u) {
		/*if(findById(u.getNid())!=null) {
			throw new DuplicateNid("Nid arleady exist");
		}*/
		 if (getByEmail(u.getEmail())!=null) {
			throw new DuplicateNid("Imeri isanzwe ibamo,koresha iyindi");
		}
		
		else if(PasswordProtection.verifyPassword(u.getCpassword(), u.getPassword())) {
			session.getCurrentSession().save(u);
		}
		else {
			throw new PasswordNotMatch("Ijambo ry'ibanga ntabwo risa,ongera ugerageze ");
		}
		
	}
	
	
	
	public User userExist(String email, String password) {
		User user = (User) session.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
		if(user==null) throw new InvalidMailException("Imeri yawe ntiyanditse neza");
		if (PasswordProtection.verifyPassword(password, user.getPassword())) {
			return user;
		} else {
			throw new InvalidMailException("Wanditse nabi ijambo ry'ibanga");
		}
	}
	
	
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
session.getCurrentSession().delete(u);
	}
@SuppressWarnings("unchecked")
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from User").list();
	}

	public void update(User u) {
		// TODO Auto-generated method stub
session.getCurrentSession().update(u);
	}

	public SessionFactory getSession() {
		return session;
	}
	

	public void setSession(SessionFactory session) {
		this.session = session;
	}

	public User findById(String nid) {
		
		Session s=session.openSession();
		s.beginTransaction();
		User p=(User) s.get(User.class, nid);
		return p;
	}
	public void forgotPassword(User user,String npassword,String email) {
		try {
			
			if(getByEmail(user.getEmail())!=null) {
				
			
			String sql="update User set password=:npassword where email=:email";
			Query query=session.getCurrentSession().createQuery(sql);
			query.setParameter("npassword", PasswordProtection.hashPassword(npassword));
			query.setParameter("email", email);
				
			System.out.println(email);
			System.out.println(npassword);
				 
			query.executeUpdate();
				
			}
			else {
				throw new InvalidMailException("email doesn't exist");
			}
		} 
		
		
			catch (Exception e) {
					e.getMessage();
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}
	public void search(User user) {
		if(getByEmail(user.getEmail())!=null) {
			Executions.sendRedirect("/pages/users/ForgetPassword.zul");
		}
		else {
			throw new InvalidMailException("Imeri ntibaho!Shyiramo iyindi");
		}
	}

	public User updatePassword(String email,  String oldPassword,String password, String cpassword) {
		
		User user=getByEmail(email);
			System.out.println("NNNN: "+oldPassword+" "+password+" "+cpassword);
		if(user!=null) {
			if(PasswordProtection.verifyPassword(oldPassword,user.getPassword())) {
					
			
			
			if(password.equals(cpassword)) {
				System.out.println(password);
				user.setPassword(PasswordProtection.hashPassword(password));
				session.getCurrentSession().update(user);
			}
			else {
				throw new InvalidMailException("Amagambo y'ibanga ntabwo asa!");
			}
			
		}
			else {
				throw new InvalidMailException("Ijambo ryawe ry'inbanga ntabwo ari ryo");
			}
	} else {
		throw new InvalidMailException("iyi konti ntibaho");
	}
		return user;
	}



	public User getByEmail(String email) {
	
	return (User) session.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
		
	}


	@SuppressWarnings("unchecked")
	public List<User> listOfLearner(){
	return 	session.getCurrentSession().createQuery("from Student").list();
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



	@SuppressWarnings("unchecked")
	@Override
	public List<User> listOfTeacher() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery(" from Teacher").list();
	}



	
	
	
}



