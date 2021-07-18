package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.Transaction;

public interface TransactionInterfaceServive {
	public void saveTransaction( Transaction t);
	public void deleteTransaction(Transaction t);
	public void updateTransaction(Transaction t);
	public List<Transaction>listofTransaction();
	public Transaction getTransactionById(int id);
}
