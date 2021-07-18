package cultureLearning.cultureLearning.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cultureLearning.cultureLearning.domain.Address;
import cultureLearning.cultureLearning.exception.CountryNotSelectedException;
import cultureLearning.cultureLearning.exception.DuplicateNid;
@Repository
public class AddressDaoImplementation implements AddressInterface {

	@Autowired
	private SessionFactory session;
	
	public void registerAddress(Address u) {
		// TODO Auto-generated method stub
//		if(findById(u.getCode())==null) {
//			throw new CountryNotSelectedException("Banza Uhitemo Igihugu");
//		}
		 if(findById(u.getCode())!=null) {
			throw new DuplicateNid("Igihugu Gisanzwe kirimo");
		}
		else {
			session.getCurrentSession().save(u);
		}
	
	}

	public void deleteAddress(Address u) {
		// TODO Auto-generated method stub
		//session.getCurrentSession().delete(u);
		session.openSession().beginTransaction();
		session.getCurrentSession().delete(u);
		session.getCurrentSession().getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Address> findAllAddress() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Address").list();
	}

	public void update(Address u) {
		// TODO Auto-generated method stub
		//session.getCurrentSession().update(u);
		session.openSession().beginTransaction();
		session.getCurrentSession().update(u);
		session.getCurrentSession().getTransaction().commit();
		session.close();
		
	}

	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}

	public Address findById(String code) {
		// TODO Auto-generated method stub
		
		try {
			
			/*
			 * Session s=session.openSession(); s.beginTransaction(); Address ad=(Address)
			 * s.get(Address.class, code);
			 * 
			 * return ad;
			 */
			return (Address) session.getCurrentSession().createCriteria(Address.class).add(Restrictions.eq("code", code)).uniqueResult();
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage()) ;
			
		}
		
	}

}
