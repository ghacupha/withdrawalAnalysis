/**
 * 
 */
package com.gha.wa.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gha.wa.api.Visitable;
import com.gha.wa.api.Visitor;

/**
 * Objects containing debit transactions as raw data, with native 
 * unreconciled currencies
 * 
 * @author edwin.njeru
 *
 */
@Entity
public class RawDebit implements Visitable{
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private LocalDate date;
	
	@Column
	private String month;
	
	@Column
	private double consAmount;
	
	@Column
	private String number;
	
	@Column
	private String name;
	
	@Column
	private String currency;
	
	@Column
	private double amount;
	
		
	
	/**
	 * @param date
	 * @param number
	 * @param name
	 * @param currency
	 * @param amount
	 */
	public RawDebit(LocalDate date, String number, String name, String currency, double amount) {
		this.date = date;
		this.number = number;
		this.name = name;
		this.currency = currency;
		this.amount = amount;
	}



	public RawDebit() {
		
	}
	
	

	@Override
	public void accept(Visitor visitor) {
		
		visitor.visit(this);
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
	 * @return the date
	 */
	public synchronized LocalDate getDate() {
		return date;
	}



	/**
	 * @param date the date to set
	 */
	public synchronized void setDate(LocalDate date) {
		this.date = date;
	}



	/**
	 * @return the number
	 */
	public synchronized String getNumber() {
		return number;
	}



	/**
	 * @param number the number to set
	 */
	public synchronized void setNumber(String number) {
		this.number = number;
	}



	/**
	 * @return the name
	 */
	public synchronized String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public synchronized void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the currency
	 */
	public synchronized String getCurrency() {
		return currency;
	}



	/**
	 * @param currency the currency to set
	 */
	public synchronized void setCurrency(String currency) {
		this.currency = currency;
	}

	
	/**
	 * @return the amount
	 */
	public synchronized double getAmount() {
		return amount;
	}



	/**
	 * @param amount the amount to set
	 */
	public synchronized void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * @return the month
	 */
	public synchronized String getMonth() {
		return month;
	}



	/**
	 * @param month the month to set
	 */
	public synchronized void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the consAmount
	 */
	public synchronized double getConsAmount() {
		return consAmount;
	}



	/**
	 * @param consAmount the consAmount to set
	 */
	public synchronized void setConsAmount(double consAmount) {
		this.consAmount = consAmount;
	}



	/**
	 * Copies data from the value object into the object
	 * 
	 * @param rawDebitVo
	 */
	public void copy(RawDebit rawDebitVo) {
		
		this.amount = rawDebitVo.getAmount();
		this.currency = rawDebitVo.getCurrency();
		this.date = rawDebitVo.getDate();
		this.name = rawDebitVo.getName();
		this.number = rawDebitVo.getNumber();
		
	}
	
	
}
