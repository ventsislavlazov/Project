package model.dao;

import java.lang.Thread.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.MySQLExseption;
import model.db.DBManager;

public class DBColorDAO implements IDBColorDAO {
	
	 private static DBColorDAO instance;
		
	 private DBManager manager;
	 
	 public DBColorDAO() {
		 manager = DBManager.getInstance();
	 }
	 
	 public static DBColorDAO getInstance(){
		 if(instance == null){
			 instance = new DBColorDAO();
		 }
		 return instance;
	 }

	@Override
	public void addNewColor(String color) throws MySQLExseption {
		String query = "INSERT INTO KnifeShop.Colors (colorNumber) VALUES (?)";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, color);
			st.execute();
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
	}

	@Override
	public void changeColor(String newColor) throws MySQLExseption {
		if(!isThereSuchColor(newColor)){
			addNewColor(newColor);
		}
		makeAllColorsUnactive();
		String query = "UPDATE KnifeShop.Colors SET chosen = TRUE WHERE colorNumber = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, newColor);
			st.execute();
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
	}

	@Override
	public boolean isThereSuchColor(String color) throws MySQLExseption {
		String query = "SELECT colorNumber FROM KnifeShop.Colors";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				if(rs.getString(1).equals(color)){
					return true;
				}
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return false;
	}

	@Override
	public void makeAllColorsUnactive() throws MySQLExseption {
		String query = "UPDATE KnifeShop.Colors SET chosen = FALSE";
		try {
			Statement st = manager.getConnection().createStatement();
			st.execute(query);
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		
	}

	@Override
	public String getTheCurrentColor() throws MySQLExseption {
		String query = "SELECT colorNumber FROM KnifeShop.Colors WHERE chosen = TRUE";
		String color = null;
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()){
				color = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return color;
	}
	 
	 

}
