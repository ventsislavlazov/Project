package model.dao;

import java.util.ArrayList;

import exceptions.MYIOException;
import exceptions.MYSQLException;
import model.classes.Knife;
import model.classes.Transaction;

public interface IDBFinanceDAO {
	
	public int getTheNumberOfTheLastTransaction() throws MYSQLException;
	public int getAllMoneyByTheLastTransaction() throws MYSQLException;
	public void addMoneyToTheShop(int addedMoney) throws MYSQLException;
	public void getMoneyFromTheShop(int takenMoney) throws MYSQLException;
	public void updateEventByTransactionId(String event, int transactionId) throws MYSQLException;
	public int getTheLastTransactionsIdNewMethod() throws MYSQLException;
	public ArrayList<Transaction> getAllTransactions() throws MYSQLException;
	public int getTheForlastAllMoney() throws MYSQLException;
	public int getThePreviousId(int currentId) throws MYSQLException;
	public int getAllMoneyByTransactionId(int transactionId) throws MYSQLException;
	public void makeTransactionAddMoneySellKnife(ArrayList<Knife> allKnifesFromTheBasket, int userId) throws MYIOException, MYSQLException ;
}
