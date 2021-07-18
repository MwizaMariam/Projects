package cultureLearning.cultureLearning.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import cultureLearning.cultureLearning.domain.Teacher;
import cultureLearning.cultureLearning.services.TeacherInterface;
@Service("tservice")
public class TeacherServiceImplementation implements TeacherInterface,Initiator {
	@Autowired
private TeacherInterface ti;
	@Override
	@Transactional
	public void registerTeacher(Teacher u) {
		// TODO Auto-generated method stub
ti.registerTeacher(u);
	}

	@Override
	@Transactional
	public void deleteTeacher(Teacher u) {
		// TODO Auto-generated method stub
		ti.deleteTeacher(u);

	}

	@Override
	@Transactional
	public List<Teacher> findAllTeacher() {
		// TODO Auto-generated method stub
		return ti.findAllTeacher();
	}

	@Override
	@Transactional
	public List<Teacher> listOfLearner() {
		// TODO Auto-generated method stub
		return ti.listOfLearner();
	}

	@Override
	@Transactional
	public void update(Teacher u) {
		// TODO Auto-generated method stub
		ti.update(u);

	}

	@Override
	@Transactional

	public Teacher findById(String nid) {
		// TODO Auto-generated method stub
		return ti.findById(nid);
	}

	@Override
	@Transactional
	public void forgotPassword(Teacher Teacher, String npassword, String email) {
		// TODO Auto-generated method stub
ti.forgotPassword(Teacher, npassword, email);
	}

	@Override
	@Transactional
	public Teacher TeacherExist(String email, String password) {
		// TODO Auto-generated method stub
		return ti.TeacherExist(email, password);
	}

	@Override
	@Transactional
	public Teacher updatePassword(String email, String oldPassword, String password, String cpassword) {
		// TODO Auto-generated method stub
		return ti.updatePassword(email, oldPassword, password, cpassword);
	}

	@Override
	@Transactional
	public Teacher getByEmail(String email) {
		// TODO Auto-generated method stub
		return ti.getByEmail(email);
	}

	@Override
	@Transactional
	public void search(Teacher Teacher) {
		// TODO Auto-generated method stub
		ti.search(Teacher);
	}

	public TeacherInterface getTi() {
		return ti;
	}

	public void setTi(TeacherInterface ti) {
		this.ti = ti;
	}

	@Override
	public void doInit(Page arg0, Map<String, Object> arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
