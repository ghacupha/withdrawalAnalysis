package com.gha.wa.model.logic;

public enum FxRates {
	
	KES(1.00),
	USD(103.88),
	GBP(136.36850),
	EUR(122.05),
	CHF(107.1536),
	ZAR(7.8771),
	JPY(1.06397),
	INR(1.6178),
	CAD(83.1273),
	AED(28.2821),
	CNY(15.43830),
	TZS(0.04632),
	UGX(0.02881);
	
	public double rate;
	
	FxRates(double rate){
		
		this.rate = rate;
	}

}
