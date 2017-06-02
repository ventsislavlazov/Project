package model.classes;

import java.util.ArrayList;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private int age;
	private boolean admin;
	private boolean masterAdmin;
	private ArrayList<Knife> knifesInBasket = new ArrayList<>();
	
	//konstruktori
	public User(int id, String username, String password, String email, int age, ArrayList<Knife> knifesInBasket) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.knifesInBasket = knifesInBasket;
	}
	
	public User(String username, String password, String email, int age) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
	}
	
	public User(int id, String username, String email, int age, boolean admin) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.age = age;
		this.admin = admin;
	}
	
	public User(String username, String password, String email, int age, boolean admin) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.admin = admin;
	}
	
	public User(int id, String username, String password, String email, int age, boolean admin) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.admin = admin;
	}
	
	public User(){
		
	}
	
	//geteri i seteri
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public ArrayList<Knife> getKnifesInBasket() {
		return knifesInBasket;
	}
	public void setKnifesInBasket(ArrayList<Knife> knifesInBasket) {
		this.knifesInBasket = knifesInBasket;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isMasterAdmin() {
		return masterAdmin;
	}
	public void setMasterAdmin(boolean masterAdmin) {
		this.masterAdmin = masterAdmin;
	}

}
