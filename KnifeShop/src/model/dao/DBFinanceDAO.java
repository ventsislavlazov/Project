package model.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.PartialResultException;

import exceptions.MYIOException;
import exceptions.MYSQLException;
import model.classes.Knife;
import model.classes.Transaction;
import model.db.DBManager;

public class DBFinanceDAO implements IDBFinanceDAO{
	
	 private static DBFinanceDAO instance;
		
	 private DBManager manager;
	 
	 public DBFinanceDAO() {
		 manager = DBManager.getInstance();
	 }
	 
	 public static DBFinanceDAO getInstance(){
		 if(instance == null){
			 instance = new DBFinanceDAO();
		 }
		 return instance;
	 }
	 
	 DBKnifeDAO knifeDAO = new DBKnifeDAO();
	 DBBasketDAO basketDAO = new DBBasketDAO();
	 DBUserDAO userDAO = new DBUserDAO();

	@Override
	public int getTheNumberOfTheLastTransaction() throws MYSQLException {
		int lastTransactionsId = 0;
		String query = "SELECT last_insert_id() AS last_id FROM KnifeShop.Transactions";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()){
				lastTransactionsId = rs.getInt("last_id");
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return lastTransactionsId;
	}

	@Override
	public int getAllMoneyByTheLastTransaction() throws MYSQLException {
		String query = "SELECT allMoney FROM KnifeShop.Transactions WHERE transactionId = ?";
		int allMoney = 0;
		int lastTransactionsId = getTheLastTransactionsIdNewMethod();
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, lastTransactionsId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				allMoney = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allMoney;
	}

	@Override
	public void addMoneyToTheShop(int addedMoney) throws MYSQLException {
		String query = "INSERT INTO KnifeShop.Transactions (allMoney, addedMoney) VALUES (?, true)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, addedMoney);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void getMoneyFromTheShop(int takenMoney) throws MYSQLException {
		String query = "INSERT INTO KnifeShop.Transactions (allMoney, addedMoney) VALUES (?, false)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, takenMoney);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void updateEventByTransactionId(String event, int transactionId) throws MYSQLException {
		String query = "UPDATE KnifeShop.Transactions SET event=? WHERE transactionId = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, event);
			st.setInt(2, transactionId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public int getTheLastTransactionsIdNewMethod() throws MYSQLException {
		String query = "SELECT transactionId FROM KnifeShop.Transactions ORDER BY transactionId DESC LIMIT 1";
		int lastId = 0;
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()){
				lastId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return lastId;
	}

	@Override
	public ArrayList<Transaction> getAllTransactions() throws MYSQLException {
		String query = "SELECT transactionId, allMoney, addedMoney, event FROM KnifeShop.Transactions";
		ArrayList<Transaction> allTransactions = new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int transactionId = rs.getInt(1);
				int allMoney = rs.getInt(2);
				boolean addedMoney = rs.getBoolean(3);
				String event = rs.getString(4);
				int previousId = getThePreviousId(transactionId);
				int allMoneyForThePreviousTransaction = getAllMoneyByTransactionId(previousId);
				int transactedMoney = allMoney-allMoneyForThePreviousTransaction;
				Transaction transaction = new Transaction(transactionId, allMoney, addedMoney, event, transactedMoney);
				allTransactions.add(transaction);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allTransactions;
	}

	@Override
	public int getTheForlastAllMoney() throws MYSQLException {
		String query = "SELECT allMoney FROM KnifeShop.Transactions ORDER BY transactionId DESC LIMIT 1,1";
		int foreLastAllMoney = 0;
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()){
				foreLastAllMoney = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return foreLastAllMoney;
	}

	@Override
	public int getThePreviousId(int currentId) throws MYSQLException {
		String query = "SELECT * FROM KnifeShop.Transactions WHERE transactionId = (SELECT MAX(transactionId) FROM Transactions WHERE transactionId < ?)";
		int previousId = 0;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, currentId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				previousId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return previousId;
	}

	@Override
	public int getAllMoneyByTransactionId(int transactionId) throws MYSQLException {
		String query = "SELECT allMoney FROM KnifeShop.Transactions WHERE transactionId = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, transactionId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return 0;
	}

	@Override
	public void makeTransactionAddMoneySellKnife(ArrayList<Knife> allKnifesFromTheBasket, int userId) throws MYIOException, MYSQLException {
		
		Connection con = manager.getConnection();
		
		try {
			
				
			
			con.setAutoCommit(false);
		
			for(Knife knife : allKnifesFromTheBasket){
				//pri slaganeto na noj w koli4kata namalqwame koli4estwoto mu w magazina
				//knifeDAO.decreezeKnifeQuantityByKnifeId(knife.getId(), knife.getQuantityBasket());
				//uweli4awame parite na magazina s cenite na wseki ot nojowete
				if( knife.getQuantityBasket()>1){
					for(int i = 0; i<knife.getQuantityBasket(); i++){
						int moneyToAdd = knife.getPrice();
						int currentMoneyInTheShop = getAllMoneyByTheLastTransaction();
						int moneyAfterAdding = currentMoneyInTheShop + moneyToAdd;
						addMoneyToTheShop(moneyAfterAdding);
					}
				}else{
					int moneyToAdd = knife.getPrice();
					int currentMoneyInTheShop = getAllMoneyByTheLastTransaction();
					int moneyAfterAdding = currentMoneyInTheShop + moneyToAdd;
					addMoneyToTheShop(moneyAfterAdding);
				}
				//allSoldKnifes.add(knife);
//				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//				LocalDateTime now = LocalDateTime.now();
//				String currentDateTime = dtf.format(now);
//				
//				String DateTimeAdded = knife.getCurrentDateTimeWhenIsAddedToTheBasket();
				
				
				
				//dobawqme broq mu kam prodadenite nojowe
				knifeDAO.addSoldKnifeQuantity(knife.getId(), knife.getQuantityBasket());
				//knifeDAO.decreezeKnifeQuantityByKnifeId(knife.getId(), knife.getQuantityBasket());
				//ako ot tozi noj nqma powe4e koli4estwo, mahame tozi noj ot wsi4ki drugi ko6nici
				if(knife.getQuantity() == 0){
					//mahame we4e prodadenite nojowe ot koli4kata
					basketDAO.removeKnifeFromAllBasketsFromAdminByKnifeId(knife.getId());
				}
				//mahame wsi4ki nojowe ot ko6nicata na teku6tiq user
				basketDAO.removeQuantityForSpecificUserByUserId(userId);
			}
			
			con.commit();
			String pathToDir = File.separator +"Users"+ File.separator +"Venci" + File.separator  + "Desktop" + File.separator+ "Sells";
			File theDir = new File(pathToDir);
			if (!theDir.exists()) {
			    theDir.mkdir();
			}

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.now();
			String dateName = dtf.format(localDate);
			String pathToFile = pathToDir + File.separator  + dateName.replaceAll("/", "-") + ".txt";
			File file = new File(pathToFile);
			boolean doesTheFileExist = false;
			
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					throw new MYIOException("Exception checking if file exists");
				}
			}else{
				doesTheFileExist = true;
			}
			
			for(Knife knife : allKnifesFromTheBasket){
				
				BufferedWriter bw = null;
				FileWriter fw = null;

				try {
					String content = "\nDate and time : " + dateName + " knife name : "
									+ knife.getManufactor() + " " 
									+ knife.getModel() + " quantity : " + 
									+ knife.getQuantityBasket()
									+ " money to the shop : " + (knife.getQuantityBasket()*knife.getPrice())
									+ "shop capital : " + (getAllMoneyByTheLastTransaction());
					
					//ako failat sa6testwuwa, dopiswame w nego
					if(doesTheFileExist){
						 fw = new FileWriter(file, true);
					}else{//ako failat e prazen, pi6em w nego
						fw = new FileWriter(file);
					}
					bw = new BufferedWriter(fw);
					bw.write(content);
					
				} catch (IOException e) {
					throw new MYIOException("Exception checking if file exists");
				} finally {

					try {

						if (bw != null)
							bw.close();

						if (fw != null)
							fw.close();

					} catch (IOException ex) {
						throw new MYIOException("Exception checking if file exists");
					}


			}
		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				throw new MYSQLException("There is a problem with the DB");
			}
		}
		
	}
	
	
	 
	
	 

}
