package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cultureLearning.cultureLearning.domain.Transaction;
import cultureLearning.cultureLearning.services.TransactionInterface;
@Service("transaction")
public class TransactionServiceImplementation implements TransactionInterfaceServive {
@Autowired
	private TransactionInterface tdao;

@Transactional
@Override
public void saveTransaction(Transaction t) {
	// TODO Auto-generated method stub
	tdao.saveTransaction(t);
}
@Transactional
@Override
public void deleteTransaction(Transaction t) {
	// TODO Auto-generated method stub
	tdao.deleteTransaction(t);
}
@Transactional
@Override
public void updateTransaction(Transaction t) {
	// TODO Auto-generated method stub
	tdao.updateTransaction(t);
}
@Transactional
@Override
public List<Transaction> listofTransaction() {
	// TODO Auto-generated method stub
	return tdao.listofTransaction();
}
@Transactional

@Override
public Transaction getTransactionById(int id) {
	// TODO Auto-generated method stub
	return tdao.getTransactionById(id);
}

}
