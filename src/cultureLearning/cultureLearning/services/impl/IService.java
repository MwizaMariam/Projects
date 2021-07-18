package cultureLearning.cultureLearning.services.impl;

import java.io.Serializable;
import java.util.List;

public interface IService<T extends Serializable> {
	T findOne(Class<T> clazz,String id);
	 
	   List<T> findAll(Class<T> clazz);
	 
	   void create( T entity);
	 
	   T update( T entity);
	 
	   void delete( T entity);
	 
	   void deleteById(Class<T> clazz,String entityId);
}
