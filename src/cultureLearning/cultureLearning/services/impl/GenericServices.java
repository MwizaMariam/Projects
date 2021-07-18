package cultureLearning.cultureLearning.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cultureLearning.cultureLearning.services.IGenericDao;
@Service("general")
public class GenericServices<T extends Serializable> implements GenericInterface<T> {
	@Autowired
	private IGenericDao<T> gen;
	
	@Transactional
	public T findOne(Class<T> clazz, String id) {
		// TODO Auto-generated method stub
		return gen.findOne(clazz, id);
	}
	@Transactional
	public List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return gen.findAll(clazz);
	}
	@Transactional
	public void create(T entity) {
		// TODO Auto-generated method stub
		gen.create(entity);
	}
	@Transactional
	public T update(T entity) {
		// TODO Auto-generated method stub
		return gen.update(entity);
	}
	@Transactional
	public void delete(T entity) {
		// TODO Auto-generated method stub
		gen.delete(entity);
	}
	@Transactional
	public void deleteById(Class<T> clazz, String entityId) {
		// TODO Auto-generated method stub
		gen.deleteById(clazz, entityId);
	}
	public IGenericDao<T> getGen() {
		return gen;
	}
	public void setGen(IGenericDao<T> gen) {
		this.gen = gen;
	}

}
