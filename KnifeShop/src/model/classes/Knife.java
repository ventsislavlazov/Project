package model.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.google.gson.JsonObject;

public class Knife {
	
//	public enum Steel{
//		D2, N690, ELMAX, S30V, S35VN
//    } 
//	
//	public enum Manufactor{
//		SPYDERCO, Viper, SOG, BENCHMADE, ZEROTOLERANCE
//    } 
	
//	public enum Lock{
//		LINERLOCK, FRAMELOCK, BACKLOCK, BOLTACTION, COMPRESSIONLOCK
//    } 
	
//	//wsi4ki proizwoditeli
//	public ArrayList<String> getManufactorNames(){
//		ArrayList<String> manufactorsNames = new ArrayList<>();
//		for (Manufactor m : Manufactor.values()) {
//			manufactorsNames.add(m.toString());
//		}
//		return manufactorsNames;
//	}
//	
//	//wsi4ki stomani
//		public ArrayList<String> getSteelNames(){
//			ArrayList<String> steelNames = new ArrayList<>();
//			for (Steel s : Steel.values()) {
//				steelNames.add(s.toString());
//			}
//			return steelNames;
//		}
		
//		//wsi4ki zaklu4waniq
//		public ArrayList<String> getLockNames(){
//			ArrayList<String> lockNames = new ArrayList<>();
//			for (Lock m : Lock.values()) {
//				lockNames.add(m.toString());
//			}
//			return lockNames;
//		}

	private int id;
	private int length;
//	private Steel steel;
//	private Manufactor manufactor;
	private String steel;
	private String manufactor;
	private String model;
	private int price;
//	private Lock lock;
	private String lock;
	private boolean folder;
	private String imageName;
	private int quantity;
	private int quantityBasket;
	private int numberOfSells;
//	private String currentDateTimeWhenIsAddedToTheBasket;
	private LocalDateTime currentDateTimeWhenIsAddedToTheBasket;
	
	//konstruktori
//	public Knife(int length, Steel steel, Manufactor manufactor, String model, int price, Lock lock, boolean folder) {
//		this.length = length;
//		this.steel = steel;
//		this.manufactor = manufactor;
//		this.model = model;
//		this.price = price;
//		this.lock = lock;
//		this.folder = folder;
//	}
	
//	public String getCurrentDateTimeWhenIsAddedToTheBasket() {
//		return currentDateTimeWhenIsAddedToTheBasket;
//	}
//
//	public void setCurrentDateTimeWhenIsAddedToTheBasket(String currentDateTimeWhenIsAddedToTheBasket) {
//		this.currentDateTimeWhenIsAddedToTheBasket = currentDateTimeWhenIsAddedToTheBasket;
//	}

	public LocalDateTime getCurrentDateTimeWhenIsAddedToTheBasket() {
		return currentDateTimeWhenIsAddedToTheBasket;
	}

	public void setCurrentDateTimeWhenIsAddedToTheBasket(LocalDateTime currentDateTimeWhenIsAddedToTheBasket) {
		this.currentDateTimeWhenIsAddedToTheBasket = currentDateTimeWhenIsAddedToTheBasket;
	}

	public int getNumberOfSells() {
		return numberOfSells;
	}

	public void setNumberOfSells(int numberOfSells) {
		this.numberOfSells = numberOfSells;
	}
	
	public Knife(int id,int length, String steel, String manufactor, String model, int price, String lock, boolean folder, int numberOfSells, String imageName){
		this.id = id;
		this.length = length;
		this.steel = steel;
		this.manufactor = manufactor;
		this.model = model;
		this.price = price;
		this.lock = lock;
		this.folder = folder;
		this.imageName = imageName;
		this.numberOfSells = numberOfSells;
	}

	public Knife(int id,int length, String steel, String manufactor, String model, int price, String lock, boolean folder, String imageName, int quantity, int quantityBasket){
		this.id = id;
		this.length = length;
		this.steel = steel;
		this.manufactor = manufactor;
		this.model = model;
		this.price = price;
		this.lock = lock;
		this.folder = folder;
		this.imageName = imageName;
		this.quantity = quantity;
		this.quantityBasket = quantityBasket;
	}
	
	public Knife(int length, String steel, String manufactor, String model, int price, String lock, boolean folder, String imageName, int quantity, int quantityBasket){
		this.length = length;
		this.steel = steel;
		this.manufactor = manufactor;
		this.model = model;
		this.price = price;
		this.lock = lock;
		this.folder = folder;
		this.imageName = imageName;
		this.quantity = quantity;
		this.quantityBasket = quantityBasket;
	}
	
	public Knife(int length, String steel, String manufactor, String model, int price, String lock, boolean folder, String imageName) {
		this.length = length;
		this.steel = steel;
		this.manufactor = manufactor;
		this.model = model;
		this.price = price;
		this.lock = lock;
		this.folder = folder;
		this.imageName = imageName;
	}
	
	public Knife(int id, int length, String steel, String manufactor, String model, int price, String lock, boolean folder, String imageName) {
		this.id = id;
		this.length = length;
		this.steel = steel;
		this.manufactor = manufactor;
		this.model = model;
		this.price = price;
		this.lock = lock;
		this.folder = folder;
		this.imageName = imageName;
	}
	
	public Knife(int id, int length, String steel, String manufactor, String model, int price, String lock, boolean folder, String imageName, int quantity) {
		this.id = id;
		this.length = length;
		this.steel = steel;
		this.manufactor = manufactor;
		this.model = model;
		this.price = price;
		this.lock = lock;
		this.folder = folder;
		this.imageName = imageName;
		this.quantity = quantity;
	}
	
	public Knife(int length, String steel, String manufactor, String model, int price, String lock, boolean folder, String imageName, int quantity) {
		this.length = length;
		this.steel = steel;
		this.manufactor = manufactor;
		this.model = model;
		this.price = price;
		this.lock = lock;
		this.folder = folder;
		this.imageName = imageName;
		this.quantity = quantity;
	}
	
	public Knife(){
		
	}
	
	//geteri i seteri
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
//	public Steel getSteel() {
//		return steel;
//	}
//	public void setSteel(Steel steel) {
//		this.steel = steel;
//	}
//	public Manufactor getManufactor() {
//		return manufactor;
//	}
//	public void setManufactor(Manufactor manufactor) {
//		this.manufactor = manufactor;
//	}
	
	
	public String getModel() {
		return model;
	}
	public String getSteel() {
		return steel;
	}

	public void setSteel(String steel) {
		this.steel = steel;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	public void setModel(String model) {
		this.model = model;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
//	public Lock getLock() {
//		return lock;
//	}
//	public void setLock(Lock lock) {
//		this.lock = lock;
//	}
	public boolean isFolder() {
		return folder;
	}
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}
	public void setFolder(boolean folder) {
		this.folder = folder;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public int getQuantityBasket() {
		return quantityBasket;
	}
	public void setQuantityBasket(int quantityBasket) {
		this.quantityBasket = quantityBasket;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
