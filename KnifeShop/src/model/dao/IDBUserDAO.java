package model.dao;

import java.util.ArrayList;

import exceptions.MySQLExseption;
import model.classes.User;

public interface IDBUserDAO {
	
	public void addUserToDB(User user) throws MySQLExseption;
	public boolean isThereSuchUserInDBByUsernameAndPassword(String username, String password) throws MySQLExseption;
	public User getUserByUsernameFromDB(String username) throws MySQLExseption;
	public void makeUserToAdminByUserId(int userId) throws MySQLExseption;
	public void makeAdminToUserByUserId(int userId) throws MySQLExseption;
	public ArrayList<User> getAllUsersFromDB() throws MySQLExseption;
	public ArrayList<User> getAllAdminsFromDB() throws MySQLExseption;
	public boolean isTheUserAdminByUserId(int userId) throws MySQLExseption;
	public int getUserIdByUsernameAndPassword(String username, String password) throws MySQLExseption;
	public User getUserByUserId(int userId) throws MySQLExseption;
	public void deleteUserOrAdminFromDbByUsername(int userId) throws MySQLExseption;
	public boolean isTheUserMasterAdminByUserId(int userId) throws MySQLExseption;
	public boolean isThereSuchUsernameInDB(String username) throws MySQLExseption;
	public boolean isThereSuchEmailInDB(String email) throws MySQLExseption;
}
