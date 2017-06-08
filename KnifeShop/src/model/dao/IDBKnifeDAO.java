package model.dao;

import java.util.ArrayList;

import exceptions.MYSQLException;
import model.classes.Knife;
//import model.classes.Knife.Lock;
//import model.classes.Knife.Manufactor;
//import model.classes.Knife.Steel;

public interface IDBKnifeDAO{
	
	public void addKnifeToDB(Knife knife) throws MYSQLException;
	public void addSteelToKnifeInDB(String steel) throws MYSQLException;
	public void addManufactorToKnifeInDB(String manufactor) throws MYSQLException;
	public void addLockToKnifeInDB(String lock) throws MYSQLException;
	public int getLastInsertedId() throws MYSQLException;
	public void addManufactorToDB(String manufactorName) throws MYSQLException;
	public ArrayList<String> getAllManufactorsNamesFromDB() throws MYSQLException;
	public void addSteelToDB(String steelName) throws MYSQLException;
	public ArrayList<String> getAllSteelsNamesFromDB() throws MYSQLException;
	public void addLockToDB(String lockName) throws MYSQLException;
	public ArrayList<String> getAllLocksNamesFromDB() throws MYSQLException;
	public boolean isThereSuchManufactorInDB(String manufactor) throws MYSQLException;
	public boolean isThereSuchLockInDB(String lock) throws MYSQLException;
	public boolean isThereSuchSteelInDB(String steel) throws MYSQLException;
	public ArrayList<Knife> getAllKnifesFromDB() throws MYSQLException;
	public String getKnifeSteel(int knifeId) throws MYSQLException;
	public String getKnifeManufactor(int knifeId) throws MYSQLException;
	public String getKnifeLock(int knifeId) throws MYSQLException;
	public int getIdSteelBySteelName(String steel) throws MYSQLException;
	public int getIdManufactorByManufactorName(String manufactor) throws MYSQLException;
	public int getIdLockByLockName(String lock) throws MYSQLException;
	public String getSteelNameBySteelId(int steelId) throws MYSQLException;
	public String getManufactorNameByManufactorId(int manufactorId) throws MYSQLException;
	public String getLockNameByLockId(int lockId) throws MYSQLException;
	public void addImageToDB(String imageName) throws MYSQLException;
	public String getKnifeImageName(int knifeId) throws MYSQLException;
	public void addQuantityToDB(int quantity, int knifeId) throws MYSQLException;
	public int getQuantityForCurrentKnifeByKnifeId(int knifeId) throws MYSQLException;
	public void removeKnifeFromDBByKnifeId(int knifeId) throws MYSQLException;
	public void deleteFromTableWithKnifeIdAndManufactorIdByKnifeId(int knifeId) throws MYSQLException;
	public void deleteFromTableWithKnifeIdAndLockIdByKnifeId(int knifeId) throws MYSQLException;
	public void deleteFromTableWithKnifeIdAndSteelIdByKnifeId(int knifeId) throws MYSQLException;
	public void deleteFromTableWithKnifeIdAndImageIdByKnifeId(int knifeId) throws MYSQLException;
	public Knife getKnifeByKnifeId(int knifeId) throws MYSQLException;
	public boolean isThereEnaughtQuantityFromThisKnifeByKnifeId(int knifeId, int requestedQuantity) throws MYSQLException;
	public int getQuantityByKnifeId(int knifeId) throws MYSQLException;
	public void decreezeKnifeQuantityByKnifeId(int knifeId, int quantityToDecreezeWith) throws MYSQLException;
	public Knife getKnifeWithQunatityInTheBasketByKnifeId(int knifeId, int userId) throws MYSQLException;
	public void addSoldKnifeQuantity(int knifeId, int quantityToAdd) throws MYSQLException;
	public int getNumberOfSoldKnifes(int knifeId) throws MYSQLException;
	public ArrayList<Knife> getTheThreeBestSellers() throws MYSQLException;
	public ArrayList<Knife> getAllSoldKnifes() throws MYSQLException;
	public ArrayList<Knife> getTheThreeCheapest() throws MYSQLException;
	public ArrayList<Knife> getTheThreeMostExpensive() throws MYSQLException;
	public ArrayList<Knife> getTheThreeWithHighestQuantity() throws MYSQLException;
	public ArrayList<Knife> getTheThreeWithLowestQuantity() throws MYSQLException;
	public Knife getKnifeForBestsellers(int idKnife) throws MYSQLException;
	public ArrayList<Knife> getAllKnifesSortByHighestPrice() throws MYSQLException;
	public ArrayList<Knife> getAllKnifesSortByHighestLength() throws MYSQLException;
	public ArrayList<Knife> getAllKnifesSortByLowestPrice() throws MYSQLException;
	public ArrayList<Knife> getAllKnifesSortByLowestLength() throws MYSQLException;
	public ArrayList<Knife> getLastTreeByQuantityMoreThanZero() throws MYSQLException;
}
