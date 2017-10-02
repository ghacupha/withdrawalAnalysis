package com.gha.wa.api;

import java.util.Collection;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.gha.wa.model.MonthlyDebit;
import com.gha.wa.model.RawDebit;
import com.google.common.base.Optional;

/**
 * Basic CRUD for RawDebit
 * @author edwin.njeru
 *
 */
public interface RawDebitDao {
	
	void createRawDebit(RawDebit rawDebit);
	
	void saveAll(Optional<Collection<RawDebit>> rawDebits);
	
	RawDebit getByIdRawDebit(int id);
	
	void updateRawDebitId(int id,RawDebit valueObject);
	
	void deleteRawDebit(int id);
	
	List<MonthlyDebit> getMontlyDebits();
	
	public void saveAllMonths(Optional<Collection<MonthlyDebit>> monthlyDebit);

	void saveAll(List<Row> rowList); 

}
