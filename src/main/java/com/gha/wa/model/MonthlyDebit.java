package com.gha.wa.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MonthlyDebit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String month;
	
	@Column
	private double consAmount;
	
	
	
	/**
	 * @param month
	 * @param consAmount
	 */
	public MonthlyDebit(String month, double consAmount) {
		this.month = month;
		this.consAmount = Double.parseDouble(
				BigDecimal.valueOf(consAmount).setScale(2,RoundingMode.HALF_EVEN).toString());
	}

	/**
	 * 
	 */
	public MonthlyDebit() {
	}

	/**
	 * @return the id
	 */
	public synchronized int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public synchronized void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the monthName
	 */
	public synchronized String getMonthName() {
		return month;
	}

	/**
	 * @param monthName the monthName to set
	 */
	public synchronized void setMonthName(String monthName) {
		this.month = monthName;
	}

	/**
	 * @return the monthlyTotal
	 */
	public synchronized double getMonthlyTotal() {
		return consAmount;
	}

	/**
	 * @param monthlyTotal the monthlyTotal to set
	 */
	public synchronized void setMonthlyTotal(double monthlyTotal) {
		this.consAmount = monthlyTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		long temp;
		temp = Double.doubleToLongBits(consAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MonthlyDebit)) {
			return false;
		}
		MonthlyDebit other = (MonthlyDebit) obj;
		if (id != other.id) {
			return false;
		}
		if (month == null) {
			if (other.month != null) {
				return false;
			}
		} else if (!month.equals(other.month)) {
			return false;
		}
		if (Double.doubleToLongBits(consAmount) != Double.doubleToLongBits(other.consAmount)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "MonthlyDebit [id=" + id + ", monthName=" + month + ", monthlyTotal=" + consAmount + "]";
	}
	
	

}
