package model.dao;

import java.lang.Thread.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exceptions.MySQLExseption;
import model.classes.User;
import model.db.DBManager;

public class DBUserDAO implements IDBUserDAO{
	
	private static DBUserDAO instance;
	
	private DBManager manager;
	 
	 public DBUserDAO() {
		 manager = DBManager.getInstance();
	 }
	 public static DBUserDAO getInstance(){
		 if(instance == null){
			 instance = new DBUserDAO();
		 }
		 return instance;
	 }
	
	private static final String ADD_USER_TO_DB = "INSERT INTO KnifeShop.User (username, password, email, age) VALUES (?, ?, ?, ?)";
	private static final String IS_THERE_SUCH_USER_IN_DB_BY_USERNAME_AND_PASSWORD = "SELECT id FROM KnifeShop.User WHERE username = ? AND password = ?";

	@Override
	public void addUserToDB(User user) throws MySQLExseption {
		String query = ADD_USER_TO_DB;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getEmail());
			st.setInt(4, user.getAge());
			st.execute();
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
	}
	
	@Override
	public boolean isThereSuchUserInDBByUsernameAndPassword(String username, String password) throws MySQLExseption {
		String query = IS_THERE_SUCH_USER_IN_DB_BY_USERNAME_AND_PASSWORD;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return false;
	}
	
	@Override
	public User getUserByUsernameFromDB(String username) throws MySQLExseption {
		String query = "SELECT id, username, password, email, age FROM KnifeShop.User WHERE username = ?";
		User user = new User();
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setAge(rs.getInt(5));
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return user;
	}
	@Override
	public void makeUserToAdminByUserId(int userId) throws MySQLExseption {
		String query = "UPDATE KnifeShop.User SET isAdmin=true WHERE id = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			st.execute();
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
	}
	@Override
	public void makeAdminToUserByUserId(int userId) throws MySQLExseption {
		String query = "UPDATE KnifeShop.User SET isAdmin=false WHERE id = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			st.execute();
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
	}
	@Override
	public ArrayList<User> getAllUsersFromDB() throws MySQLExseption {
		String query = "SELECT id, username, email, age, isAdmin FROM KnifeShop.User WHERE isAdmin = false";
		ArrayList<User> allUsers = new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String email = rs.getString(3);
				int age = rs.getInt(4);
				boolean isAdmin = rs.getBoolean(5);
				User user = new User(id, username, email, age, isAdmin);
				allUsers.add(user);
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return allUsers;
	}
	@Override
	public ArrayList<User> getAllAdminsFromDB() throws MySQLExseption {
		String query = "SELECT id, username, email, age, isAdmin FROM KnifeShop.User WHERE NOT username = \"adminMaster\" AND isAdmin = true";
		ArrayList<User> allAdmins = new ArrayList<>();
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String email = rs.getString(3);
				int age = rs.getInt(4);
				boolean isAdmin = rs.getBoolean(5);
				User admin = new User(id, username, email, age, isAdmin);
				allAdmins.add(admin);
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return allAdmins;
	}
	
	@Override
	public boolean isTheUserAdminByUserId(int userId) throws MySQLExseption {
		String query = "SELECT isAdmin FROM KnifeShop.User WHERE id = ?";
		boolean isTheUserAdmin = false;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				isTheUserAdmin = rs.getBoolean(1);
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return isTheUserAdmin;
	}
	
	@Override
	public int getUserIdByUsernameAndPassword(String username, String password) throws MySQLExseption {
		String query = "SELECT id FROM KnifeShop.User WHERE username = ? and password = ?";
		int userId = 0;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				userId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return userId;
	}
	
	@Override
	public User getUserByUserId(int userId) throws MySQLExseption {
		String query = "SELECT id, username, password, email, age, isAdmin FROM KnifeShop.User WHERE id = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				int age = rs.getInt(5);
				boolean isAdmin = rs.getBoolean(6);
				User user = new User(id, username, password, email, age, isAdmin);
				return user;
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return null;
	}
	
	@Override
	public void deleteUserOrAdminFromDbByUsername(int userId) throws MySQLExseption {
		String query = "DELETE FROM KnifeShop.User WHERE id = ?";
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			st.execute();
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
	}
	
	@Override
	public boolean isTheUserMasterAdminByUserId(int userId) throws MySQLExseption {
		String query = "SELECT isMasterAdmin FROM KnifeShop.User WHERE id = ?";
		boolean isTheUserMasterAdmin = false;
		try {
			PreparedStatement st = manager.getConnection().prepareStatement(query);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				isTheUserMasterAdmin = rs.getBoolean(1);
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return isTheUserMasterAdmin;
	}
	
	@Override
	public boolean isThereSuchUsernameInDB(String username) throws MySQLExseption {
		String query = "SELECT username FROM KnifeShop.User";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				if(rs.getString(1).equals(username)){
					return true;
				}
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return false;
	}
	
	@Override
	public boolean isThereSuchEmailInDB(String email) throws MySQLExseption {
		String query = "SELECT email FROM KnifeShop.User";
		try {
			Statement st = manager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				if(rs.getString(1).equals(email)){
					return true;
				}
			}
		} catch (SQLException e) {
			throw new MySQLExseption("There is a problem with the DB");
		}
		return false;
	}

}
