package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import exceptions.MYSQLException;
import model.classes.Knife;
import model.db.DBManager;

public class DBBasketDAO implements IDBasketDAO {
	
	 private static DBBasketDAO instance;
		
	 private DBManager manager;
	 
	 public DBBasketDAO() {
		 manager = DBManager.getInstance();
	 }
	 
	 public static DBBasketDAO getInstance(){
		 if(instance == null){
			 instance = new DBBasketDAO();
		 }
		 return instance;
	 }
	 
	 
	@Override
	public void addKnifeToBasketByKnifeId(int userId, int knifeId, int quantity) throws MYSQLException {
		String query = "INSERT INTO KnifeShop.Basket (idUser, idKnife, quantity) VALUES (?, ?, ?)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			st.setInt(2, knifeId);
			st.setInt(3, quantity);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void removeKnifeFromBasketByKnifeId(int userId, int knifeId) throws MYSQLException {
		String query = "DELETE FROM KnifeShop.Basket WHERE idUser = ? AND idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			st.setInt(2, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public boolean isThereSuchKnifeInTheBasketByKnifeId(int userId, int knifeId) throws MYSQLException {
		String query = "SELECT idKnife FROM KnifeShop.Basket WHERE idUser = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				int currentKnifeId = rs.getInt(1);
				if(currentKnifeId == knifeId){
					return true;
				}
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return false;
	}

	@Override
	public void addMoreQuantityToKnifeInBasket(int userId, int knifeId, int quantityToAdd) throws MYSQLException {
		String query = "UPDATE KnifeShop.Basket SET quantity = ? WHERE idUser = ? AND idKnife = ?";
		int currentQuantity = getQuantityForKnifeFromBasket(userId, knifeId);
		int updatedQuantity = currentQuantity + quantityToAdd;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, updatedQuantity);
			st.setInt(2, userId);
			st.setInt(3, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public int getQuantityForKnifeFromBasket(int userId, int knifeId) throws MYSQLException {
		String query = "SELECT quantity FROM KnifeShop.Basket WHERE idUser = ? AND idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			st.setInt(2, knifeId);
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
	public ArrayList<Knife> getAllKnifesFromTheBasketByUserId(int userId) throws MYSQLException {
		String query = "SELECT idKnife FROM KnifeShop.Basket WHERE idUser = ?";
		DBKnifeDAO knifeDAO = new DBKnifeDAO();
		ArrayList<Knife> allKnifesFromBasket = new ArrayList<>();
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				int currentKnifeId = rs.getInt(1);
				Knife currentKnife = knifeDAO.getKnifeWithQunatityInTheBasketByKnifeId(currentKnifeId, userId);
				allKnifesFromBasket.add(currentKnife);
			}
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
		return allKnifesFromBasket;
	}

	@Override
	public void removeQuantitFromKnifeInTheBasket(int userId, int knifeId, int quantityToRemove) throws MYSQLException {
		String query = "UPDATE KnifeShop.Basket SET quantity = ? WHERE idUser = ? AND idKnife = ?";
		int currentQuantity = getQuantityForKnifeFromBasket(userId, knifeId);
		int updatedQuantity = currentQuantity - quantityToRemove;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, updatedQuantity);
			st.setInt(2, userId);
			st.setInt(3, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void removeKnifeFromAllBasketsFromAdminByKnifeId(int knifeId) throws MYSQLException {
		String query = "DELETE FROM KnifeShop.Basket WHERE idKnife = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, knifeId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}

	@Override
	public void removeQuantityForSpecificUserByUserId(int userId) throws MYSQLException {
		String query = "DELETE FROM KnifeShop.Basket WHERE idUser = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			st.execute();
		} catch (SQLException e) {
			throw new MYSQLException("There is a problem with the DB");
		}
	}


}
