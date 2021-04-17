package com.test.bean;

import java.util.Date;

public class Transaction {
	private static long counter = 0;

	private long srno;
	private Date date;
	private String details;
	private double openingBal;
	private double amount;
	private double closingBal;

	public static long getCounter() {
		return ++counter;
	}

	public Transaction(long srno, Date date, String details, double openingBal, double amount, double closingBal) {
		super();
		this.srno = srno;
		this.date = date;
		this.details = details;
		this.openingBal = openingBal;
		this.amount = amount;
		this.closingBal = closingBal;
	}



	public long getSrno() {
		return srno;
	}

	public Transaction setSrno(long srno) {
		this.srno = srno;
		return this;
	}

	public Date getDate() {
		return date;
	}

	public Transaction setDate(Date date) {
		this.date = date;
		return this;
	}

	public String getDetails() {
		return details;
	}

	public Transaction setDetails(String details) {
		this.details = details;
		return this;
	}

	public double getOpeningBal() {
		return openingBal;
	}

	public Transaction setOpeningBal(double openingBal) {
		this.openingBal = openingBal;
		return this;
	}

	public double getAmount() {
		return amount;
	}

	public Transaction setAmount(double amount) {
		this.amount = amount;
		return this;
	}

	public double getClosingBal() {
		return closingBal;
	}

	public Transaction setClosingBal(double closingBal) {
		this.closingBal = closingBal;
		return this;
	}

}
