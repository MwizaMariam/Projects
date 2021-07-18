package cultureLearning.cultureLearning.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.Executions;

import cultureLearning.cultureLearning.domain.Student;
import cultureLearning.cultureLearning.domain.Student;
import cultureLearning.cultureLearning.domain.Student;
import cultureLearning.cultureLearning.domain.User;
import cultureLearning.cultureLearning.domain.Student;
import cultureLearning.cultureLearning.exception.DuplicateNid;
import cultureLearning.cultureLearning.exception.InvalidMailException;
import cultureLearning.cultureLearning.exception.PasswordNotMatch;
import cultureLearning.cultureLearning.services.impl.PasswordProtection;
@Repository
public class StudentDao implements StudentInterfaceDao {
@Autowired
	private SessionFactory session;
private String npassword;
private String email;
@Override
public void registerStudent(Student u) {
	// TODO Auto-generated method stub
	if (getByEmail(u.getEmail())!=null) {
		throw new DuplicateNid("Imeri isanzwe ibamo,koresha iyindi");
	}
	
	
	else if(PasswordProtection.verifyPassword(u.getCpassword(), u.getPassword())) {
		System.out.println(u.getCpassword());
		System.out.println(u.getPassword());
		session.getCurrentSession().save(u);
	}
	else {
		throw new PasswordNotMatch("Ijambo ry'ibanga ntabwo risa,ongera ugerageze ");
	}
	
}

@Override
public void deleteStudent(Student u) {
	// TODO Auto-generated method stub
	session.getCurrentSession().delete(u);
}

@SuppressWarnings("unchecked")
@Override
public List<Student> findAllStudent() {
	// TODO Auto-generated method stub
	return session.openSession().createQuery("from Student").list();
}

@SuppressWarnings("unchecked")
@Override
public List<Student> listOfLearner() {
	// TODO Auto-generated method stub
	return session.getCurrentSession().createQuery("from Student").list();
}

@Override
public void update(Student u) {
	// TODO Auto-generated method stub
	session.getCurrentSession().update(u);
}

@Override
public Student findById(String nid) {
	// TODO Auto-generated method stub
	return (Student) session.getCurrentSession().createCriteria(Student.class).add(Restrictions.eq("nid", nid)).uniqueResult();
}

@Override
public void forgotPassword(Student Student, String npassword, String email) {
	// TODO Auto-generated method stub
try {
			
		
		if(getByEmail(Student.getEmail())!=null) {
				
			
			String sql="update Student set password=:npassword where email=:email";
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
		 

		}catch (Exception e) {
					e.getMessage();
			// TODO: handle exception
			e.printStackTrace();
		}
		
}

@Override
public Student StudentExist(String email, String password) {
	// TODO Auto-generated method stub
	Student student = (Student) session.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
	if(student==null) throw new InvalidMailException("Imeri yawe ntiyanditse neza");
	if (PasswordProtection.verifyPassword(password, student.getPassword())) {
		return student;
	} else {
		throw new InvalidMailException("Wanditse nabi ijambo ry'ibanga");
	}
	
}

@Override
public Student updatePassword(String email, String oldPassword, String password, String cpassword) {
	// TODO Auto-generated method stub
	Student t=getByEmail(email);
	System.out.println("NNNN: "+oldPassword+" "+password+" "+cpassword);
if(t!=null) {
	if(PasswordProtection.verifyPassword(oldPassword,t.getPassword())) {
			
	
	
	if(password.equals(cpassword)) {
		System.out.println(password);
		t.setPassword(PasswordProtection.hashPassword(password));
		session.getCurrentSession().update(t);
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
return t;
}

@Override
public Student getByEmail(String email) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void search(Student student) {
	// TODO Auto-generated method stub
	if(getByEmail(student.getEmail())!=null) {
		Executions.sendRedirect("/pages/users/ForgetPassword.zul");
	}
	else {
		throw new InvalidMailException("Imeri ntibaho!Shyiramo iyindi");
	}
}

public SessionFactory getSession() {
	return session;
}

public void setSession(SessionFactory session) {
	this.session = session;
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


	
}
