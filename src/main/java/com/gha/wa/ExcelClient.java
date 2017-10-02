package com.gha.wa;

import java.util.List;

import org.eclipse.collections.impl.list.mutable.FastList;

import com.gha.wa.api.RawDebitDao;
import com.gha.wa.excel.ColumnTitles;
import com.gha.wa.excel.ColumnTitlesImpl;
import com.gha.wa.excel.InteractiveRow;
import com.gha.wa.excel.RowObjectTransformer;
import com.gha.wa.excel.SimpleExcelReader;
import com.gha.wa.excel.SimpleExcelReaderImpl;
import com.gha.wa.model.RawDebit;
import com.gha.wa.model.data.RawDebitDaoImpl;
import com.google.common.base.Optional;

public class ExcelClient {
	
	private SimpleExcelReader simpleExcelReader;
	
	private RowObjectTransformer rowObjectTrans;
	
	

	public ExcelClient() {
		
		
		
		simpleExcelReader = SimpleExcelReaderImpl.createSimpleExcelReader();
		
		rowObjectTrans = RowObjectTransformer.createRowObjectTransformer();
	}
	

	/**
	 * @return the simpleExcelReader
	 */
	public synchronized SimpleExcelReader getSimpleExcelReader() {
		return simpleExcelReader;
	}



	/**
	 * @return the rowObjectTrans
	 */
	public synchronized RowObjectTransformer getRowObjectTrans() {
		return rowObjectTrans;
	}



	public static void main(String[] args) {
		
		RawDebitDao rawDebitDao = RawDebitDaoImpl.getInstance();
		
		/* Corresponding to fields in the mapped bean*/
		ColumnTitles colTitles = 
				ColumnTitlesImpl.createColumnTitles("date","number","name","currency","amount");
		
		String filePath = "C:\\ExcelFilesForAnalysis\\test_file_data.xls";
		
		ExcelClient client = new ExcelClient();
		
		List<InteractiveRow> rows = 
				client.getSimpleExcelReader().readInteractiveRowObjects(filePath,colTitles);
		
		List<RawDebit> rawDebits = new FastList<>();
		
		for(InteractiveRow row : rows) {
			
			RawDebit rawDebit = client.getRowObjectTrans().createRowObject(row, RawDebit.class, colTitles);	
			
			rawDebits.add(rawDebit);
			
		}
		
		System.out.println("Created a list of rawDebits of size : "+rawDebits.size());
		
		//rawDebitDao.saveAll(Optional.of(rawDebits));
		
		
	}

}
