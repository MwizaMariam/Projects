package cultureLearning.cultureLearning.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import cultureLearning.cultureLearning.domain.Student;
import cultureLearning.cultureLearning.services.StudentDao;
import cultureLearning.cultureLearning.services.StudentInterfaceDao;
@Service("student")
public class StudentService  implements StudentServiceInterface{
	@Autowired
	private StudentInterfaceDao sdao;
	
	
	
	@Override
	@Transactional
	public void registerStudent(Student u) {
		// TODO Auto-generated method stub
		sdao.registerStudent(u);
	}

	@Override
	@Transactional
	public void deleteStudent(Student u) {
		// TODO Auto-generated method stub
		sdao.deleteStudent(u);
	}

	@Override
	@Transactional
	public List<Student> findAllStudent() {
		// TODO Auto-generated method stub
		return sdao.findAllStudent();
	}

	@Override
	@Transactional
	public List<Student> listOfLearner() {
		// TODO Auto-generated method stub
		return sdao.listOfLearner();
	}

	@Override
	@Transactional
	public void update(Student u) {
		// TODO Auto-generated method stub
		sdao.update(u);
	}

	@Override
	@Transactional
	public Student findById(String nid) {
		// TODO Auto-generated method stub
		return sdao.findById(nid);
	}

	@Override
	@Transactional
	public void forgotPassword(Student Student, String npassword, String email) {
		// TODO Auto-generated method stub
		sdao.forgotPassword(Student, npassword, email);
	}

	@Override
	@Transactional
	public Student StudentExist(String email, String password) {
		// TODO Auto-generated method stub
		return sdao.StudentExist(email, password);
	}

	@Override
	@Transactional
	public Student updatePassword(String email, String oldPassword, String password, String cpassword) {
		// TODO Auto-generated method stub
		return sdao.updatePassword(email, oldPassword, password, cpassword);
	}

	@Override
	@Transactional
	public Student getByEmail(String email) {
		// TODO Auto-generated method stub
		return sdao.getByEmail(email);
	}

	@Override
	@Transactional
	public void search(Student student) {
		// TODO Auto-generated method stub
		sdao.search(student);
	}

	public StudentInterfaceDao getSdao() {
		return sdao;
	}

	public void setSdao(StudentInterfaceDao sdao) {
		this.sdao = sdao;
	}
	

}
	

