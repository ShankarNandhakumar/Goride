package com.ta.Goride.dto;

import java.sql.Timestamp;

public class Transaction {

	private	int transactionId;
		private int driverid;
		private int passangerid;
		private String transactionType;
		private double transactionAmount;
		public Timestamp getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(Timestamp transactionDate) {
			this.transactionDate = transactionDate;
		}



		private Timestamp transactionDate;
		private String pickuppoint;
		private String droppoint;
		
		public int getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}
		public int getDriverid() {
			return driverid;
		}
		public void setDriverid(int driverid) {
			this.driverid = driverid;
		}
		public int getPassangerid() {
			return passangerid;
		}
		public void setPassangerid(int passangerid) {
			this.passangerid = passangerid;
		}
		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		public double getTransactionAmount() {
			return transactionAmount;
		}
		public void setTransactionAmount(double transactionAmount) {
			this.transactionAmount = transactionAmount;
		}
		public String getPickuppoint() {
			return pickuppoint;
		}
		public void setPickuppoint(String pickuppoint) {
			this.pickuppoint = pickuppoint;
		}
		public String getDroppoint() {
			return droppoint;
		}
		public void setDroppoint(String droppoint) {
			this.droppoint = droppoint;
		}
		@Override
		public String toString() {
			return "Transaction [transactionId=" + transactionId + ", driverid=" + driverid + ", passangerid="
					+ passangerid + ", transactionType=" + transactionType + ", transactionAmount=" + transactionAmount
					+ ", transactionDate=" + transactionDate + ", pickuppoint=" + pickuppoint + ", droppoint="
					+ droppoint + "]";
		}
	
		
	
}
