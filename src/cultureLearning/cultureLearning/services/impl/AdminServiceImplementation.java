package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cultureLearning.cultureLearning.domain.Admin;

import cultureLearning.cultureLearning.services.AdminInterface;
@Service("admin")
public class AdminServiceImplementation implements AdminServiceInterface {

	@Autowired
	private AdminInterface sdao;
	
	
	
	@Override
	@Transactional
	public void registerAdmin(Admin u) {
		// TODO Auto-generated method stub
		sdao.registerAdmin(u);
	}

	@Override
	@Transactional
	public void deleteAdmin(Admin u) {
		// TODO Auto-generated method stub
		sdao.deleteAdmin(u);
	}

	@Override
	@Transactional
	public List<Admin> findAllAdmin() {
		// TODO Auto-generated method stub
		return sdao.findAllAdmin();
	}

	@Override
	@Transactional
	public List<Admin> listOfLearner() {
		// TODO Auto-generated method stub
		return sdao.listOfLearner();
	}

	@Override
	@Transactional
	public void update(Admin u) {
		// TODO Auto-generated method stub
		sdao.update(u);
	}

	@Override
	@Transactional
	public Admin findById(String nid) {
		// TODO Auto-generated method stub
		return sdao.findById(nid);
	}

	@Override
	@Transactional
	public void forgotPassword(Admin Admin, String npassword, String email) {
		// TODO Auto-generated method stub
		sdao.forgotPassword(Admin, npassword, email);
	}

	@Override
	@Transactional
	public Admin AdminExist(String email, String password) {
		// TODO Auto-generated method stub
		return sdao.AdminExist(email, password);
	}

	@Override
	@Transactional
	public Admin updatePassword(String email, String oldPassword, String password, String cpassword) {
		// TODO Auto-generated method stub
		return sdao.updatePassword(email, oldPassword, password, cpassword);
	}

	@Override
	@Transactional
	public Admin getByEmail(String email) {
		// TODO Auto-generated method stub
		return sdao.getByEmail(email);
	}

	@Override
	@Transactional
	public void search(Admin Admin) {
		// TODO Auto-generated method stub
		sdao.search(Admin);
	}

	public AdminInterface getSdao() {
		return sdao;
	}

	public void setSdao(AdminInterface sdao) {
		this.sdao = sdao;
	}
	

}
