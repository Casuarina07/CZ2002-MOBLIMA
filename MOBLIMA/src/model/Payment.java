package model;

public class Payment {
	private Customer customer;
	private String transactionID;
	private double totalAmount;

	public Payment(Customer customer, String transactionID, double totalAmount) {
		super();
		this.customer = customer;
		this.transactionID = transactionID;
		this.totalAmount = totalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

}