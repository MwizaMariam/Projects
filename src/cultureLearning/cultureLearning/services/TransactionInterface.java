package cultureLearning.cultureLearning.services;

import java.util.List;

import cultureLearning.cultureLearning.domain.Transaction;


public interface TransactionInterface {
	public void saveTransaction( Transaction t);
	public void deleteTransaction(Transaction t);
	public void updateTransaction(Transaction t);
	public List<Transaction>listofTransaction();
	public Transaction getTransactionById(int id);
}
