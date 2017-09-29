package com.gha.wa.model.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.gha.wa.api.Visitable;
import com.gha.wa.api.Visitor;

public class CcyConsolidationVisitor implements Visitor {
	
	private static final Visitor instance = new CcyConsolidationVisitor();

	private CcyConsolidationVisitor() {
	}

	@Override
	public void visit(Visitable visitable) {
		
		visitable.setConsAmount(consolidatedCCY(visitable)/1000);
	}

	private double consolidatedCCY(Visitable visitable) {
		
		double consCCY = 0.00;

		switch(visitable.getCurrency()) {
		
		case"USD":
			consCCY = round2Dps(visitable.getAmount() * FxRates.USD.rate);
			break;
		case"GBP":
			consCCY = round2Dps(visitable.getAmount() * FxRates.GBP.rate);
			break;
		case"EUR":
			consCCY = round2Dps(visitable.getAmount() * FxRates.EUR.rate);
			break;
		case"CHF":
			consCCY = round2Dps(visitable.getAmount() * FxRates.CHF.rate);
			break;
		case"ZAR":
			consCCY = round2Dps(visitable.getAmount() * FxRates.ZAR.rate);
			break;
		case"UGX":
			consCCY = round2Dps(visitable.getAmount() * FxRates.UGX.rate);
			break;
		case"TZS":
			consCCY = round2Dps(visitable.getAmount() * FxRates.TZS.rate);
			break;
		case"JPY":
			consCCY = round2Dps(visitable.getAmount() * FxRates.JPY.rate);
			break;
		case"INR":
			consCCY = round2Dps(visitable.getAmount() * FxRates.INR.rate);
			break;
		case"CAD":
			consCCY = round2Dps(visitable.getAmount() * FxRates.CAD.rate);
			break;
		case"AED":
			consCCY = round2Dps(visitable.getAmount() * FxRates.AED.rate);
			break;
		case"CNY":
			consCCY = round2Dps(visitable.getAmount() * FxRates.CNY.rate);
			break;
			default:
				consCCY = round2Dps(visitable.getAmount() * FxRates.KES.rate);
		}
		
		return consCCY;
	}

	private double round2Dps(double d) {
		// TODO Auto-generated method stub
		return Double.parseDouble(
				BigDecimal.valueOf(d).setScale(2,RoundingMode.HALF_EVEN).toString()
				);
	}

	/**
	 * @return the instance
	 */
	public static synchronized Visitor getInstance() {
		return instance;
	}

	
}
