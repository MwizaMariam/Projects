package cultureLearning.cultureLearning.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cultureLearning.cultureLearning.domain.Transaction;
@Repository
public class TransactionDao implements TransactionInterface {

	@Autowired
	private SessionFactory session;
	@Override
	public void saveTransaction(Transaction t) {
		// TODO Auto-generated method stub
session.getCurrentSession().save(t);
	}

	@Override
	public void deleteTransaction(Transaction t) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(t);
	}

	@Override
	public void updateTransaction(Transaction t) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> listofTransaction() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Transaction").list();
	}

	@Override
	public Transaction getTransactionById(int id) {
		// TODO Auto-generated method stub
		return (Transaction) session.getCurrentSession().get(Transaction.class, id);
	}

}
