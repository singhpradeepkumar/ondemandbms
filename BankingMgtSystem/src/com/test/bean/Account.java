package com.test.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

abstract public class Account {
	
	private static long accNumber = 0;

	private double balance = 0.0;
	private final long accountNumber = Account.getAccNumber();
	private List<Transaction> transactions = new ArrayList<>();

	public boolean deposit(double amt, String transstr) {
		if (transstr==null) {
			transstr = "Amount deposited";
		}
		if (amt > 0) {
			balance = balance + amt;
			Transaction trans = new Transaction(Transaction.getCounter(), new Date(), transstr, balance - amt, amt, balance);
			getTransactions().add(trans);
		}
		return (amt > 0);
	}
	
	public boolean transfer(double amt, Account toAccount) {
		if (this.getBalance() > 0 && this.getBalance() >= amt) {
			if (toAccount.deposit(amt, null)) {
				this.withdraw(amt);
				Transaction trans = new Transaction(Transaction.getCounter(), new Date(), "Transfer to Account:" + toAccount.getAccountNumber(), this.getBalance() + amt, amt, this.getBalance());
				getTransactions().add(trans);
				return true;
			}
		}
		return false;
	}
	
	abstract boolean withdraw(double amt);
	abstract double withdrawalLimit();
	
	public List<Transaction> getTransactionHistory() {
		return new ArrayList<Transaction>(getTransactions());
	}
	
	public double getBalance() {
		return balance;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public static long getAccNumber() {
		return ++accNumber;
	}

	public long getAccountNumber() {
		return accountNumber;
	}
	
}
