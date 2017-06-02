package model.dao;

import java.util.ArrayList;

import exceptions.MyIOException;
import exceptions.MySQLExseption;
import model.classes.Knife;
import model.classes.Transaction;

public interface IDBFinanceDAO {
	
	public int getTheNumberOfTheLastTransaction() throws MySQLExseption;
	public int getAllMoneyByTheLastTransaction() throws MySQLExseption;
	public void addMoneyToTheShop(int addedMoney) throws MySQLExseption;
	public void getMoneyFromTheShop(int takenMoney) throws MySQLExseption;
	public void updateEventByTransactionId(String event, int transactionId) throws MySQLExseption;
	public int getTheLastTransactionsIdNewMethod() throws MySQLExseption;
	public ArrayList<Transaction> getAllTransactions() throws MySQLExseption;
	public int getTheForlastAllMoney() throws MySQLExseption;
	public int getThePreviousId(int currentId) throws MySQLExseption;
	public int getAllMoneyByTransactionId(int transactionId) throws MySQLExseption;
	public void makeTransactionAddMoneySellKnife(ArrayList<Knife> allKnifesFromTheBasket, int userId) throws MyIOException, MySQLExseption ;
}
