package model.dao;

import java.util.ArrayList;

import exceptions.MySQLExseption;
import model.classes.Knife;

public interface IDBasketDAO {
	
	public void addKnifeToBasketByKnifeId(int userId, int knifeId, int quantity) throws MySQLExseption;
	public void removeKnifeFromBasketByKnifeId(int userId, int knifeId) throws MySQLExseption;
	public boolean isThereSuchKnifeInTheBasketByKnifeId(int userId, int knifeId) throws MySQLExseption;
	public void addMoreQuantityToKnifeInBasket(int userId, int knifeId, int quantityToAdd) throws MySQLExseption;
	public int getQuantityForKnifeFromBasket(int userId, int knifeId) throws MySQLExseption;
	public ArrayList<Knife> getAllKnifesFromTheBasketByUserId(int userId) throws MySQLExseption;
	public void removeQuantitFromKnifeInTheBasket(int userId, int knifeId, int quantityToRemove) throws MySQLExseption;
	public void removeKnifeFromAllBasketsFromAdminByKnifeId(int knifeId) throws MySQLExseption;
	public void removeQuantityForSpecificUserByUserId(int userId) throws MySQLExseption;
}
