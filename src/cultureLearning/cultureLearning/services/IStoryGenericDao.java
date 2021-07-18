package cultureLearning.cultureLearning.services;

import java.io.Serializable;
import java.util.List;

public interface IStoryGenericDao<T extends Serializable> {
	T findOne( Class<T>  clazz,String id);
	 
	   List<T> findAll(Class<T> clazz);
	   List<T>findCulture(Class<T> clazz);
	   List<T>findC(Class<T> clazz);
	   void create( T entity);
	   List<T> findAllCulture(Class<T> clazz);
	 
	   T update( T entity);
	 
	   void delete( T entity);
	 
	   void deleteById(Class<T> clazz,String entityId);
}
