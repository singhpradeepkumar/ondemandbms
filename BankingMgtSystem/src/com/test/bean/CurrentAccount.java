package com.test.bean;

import java.util.Date;

public class CurrentAccount extends Account {

	@Override
	public double withdrawalLimit() {
		return 0;
	}
	
	@Override
	public boolean withdraw(double amt) {
		if (getBalance() > 0 && amt <= getBalance()) {
			this.withdraw(amt);
			Transaction trans = new Transaction(Transaction.getCounter(), new Date(), "Amount withdrawal", getBalance() + amt, amt, getBalance());
			getTransactions().add(trans);
		}
		return (getBalance() > 0 && amt <= getBalance());
	}
	
}
