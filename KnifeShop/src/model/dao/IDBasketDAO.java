package model.dao;

import java.util.ArrayList;

import exceptions.MYSQLException;
import model.classes.Knife;

public interface IDBasketDAO {
	
	public void addKnifeToBasketByKnifeId(int userId, int knifeId, int quantity) throws MYSQLException;
	public void removeKnifeFromBasketByKnifeId(int userId, int knifeId) throws MYSQLException;
	public boolean isThereSuchKnifeInTheBasketByKnifeId(int userId, int knifeId) throws MYSQLException;
	public void addMoreQuantityToKnifeInBasket(int userId, int knifeId, int quantityToAdd) throws MYSQLException;
	public int getQuantityForKnifeFromBasket(int userId, int knifeId) throws MYSQLException;
	public ArrayList<Knife> getAllKnifesFromTheBasketByUserId(int userId) throws MYSQLException;
	public void removeQuantitFromKnifeInTheBasket(int userId, int knifeId, int quantityToRemove) throws MYSQLException;
	public void removeKnifeFromAllBasketsFromAdminByKnifeId(int knifeId) throws MYSQLException;
	public void removeQuantityForSpecificUserByUserId(int userId) throws MYSQLException;
}
