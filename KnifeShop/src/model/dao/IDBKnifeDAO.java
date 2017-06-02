package model.dao;

import java.util.ArrayList;

import exceptions.MySQLExseption;
import model.classes.Knife;
//import model.classes.Knife.Lock;
//import model.classes.Knife.Manufactor;
//import model.classes.Knife.Steel;

public interface IDBKnifeDAO{
	
	public void addKnifeToDB(Knife knife) throws MySQLExseption;
	public void addSteelToKnifeInDB(String steel) throws MySQLExseption;
	public void addManufactorToKnifeInDB(String manufactor) throws MySQLExseption;
	public void addLockToKnifeInDB(String lock) throws MySQLExseption;
	public int getLastInsertedId() throws MySQLExseption;
	public void addManufactorToDB(String manufactorName) throws MySQLExseption;
	public ArrayList<String> getAllManufactorsNamesFromDB() throws MySQLExseption;
	public void addSteelToDB(String steelName) throws MySQLExseption;
	public ArrayList<String> getAllSteelsNamesFromDB() throws MySQLExseption;
	public void addLockToDB(String lockName) throws MySQLExseption;
	public ArrayList<String> getAllLocksNamesFromDB() throws MySQLExseption;
	public boolean isThereSuchManufactorInDB(String manufactor) throws MySQLExseption;
	public boolean isThereSuchLockInDB(String lock) throws MySQLExseption;
	public boolean isThereSuchSteelInDB(String steel) throws MySQLExseption;
	public ArrayList<Knife> getAllKnifesFromDB() throws MySQLExseption;
	public String getKnifeSteel(int knifeId) throws MySQLExseption;
	public String getKnifeManufactor(int knifeId) throws MySQLExseption;
	public String getKnifeLock(int knifeId) throws MySQLExseption;
	public int getIdSteelBySteelName(String steel) throws MySQLExseption;
	public int getIdManufactorByManufactorName(String manufactor) throws MySQLExseption;
	public int getIdLockByLockName(String lock) throws MySQLExseption;
	public String getSteelNameBySteelId(int steelId) throws MySQLExseption;
	public String getManufactorNameByManufactorId(int manufactorId) throws MySQLExseption;
	public String getLockNameByLockId(int lockId) throws MySQLExseption;
	public void addImageToDB(String imageName) throws MySQLExseption;
	public String getKnifeImageName(int knifeId) throws MySQLExseption;
	public void addQuantityToDB(int quantity, int knifeId) throws MySQLExseption;
	public int getQuantityForCurrentKnifeByKnifeId(int knifeId) throws MySQLExseption;
	public void removeKnifeFromDBByKnifeId(int knifeId) throws MySQLExseption;
	public void deleteFromTableWithKnifeIdAndManufactorIdByKnifeId(int knifeId) throws MySQLExseption;
	public void deleteFromTableWithKnifeIdAndLockIdByKnifeId(int knifeId) throws MySQLExseption;
	public void deleteFromTableWithKnifeIdAndSteelIdByKnifeId(int knifeId) throws MySQLExseption;
	public void deleteFromTableWithKnifeIdAndImageIdByKnifeId(int knifeId) throws MySQLExseption;
	public Knife getKnifeByKnifeId(int knifeId) throws MySQLExseption;
	public boolean isThereEnaughtQuantityFromThisKnifeByKnifeId(int knifeId, int requestedQuantity) throws MySQLExseption;
	public int getQuantityByKnifeId(int knifeId) throws MySQLExseption;
	public void decreezeKnifeQuantityByKnifeId(int knifeId, int quantityToDecreezeWith) throws MySQLExseption;
	public Knife getKnifeWithQunatityInTheBasketByKnifeId(int knifeId, int userId) throws MySQLExseption;
	public void addSoldKnifeQuantity(int knifeId, int quantityToAdd) throws MySQLExseption;
	public int getNumberOfSoldKnifes(int knifeId) throws MySQLExseption;
	public ArrayList<Knife> getTheThreeBestSellers() throws MySQLExseption;
	public ArrayList<Knife> getAllSoldKnifes() throws MySQLExseption;
	public ArrayList<Knife> getTheThreeCheapest() throws MySQLExseption;
	public ArrayList<Knife> getTheThreeMostExpensive() throws MySQLExseption;
	public ArrayList<Knife> getTheThreeWithHighestQuantity() throws MySQLExseption;
	public ArrayList<Knife> getTheThreeWithLowestQuantity() throws MySQLExseption;
	public Knife getKnifeForBestsellers(int idKnife) throws MySQLExseption;
	public ArrayList<Knife> getAllKnifesSortByHighestPrice() throws MySQLExseption;
	public ArrayList<Knife> getAllKnifesSortByHighestLength() throws MySQLExseption;
	public ArrayList<Knife> getAllKnifesSortByLowestPrice() throws MySQLExseption;
	public ArrayList<Knife> getAllKnifesSortByLowestLength() throws MySQLExseption;
}
