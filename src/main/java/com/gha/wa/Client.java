package com.gha.wa;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

import org.eclipse.collections.impl.list.mutable.FastList;

import com.gha.wa.api.DbaseController;
import com.gha.wa.api.RawDebitDao;
import com.gha.wa.api.WorkbookController;
import com.gha.wa.controller.DbaseControllerImpl;
import com.gha.wa.controller.WorkbookControllerImpl;
import com.gha.wa.model.MonthlyDebit;
import com.gha.wa.model.RawDebit;
import com.gha.wa.model.data.RawDebitDaoImpl;
import com.gha.wa.model.logic.CcyConsolidationVisitor;
import com.gha.wa.model.logic.FxRates;
import com.gha.wa.model.logic.MonthlyNotationVisitor;
import com.google.common.base.Optional;

import io.bloco.faker.Faker;

public class Client {
	
	public static void main(String[] args) {
		
		RawDebitDao rawDebitDao = RawDebitDaoImpl.getInstance();
		
		String file = "C:\\ExcelFilesForAnalysis\\All_Debits.bk.xls";
		
		Collection<RawDebit> rawDebits = new FastList<>();
		
		Faker faker = new Faker();
		
		for(int i = 0; i < 100; i++) {
			
			LocalDate date = 
					faker.date.between("2016-08-01","2017-07-31")
					.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
			String number = faker.business.creditCardNumber();
			String name = faker.name.nameWithMiddle();
			String currency = 
					faker.pickRandom.fromEnum(FxRates.class).toString();
			double amount = 
					Double.parseDouble((faker.commerce.price(50, 10000).toString()));
			
			RawDebit rawDebit = 
					new RawDebit( date, number, name, currency, amount);
			
			rawDebit.accept(MonthlyNotationVisitor.getInstance());
			
			rawDebit.accept(CcyConsolidationVisitor.getInstance());
			
			rawDebits.add(rawDebit);
		}
		
		rawDebitDao.saveAll(Optional.of(rawDebits));
		
		List<MonthlyDebit> monthlyDebits = rawDebitDao.getMontlyDebits();
		
		rawDebitDao.saveAllMonths(Optional.of(monthlyDebits));
		
		for(MonthlyDebit monthlyDebit : monthlyDebits) {
			
			System.out.println(monthlyDebit);
		}
		
		DbaseController dbaseController = DbaseControllerImpl.getInstance();
		
		WorkbookController workbookController = 
				WorkbookControllerImpl.getInstance();
		
		dbaseController.addCollection(workbookController.acquireWorkbook(file).get());
	}
	
}
