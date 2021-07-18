package cultureLearning.cultureLearning.services.impl;

import java.util.List;

public interface GenericInterface <T> {
	T findOne( Class<T>  clazz,String id);
	 
	   List<T> findAll(Class<T> clazz);
	 
	   void create( T entity);
	 
	   T update( T entity);
	 
	   void delete( T entity);
	 
	   void deleteById(Class<T> clazz,String entityId);
}
