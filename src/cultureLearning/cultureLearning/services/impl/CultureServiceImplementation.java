package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cultureLearning.cultureLearning.domain.Culture;
import cultureLearning.cultureLearning.services.CultureInterface;
@Service("culture")
public class CultureServiceImplementation implements CultureInterfaceService {
	@Autowired
	private CultureInterface culture;
	
	@Override
	@Transactional
	public void registerCulture(Culture u) {
		// TODO Auto-generated method stub
			culture.registerCulture(u);
	}

	@Override
	@Transactional
	public void deleteCulture(Culture u) {
		// TODO Auto-generated method stub
		culture.deleteCulture(u);
	}

	@Override
	@Transactional
	public List<Culture> findAllCulture() {
		// TODO Auto-generated method stub
		return culture.findAllCulture();
	}

	@Override
	@Transactional
	public void update(Culture u) {
		// TODO Auto-generated method stub
		culture.update(u);
	}

	@Override
	@Transactional
	public Culture findById(String id) {
		// TODO Auto-generated method stub
		return culture.findById(id);
	}

	@Override
	@Transactional
	public List<Culture> ViewCourse() {
		// TODO Auto-generated method stub
		return null;
	}

}
