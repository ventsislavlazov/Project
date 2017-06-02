package model.classes;

public class Transaction {
	
	private int transactionId;
	private int allMoney;
	private boolean addedMoney;
	private String event;
	private int transactedMoney;
	
	
	public Transaction(int transactionId, int allMoney, boolean addedMoney, String event) {
		this.transactionId = transactionId;
		this.allMoney = allMoney;
		this.addedMoney = addedMoney;
		this.event = event;
	}
	
	public Transaction(int transactionId, int allMoney, boolean addedMoney, String event, int transactedMoney) {
		this.transactionId = transactionId;
		this.allMoney = allMoney;
		this.addedMoney = addedMoney;
		this.event = event;
		this.transactedMoney = transactedMoney;
	}
	
	public int getTransactedMoney() {
		return transactedMoney;
	}

	public void setTransactedMoney(int transactedMoney) {
		this.transactedMoney = transactedMoney;
	}

	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(int allMoney) {
		this.allMoney = allMoney;
	}
	public boolean isAddedMoney() {
		return addedMoney;
	}
	public void setAddedMoney(boolean addedMoney) {
		this.addedMoney = addedMoney;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}

}
