package cultureLearning.cultureLearning.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cultureLearning.cultureLearning.services.IGenericDao;
import cultureLearning.cultureLearning.services.IStoryGenericDao;
@Service("story")
public class GenericStoryService <T extends Serializable>implements IStoryGenericDao<T> {
@Autowired
	private IGenericDao<T>dao;
	@Transactional
	public T findOne(Class<T> clazz, String id) {
		// TODO Auto-generated method stub
		return dao.findOne(clazz, id);
	}
	@Transactional
	public List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return dao.findAll(clazz);
	}
	@Transactional
	public void create(T entity) {
		// TODO Auto-generated method stub
		dao.create(entity);
	}
	@Transactional
	public T update(T entity) {
		// TODO Auto-generated method stub
		return dao.update(entity);
	}
	@Transactional
	public void delete(T entity) {
		// TODO Auto-generated method stub
		dao.delete(entity);
	}
	@Transactional
	public void deleteById(Class<T> clazz, String entityId) {
		// TODO Auto-generated method stub
		dao.deleteById(clazz, entityId);
	}
	@Transactional
	public List<T> findCulture(Class<T> clazz) {
		// TODO Auto-generated method stub
		return dao.findCulture(clazz);
	}@Transactional
	public List<T> findC(Class<T> clazz) {
		// TODO Auto-generated method stub
		return dao.findC(clazz);
	}@Transactional
	public List<T> findAllCulture(Class<T> clazz) {
		// TODO Auto-generated method stub
		return dao.findAllCulture(clazz);
	}

}
