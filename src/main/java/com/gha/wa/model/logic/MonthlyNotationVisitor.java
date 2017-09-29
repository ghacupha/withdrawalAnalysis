package com.gha.wa.model.logic;

import java.time.format.DateTimeFormatter;

import com.gha.wa.api.Visitable;
import com.gha.wa.api.Visitor;

public class MonthlyNotationVisitor implements Visitor {
	
	private static final Visitor instance = new MonthlyNotationVisitor();
	
	@Override
	public void visit(Visitable visitable) {

		visitable.setMonth(getMonth(visitable));
		
	}



	private String getMonth(Visitable visitable) {
		
		DateTimeFormatter monthly = DateTimeFormatter.ofPattern("MMMM yy");
		
		return visitable.getDate().format(monthly);
	}



	public static Visitor getInstance() {
		return instance;
	}

}
