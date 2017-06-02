package model.dao;

import exceptions.MySQLExseption;

public interface IDBColorDAO {
	
	public void addNewColor(String color) throws MySQLExseption;
	public void changeColor(String newColor) throws MySQLExseption;
	public boolean isThereSuchColor(String color) throws MySQLExseption;
	public void makeAllColorsUnactive() throws MySQLExseption;
	public String getTheCurrentColor() throws MySQLExseption;
}
