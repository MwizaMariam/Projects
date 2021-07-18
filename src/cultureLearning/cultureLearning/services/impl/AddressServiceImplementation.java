package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cultureLearning.cultureLearning.domain.Address;
import cultureLearning.cultureLearning.services.AddressInterface;
import cultureLearning.cultureLearning.services.AddressDaoImplementation;
@Service("address")
public class AddressServiceImplementation implements AddressServiceInterface {
	
	@Autowired
	private AddressDaoImplementation dao;
	@Transactional
	public void registerAddress(Address u) {
		// TODO Auto-generated method stub
		dao.registerAddress(u);
	}
	@Transactional
	
	public void deleteAddress(Address u) {
		// TODO Auto-generated method stub
		dao.deleteAddress(u);
	}
@Transactional
	public List<Address> findAllAddress() {
		// TODO Auto-generated method stub
		return dao.findAllAddress();
	}
@Transactional
	public void update(Address u) {
		// TODO Auto-generated method stub
			dao.update(u);
	}

@Transactional
public Address findById(String code) {
	// TODO Auto-generated method stub
	System.out.println("kpko");
	return dao.findById(code);
	
}
public AddressInterface getDao() {
	return dao;
}
public void setDao(AddressDaoImplementation dao) {
	this.dao = dao;
}




}

