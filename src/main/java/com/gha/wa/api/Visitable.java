package com.gha.wa.api;

import java.time.LocalDate;

public interface Visitable {
	
	void accept(Visitor visitor);

	void setMonth(String month);

	LocalDate getDate();

	void setConsAmount(double consolidatedCCY);

	String getCurrency();

	double getAmount();


}
