package cultureLearning.cultureLearning.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDao<T extends Serializable> implements IGenericDao<T> {
 private Class<T>clazz;
 @Autowired
	private SessionFactory session;
	@SuppressWarnings("unchecked")
	public T findOne(Class<T> clazz, String id) {
		// TODO Auto-generated method stub
		return (T) session.getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from " +clazz.getName()).list();
		
	}

	public void create(T entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	public T update(T entity) {
		// TODO Auto-generated method stub
		return (T)session.getCurrentSession().merge(entity);
	}

	public void delete(T entity) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(entity);
		
	}

	public void deleteById(Class<T> clazz,String entityId) {
		// TODO Auto-generated method stub
		T entity=findOne(clazz, entityId);
		delete(entity);
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}

	

	public void deleteById(String entityId) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public List<T> findCulture(Class<T> clazz) {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Story where title='Zimwe muri kirazira z’Umuco Nyarwanda zahinduwe urwenya' ").list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findC(Class<T> clazz) {
		// TODO Auto-generated method stub
		 return session.getCurrentSession().createQuery("from Story where title='Kirazira z’umuco nyarwanda n’isomo zadusigira muri iki gihe' ").list(); 
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllCulture(Class<T> clazz) {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Story where title!='Kirazira z’umuco nyarwanda n’isomo zadusigira muri iki gihe'  and title!='Zimwe muri kirazira z’Umuco Nyarwanda zahinduwe urwenya' and dtype='Culture'")
				.list(); 
	}
	

}
