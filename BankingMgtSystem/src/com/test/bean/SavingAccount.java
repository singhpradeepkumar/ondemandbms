package com.test.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SavingAccount extends Account {
	
	private Date lastInterest = new Date();
	
	@Override
	public double withdrawalLimit() {
		return 100000;
	}
	
	@Override
	public boolean withdraw(double amt) {
		if (getBalance() > 0 && amt <= getBalance() && canWithdraw(amt)) {
			this.withdraw(amt);
			Transaction trans = new Transaction(Transaction.getCounter(), new Date(), "Amount withdrawal", getBalance() + amt, amt, getBalance());
			getTransactions().add(trans);
		}
		return (getBalance() > 0 && amt <= getBalance());
	}
	
	private void calculateInterest() {
		Date todaysDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(todaysDate);
		int diffInDays = (int) ((lastInterest.getTime() -  todaysDate.getTime()) / (1000 * 60 * 60 * 24));
		if (diffInDays >= 6*30) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			for (int i=0; i<6; i++) {
				c.add(Calendar.MONTH, -i);
				String groupDate = sdf.format(c.getTime());
				double total = getTransactions().stream().filter(t -> groupDate.equals(sdf.format(t.getDate())))
				.map(t -> t.getClosingBal()).reduce(0.0, Double::sum);
				
				double intt = (total / 6.0) * 0.025;
				this.deposit(intt, "Interest Credited");
				this.lastInterest = new Date();
			}
		}
	}
	
	private boolean canWithdraw(double amt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String todaysDate = sdf.format(new Date());
		double todaysWithdrawal = getTransactions().stream().filter(t -> {
			return todaysDate.equals(sdf.format(t.getDate()));
		}).map(t -> t.getAmount()).reduce(0.0, Double::sum);
		
		return (todaysWithdrawal + amt) <= withdrawalLimit();
	}

}
