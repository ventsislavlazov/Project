package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;

import exceptions.MYSQLException;
import model.classes.Knife;
//import model.classes.Knife.Lock;
//import model.classes.Knife.Manufactor;
//import model.classes.Knife.Steel;
import model.db.DBManager;

public class DBKnifeDAO implements IDBKnifeDAO{
	
	 private static DBKnifeDAO instance;
		
	 private DBManager manager;
	 
	 public DBKnifeDAO() {
		 manager = DBManager.getInstance();
	 }
	 
	 public static DBKnifeDAO getInstance(){
		 if(instance == null){
			 instance = new DBKnifeDAO();
		 }
		 return instance;
	 }
	 
	 DBBasketDAO basketDAO = new DBBasketDAO();
	
	@Override
	public void addKnifeToDB(Knife knife) throws MYSQLException {
		String query = "INSERT INTO KnifeShop.Knife (length, model, folder, price) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knife.getLength());
			st.setString(2, knife.getModel());
			st.setBoolean(3, knife.isFolder());
			st.setInt(4, knife.getPrice());
			st.execute();
			addSteelToKnifeInDB(knife.getSteel());
			addManufactorToKnifeInDB(knife.getManufactor());
			addLockToKnifeInDB(knife.getLock());
			addImageToDB(knife.getImageName());
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void addSteelToKnifeInDB(/*Steel steel*/String steel) throws MYSQLException {
		int knifeId = getLastInsertedId();
		String query = "INSERT INTO KnifeShop.Steel (idSteel, idKnife) VALUES (?, ?)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			int idSteel = getIdSteelBySteelName(steel);
			st.setInt(1, idSteel);
			st.setInt(2, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		
	}

	@Override
	public void addManufactorToKnifeInDB(/*Manufactor manufactor*/String manufactor) throws MYSQLException {
		int knifeId = getLastInsertedId();
		String query = "INSERT INTO KnifeShop.Manufactor (idManufactor, idKnife) VALUES (?, ?)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			int idManufactor = getIdManufactorByManufactorName(manufactor);
			st.setInt(1, idManufactor);
			st.setInt(2, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void addLockToKnifeInDB(/*Lock lock*/String lock) throws MYSQLException {
		int knifeId = getLastInsertedId();
		String query = "INSERT INTO KnifeShop.Locking (idLock, idKnife) VALUES (?, ?)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			int idLock = getIdLockByLockName(lock);
			st.setInt(1, idLock);
			st.setInt(2, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}
	
	@Override
	public int getLastInsertedId() throws MYSQLException {
		int lastId = 0;
		String query = "SELECT idKnife FROM KnifeShop.Knife ORDER BY idKnife DESC LIMIT 1";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()){
				lastId = rs.getInt("idKnife");
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return lastId;
	}

	@Override
	public void addManufactorToDB(String manufactorName) throws MYSQLException {
		String query = "INSERT INTO KnifeShop.AllManufactors (manufactorName) VALUES (?)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, manufactorName);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public ArrayList<String> getAllManufactorsNamesFromDB() throws MYSQLException {
		String query = "SELECT manufactorName FROM KnifeShop.AllManufactors";
		ArrayList<String> allManufactorNames = new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				allManufactorNames.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allManufactorNames;
	}

	@Override
	public void addSteelToDB(String steelName) throws MYSQLException {
		String query = "INSERT INTO KnifeShop.AllSteels (steelName) VALUES (?)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, steelName);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public ArrayList<String> getAllSteelsNamesFromDB() throws MYSQLException {
		String query = "SELECT steelName FROM KnifeShop.AllSteels";
		ArrayList<String> allSteelNames = new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				allSteelNames.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allSteelNames;
	}

	@Override
	public void addLockToDB(String lockName) throws MYSQLException {
		String query = "INSERT INTO KnifeShop.AllLocks (lockName) VALUES (?)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, lockName);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public ArrayList<String> getAllLocksNamesFromDB() throws MYSQLException {
		String query = "SELECT lockName FROM KnifeShop.AllLocks";
		ArrayList<String> allLockNames = new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				allLockNames.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allLockNames;
	}

	@Override
	public boolean isThereSuchManufactorInDB(String manufactor) throws MYSQLException {
		String manufactorTrimed = manufactor.replaceAll("\\s+","");
		String query = "SELECT manufactorName FROM KnifeShop.AllManufactors";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				String result = rs.getString(1).replaceAll("\\s+","");
				if(result.equalsIgnoreCase(manufactorTrimed)){
					return true;
				}
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return false;
	}

	@Override
	public boolean isThereSuchLockInDB(String lock) throws MYSQLException {
		//maham wsi4ki razstoqniq w stinga
		String lockTrimed = lock.replaceAll("\\s+","");
		String query = "SELECT lockName FROM KnifeShop.AllLocks";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				String result = rs.getString(1).replaceAll("\\s+","");
				if(result.equalsIgnoreCase(lockTrimed)){
					return true;
				}
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return false;
	}

	@Override
	public boolean isThereSuchSteelInDB(String steel) throws MYSQLException {
		String steelTrimed = steel.replaceAll("\\s+","");
		String query = "SELECT steelName FROM KnifeShop.AllSteels";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				String result = rs.getString(1).replaceAll("\\s+","");
				if(result.equalsIgnoreCase(steelTrimed)){
					return true;
				}
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return false;
	}

	@Override
	public ArrayList<Knife> getAllKnifesFromDB() throws MYSQLException {
		String query = "SELECT idKnife, length, model, folder, price, quantity FROM KnifeShop.Knife";
		ArrayList<Knife> allKnifes= new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int knifeId = rs.getInt(1);
				int length = rs.getInt(2);
				String model = rs.getString(3);
				boolean folder = rs.getBoolean(4);
				int price = rs.getInt(5);
				int quantity = rs.getInt(6); 
				String steel = getKnifeSteel(knifeId);
				String manufactor = getKnifeManufactor(knifeId);
				String lock = getKnifeLock(knifeId);
				String imageName = getKnifeImageName(knifeId);
				
//				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//				String path = classLoader.getResource("/Users/Venci/Desktop/" + imageName).getPath();
//				System.out.println("path " + path);
				
				Knife knife = new Knife(knifeId, length, steel, manufactor, model, price, lock, folder, imageName, quantity);
				allKnifes.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allKnifes;
	}

	@Override
	public String getKnifeSteel(int knifeId) throws MYSQLException {
		String query = "SELECT idSteel FROM KnifeShop.Steel WHERE idKnife = ?";
		int idSteel = 0;
		String steelName = null;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				idSteel = rs.getInt(1);
			}
			steelName = getSteelNameBySteelId(idSteel);
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		//System.out.println("steel " + steelName);
		return steelName;
	}

	@Override
	public String getKnifeManufactor(int knifeId) throws MYSQLException {
		String query = "SELECT idManufactor FROM KnifeShop.Manufactor WHERE idKnife = ?";
		int idManufactor = 0;
		String manufactorName = null;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				idManufactor = rs.getInt(1);
			}
			manufactorName = getManufactorNameByManufactorId(idManufactor);
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		//System.out.println("manufactor " + manufactorName);
		return manufactorName;
	}

	@Override
	public String getKnifeLock(int knifeId) throws MYSQLException {
		String query = "SELECT idLock FROM KnifeShop.Locking WHERE idKnife = ?";
		int idLock = 0;
		String lockName = null;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				idLock = rs.getInt(1);
			}
			lockName = getLockNameByLockId(idLock);
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		//System.out.println("lock " + lockName);
		return lockName;
	}

	@Override
	public int getIdSteelBySteelName(String steel) throws MYSQLException {
		String query = "SELECT idSteel FROM KnifeShop.AllSteels WHERE steelName = ?";
		int idSteel = 0;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, steel);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				idSteel = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return idSteel;
	}

	@Override
	public int getIdManufactorByManufactorName(String manufactor) throws MYSQLException {
		String query = "SELECT idManufactor FROM KnifeShop.AllManufactors WHERE manufactorName = ?";
		int idManufactor = 0;
		PreparedStatement st;
		try {
			st = manager.getConnection().prepareStatement(query);
			st.setString(1, manufactor);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				idManufactor = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return idManufactor;
	}

	@Override
	public int getIdLockByLockName(String lock) throws MYSQLException {
		String query = "SELECT idLock FROM KnifeShop.AllLocks WHERE lockName = ?";
		int idLock = 0;
		PreparedStatement st;
		try {
			st = manager.getConnection().prepareStatement(query);
			st.setString(1, lock);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				idLock = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return idLock;
	}

	@Override
	public String getSteelNameBySteelId(int steelId) throws MYSQLException {
		String query = "SELECT steelName FROM KnifeShop.AllSteels WHERE idSteel = ?";
		String steelName = null;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, steelId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				steelName = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return steelName;
	}

	@Override
	public String getManufactorNameByManufactorId(int manufactorId) throws MYSQLException {
		String query = "SELECT manufactorName FROM KnifeShop.AllManufactors WHERE idManufactor = ?";
		String manufactorName = null;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, manufactorId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				manufactorName = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return manufactorName;
	}

	@Override
	public String getLockNameByLockId(int lockId) throws MYSQLException {
		String query = "SELECT lockName FROM KnifeShop.AllLocks WHERE idLock = ?";
		String lockName = null;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, lockId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				lockName = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return lockName;
	}

	@Override
	public void addImageToDB(String imageName) throws MYSQLException {
		int knifeId = getLastInsertedId();
		String query = "INSERT INTO KnifeShop.Images (idKnife, image) VALUES (?, ?)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			st.setString(2, imageName);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public String getKnifeImageName(int knifeId) throws MYSQLException {
		String query = "SELECT image FROM KnifeShop.Images WHERE idKnife = ?";
		String imageName = null;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				imageName = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return imageName;
	}

	@Override
	public void addQuantityToDB(int quantity, int knifeId) throws MYSQLException {
		String query = "UPDATE KnifeShop.Knife SET quantity = ? WHERE idKnife = ?;";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, quantity);
			st.setInt(2, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		
	}

	@Override
	public int getQuantityForCurrentKnifeByKnifeId(int knifeId) throws MYSQLException {
		String query = "SELECT quantity FROM KnifeShop.Knife WHERE idKnife = ?";
		int quantity=0;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				quantity = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return quantity;
	}

	@Override
	public void removeKnifeFromDBByKnifeId(int knifeId) throws MYSQLException {
		String query = "DELETE FROM KnifeShop.Knife WHERE idKnife = ?";
		deleteFromTableWithKnifeIdAndManufactorIdByKnifeId(knifeId);
		deleteFromTableWithKnifeIdAndLockIdByKnifeId(knifeId);
		deleteFromTableWithKnifeIdAndSteelIdByKnifeId(knifeId);
		deleteFromTableWithKnifeIdAndImageIdByKnifeId(knifeId);
		basketDAO.removeKnifeFromAllBasketsFromAdminByKnifeId(knifeId);
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void deleteFromTableWithKnifeIdAndManufactorIdByKnifeId(int knifeId) throws MYSQLException {
		String query = "DELETE FROM KnifeShop.Manufactor WHERE idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void deleteFromTableWithKnifeIdAndLockIdByKnifeId(int knifeId) throws MYSQLException {
		String query = "DELETE FROM KnifeShop.Locking WHERE idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void deleteFromTableWithKnifeIdAndSteelIdByKnifeId(int knifeId) throws MYSQLException {
		String query = "DELETE FROM KnifeShop.Steel WHERE idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void deleteFromTableWithKnifeIdAndImageIdByKnifeId(int knifeId) throws MYSQLException {
		String query = "DELETE FROM KnifeShop.Images WHERE idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public Knife getKnifeByKnifeId(int knifeId) throws MYSQLException {
		String query = "SELECT length, model, folder, price, quantity FROM KnifeShop.Knife WHERE idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				int length = rs.getInt(1);
				String model = rs.getString(2);
				boolean folder = rs.getBoolean(3);
				int price = rs.getInt(4);
				int quantity = rs.getInt(5);
				String steel = getKnifeSteel(knifeId);
				String manufactor = getKnifeManufactor(knifeId);
				String lock = getKnifeLock(knifeId);
				String imageName = getKnifeImageName(knifeId);
				Knife knife = new Knife(length, steel, manufactor, model, price, lock, folder, imageName, quantity);
				return knife;
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return null;
	}

	@Override
	public boolean isThereEnaughtQuantityFromThisKnifeByKnifeId(int knifeId, int requestedQuantity) throws MYSQLException {
		String query = "SELECT quantity FROM KnifeShop.Knife WHERE idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				int currentQuantity = rs.getInt(1);
				if(currentQuantity - requestedQuantity >= 0){
					return true;
				}
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return false;
	}

	@Override
	public int getQuantityByKnifeId(int knifeId) throws MYSQLException {
		String query = "SELECT quantity FROM KnifeShop.Knife WHERE idKnife = ?";
		int quantity = 0;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				quantity = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return quantity;
	}

	@Override
	public void decreezeKnifeQuantityByKnifeId(int knifeId, int quantityToDecreezeWith) throws MYSQLException {
		String query = "UPDATE KnifeShop.Knife SET quantity=? WHERE idKnife = ?";
		int currentQuantity = getQuantityByKnifeId(knifeId);
		int updatedQuantity = currentQuantity - quantityToDecreezeWith;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, updatedQuantity);
			st.setInt(2, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public Knife getKnifeWithQunatityInTheBasketByKnifeId(int knifeId, int userId) throws MYSQLException {
		String query = "SELECT length, model, folder, price, quantity FROM KnifeShop.Knife WHERE idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				int length = rs.getInt(1);
				String model = rs.getString(2);
				boolean folder = rs.getBoolean(3);
				int price = rs.getInt(4);
				int quantity = rs.getInt(5);
				String steel = getKnifeSteel(knifeId);
				String manufactor = getKnifeManufactor(knifeId);
				String lock = getKnifeLock(knifeId);
				String imageName = getKnifeImageName(knifeId);
				int quantityBasket = basketDAO.getQuantityForKnifeFromBasket(userId, knifeId);
				Knife knife = new Knife(knifeId, length, steel, manufactor, model, price, lock, folder, imageName, quantity, quantityBasket);
				return knife;
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return null;
	}

	@Override
	public void addSoldKnifeQuantity(int knifeId, int quantityToAdd) throws MYSQLException {
		String query = "UPDATE KnifeShop.Knife SET numbersOfSells=? WHERE idKnife = ?";
		int currentNumberOfSoldKnifes = getNumberOfSoldKnifes(knifeId);
		int updatedNumberOfSoldKnifes = currentNumberOfSoldKnifes + quantityToAdd;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, updatedNumberOfSoldKnifes);
			st.setInt(2, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public int getNumberOfSoldKnifes(int knifeId) throws MYSQLException {
		String query = "SELECT numbersOfSells FROM KnifeShop.Knife WHERE idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
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
	public ArrayList<Knife> getTheThreeBestSellers() throws MYSQLException {
		ArrayList<Knife> threeBestSellers = new ArrayList<>();
		String query = "SELECT idKnife FROM KnifeShop.Knife ORDER BY numbersOfSells DESC LIMIT 3";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				Knife knife = getKnifeForBestsellers(rs.getInt(1));
				threeBestSellers.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return threeBestSellers;
	}

	@Override
	public ArrayList<Knife> getAllSoldKnifes() throws MYSQLException {
		ArrayList<Knife> allSoldKnifes = new ArrayList<>();
		String query = "SELECT idKnife FROM KnifeShop.Knife WHERE numbersOfSells > 0";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				Knife knife = getKnifeForBestsellers(rs.getInt(1));
				allSoldKnifes.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allSoldKnifes;
	}

	@Override
	public ArrayList<Knife> getTheThreeCheapest() throws MYSQLException {
		ArrayList<Knife> cheapest = new ArrayList<>();
		String query = "SELECT idKnife FROM KnifeShop.Knife ORDER BY price LIMIT 3";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				Knife knife = getKnifeByKnifeId(rs.getInt(1));
				cheapest.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return cheapest;
	}

	@Override
	public ArrayList<Knife> getTheThreeMostExpensive() throws MYSQLException {
		ArrayList<Knife> mostExpensive = new ArrayList<>();
		String query = "SELECT idKnife FROM KnifeShop.Knife ORDER BY price DESC LIMIT 3";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				Knife knife = getKnifeByKnifeId(rs.getInt(1));
				mostExpensive.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return mostExpensive;
	}

	@Override
	public ArrayList<Knife> getTheThreeWithHighestQuantity() throws MYSQLException {
		ArrayList<Knife> highestQuantity = new ArrayList<>();
		String query = "SELECT idKnife FROM KnifeShop.Knife ORDER BY quantity DESC LIMIT 3";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				Knife knife = getKnifeByKnifeId(rs.getInt(1));
				highestQuantity.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return highestQuantity;
	}

	@Override
	public ArrayList<Knife> getTheThreeWithLowestQuantity() throws MYSQLException {
		ArrayList<Knife> lowestQuantity = new ArrayList<>();
		String query = "SELECT idKnife FROM KnifeShop.Knife ORDER BY quantity LIMIT 3";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				Knife knife = getKnifeByKnifeId(rs.getInt(1));
				lowestQuantity.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return lowestQuantity;
	}

	@Override
	public Knife getKnifeForBestsellers(int idKnife) throws MYSQLException {
		String query = "SELECT length, model, folder, price, numbersOfSells FROM KnifeShop.Knife WHERE idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, idKnife);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				int length = rs.getInt(1);
				String model = rs.getString(2);
				boolean folder = rs.getBoolean(3);
				int price = rs.getInt(4);
				int numberOfSells = rs.getInt(5);
				String steel = getKnifeSteel(idKnife);
				String manufactor = getKnifeManufactor(idKnife);
				String lock = getKnifeLock(idKnife);
				String imageName = getKnifeImageName(idKnife);
				Knife knife = new Knife(length, steel, manufactor, model, price, lock, folder, imageName, numberOfSells);
				return knife;
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return null;
	}

	@Override
	public ArrayList<Knife> getAllKnifesSortByHighestPrice() throws MYSQLException {
		String query = "SELECT idKnife, length, model, folder, price, quantity FROM KnifeShop.Knife ORDER BY price DESC";
		ArrayList<Knife> allKnifes= new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int knifeId = rs.getInt(1);
				int length = rs.getInt(2);
				String model = rs.getString(3);
				boolean folder = rs.getBoolean(4);
				int price = rs.getInt(5);
				int quantity = rs.getInt(6); 
				String steel = getKnifeSteel(knifeId);
				String manufactor = getKnifeManufactor(knifeId);
				String lock = getKnifeLock(knifeId);
				String imageName = getKnifeImageName(knifeId);
				
//				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//				String path = classLoader.getResource("/Users/Venci/Desktop/" + imageName).getPath();
//				System.out.println("path " + path);
				
				Knife knife = new Knife(knifeId, length, steel, manufactor, model, price, lock, folder, imageName, quantity);
				allKnifes.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allKnifes;
	}

	@Override
	public ArrayList<Knife> getAllKnifesSortByLowestPrice() throws MYSQLException {
		String query = "SELECT idKnife, length, model, folder, price, quantity FROM KnifeShop.Knife ORDER BY price";
		ArrayList<Knife> allKnifes= new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int knifeId = rs.getInt(1);
				int length = rs.getInt(2);
				String model = rs.getString(3);
				boolean folder = rs.getBoolean(4);
				int price = rs.getInt(5);
				int quantity = rs.getInt(6); 
				String steel = getKnifeSteel(knifeId);
				String manufactor = getKnifeManufactor(knifeId);
				String lock = getKnifeLock(knifeId);
				String imageName = getKnifeImageName(knifeId);
				
				Knife knife = new Knife(knifeId, length, steel, manufactor, model, price, lock, folder, imageName, quantity);
				allKnifes.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allKnifes;
	}
	
	@Override
	public ArrayList<Knife> getAllKnifesSortByHighestLength() throws MYSQLException {
		String query = "SELECT idKnife, length, model, folder, price, quantity FROM KnifeShop.Knife ORDER BY length DESC";
		ArrayList<Knife> allKnifes= new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int knifeId = rs.getInt(1);
				int length = rs.getInt(2);
				String model = rs.getString(3);
				boolean folder = rs.getBoolean(4);
				int price = rs.getInt(5);
				int quantity = rs.getInt(6); 
				String steel = getKnifeSteel(knifeId);
				String manufactor = getKnifeManufactor(knifeId);
				String lock = getKnifeLock(knifeId);
				String imageName = getKnifeImageName(knifeId);
				
				Knife knife = new Knife(knifeId, length, steel, manufactor, model, price, lock, folder, imageName, quantity);
				allKnifes.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allKnifes;
	}
	
	@Override
	public ArrayList<Knife> getAllKnifesSortByLowestLength() throws MYSQLException {
		String query = "SELECT idKnife, length, model, folder, price, quantity FROM KnifeShop.Knife ORDER BY length";
		ArrayList<Knife> allKnifes= new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int knifeId = rs.getInt(1);
				int length = rs.getInt(2);
				String model = rs.getString(3);
				boolean folder = rs.getBoolean(4);
				int price = rs.getInt(5);
				int quantity = rs.getInt(6); 
				String steel = getKnifeSteel(knifeId);
				String manufactor = getKnifeManufactor(knifeId);
				String lock = getKnifeLock(knifeId);
				String imageName = getKnifeImageName(knifeId);
				
//				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//				String path = classLoader.getResource("/Users/Venci/Desktop/" + imageName).getPath();
//				System.out.println("path " + path);
				
				Knife knife = new Knife(knifeId, length, steel, manufactor, model, price, lock, folder, imageName, quantity);
				allKnifes.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allKnifes;
	}

	@Override
	public ArrayList<Knife> getLastTreeByQuantityMoreThanZero() throws MYSQLException {
		ArrayList<Knife> knifes = new ArrayList<>();
		String query = "SELECT idKnife, length, model, folder, price, quantity FROM KnifeShop.Knife WHERE quantity>0 ORDER BY quantity LIMIT 3";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int knifeId = rs.getInt(1);
				int length = rs.getInt(2);
				String model = rs.getString(3);
				boolean folder = rs.getBoolean(4);
				int price = rs.getInt(5);
				int quantity = rs.getInt(6); 
				String steel = getKnifeSteel(knifeId);
				String manufactor = getKnifeManufactor(knifeId);
				String lock = getKnifeLock(knifeId);
				String imageName = getKnifeImageName(knifeId);
				Knife knife = new Knife(knifeId, length, steel, manufactor, model, price, lock, folder, imageName, quantity);
				knifes.add(knife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return knifes;
	}
	

//	@Override
//	public boolean isThereSuchKnifeInDB(Knife knife) {
//		ArrayList<Knife> allKnifes = getAllKnifesFromDB();
//		for(Knife k : allKnifes){
//			if(k.getLength() == knife.getLength() &&
//			   k.getSteel().equals(knife.getSteel())&&
//			   k.getManufactor().equals(knife.getManufactor())&&
//			   k.getLock().equals(knife.getLock())&&
//			   k.getImageName().equals(knife.getImageName())&&
//			   k.isFolder() == knife.isFolder()&&
//			   k.getModel().equals(knife.getModel())&&
//			   k.getPrice() == knife.getPrice()){
//				return true;
//			}
//		}
//		return false;
//	}

}
