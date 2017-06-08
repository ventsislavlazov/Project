package model.dao;

import exceptions.MYSQLException;

public interface IDBColorDAO {
	
	public void addNewColor(String color) throws MYSQLException;
	public void changeColor(String newColor) throws MYSQLException;
	public boolean isThereSuchColor(String color) throws MYSQLException;
	public void makeAllColorsUnactive() throws MYSQLException;
	public String getTheCurrentColor() throws MYSQLException;
}
