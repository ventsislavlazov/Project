package model.dao;

import java.util.ArrayList;

import exceptions.MYSQLException;
import model.classes.User;

public interface IDBUserDAO {
	
	public void addUserToDB(User user) throws MYSQLException;
	public boolean isThereSuchUserInDBByUsernameAndPassword(String username, String password) throws MYSQLException;
	public User getUserByUsernameFromDB(String username) throws MYSQLException;
	public void makeUserToAdminByUserId(int userId) throws MYSQLException;
	public void makeAdminToUserByUserId(int userId) throws MYSQLException;
	public ArrayList<User> getAllUsersFromDB() throws MYSQLException;
	public ArrayList<User> getAllAdminsFromDB() throws MYSQLException;
	public boolean isTheUserAdminByUserId(int userId) throws MYSQLException;
	public int getUserIdByUsernameAndPassword(String username, String password) throws MYSQLException;
	public User getUserByUserId(int userId) throws MYSQLException;
	public void deleteUserOrAdminFromDbByUsername(int userId) throws MYSQLException;
	public boolean isTheUserMasterAdminByUserId(int userId) throws MYSQLException;
	public boolean isThereSuchUsernameInDB(String username) throws MYSQLException;
	public boolean isThereSuchEmailInDB(String email) throws MYSQLException;
}
