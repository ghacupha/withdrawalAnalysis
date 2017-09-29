package com.gha.wa.controller;

import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gha.wa.api.DbaseController;
import com.gha.wa.api.RawDebitDao;
import com.gha.wa.model.data.RawDebitDaoImpl;
import com.google.common.base.Optional;

/**
 * To control insertion of data into the database
 * 
 * A collection will be filled with 5000 objects then the objects
 * will be written to the database to allow for efficient operations
 * over a large data set
 * 
 * @author edwin.njeru
 *
 */
public class DbaseControllerImpl implements DbaseController{
	
	private int itemsRead;	
	private int sheetsRead;
	
	private RawDebitDao rawDebitDao;
	
	private final Logger log = LoggerFactory.getLogger(DbaseControllerImpl.class);
	private static final DbaseController instance =new DbaseControllerImpl();
	

	private DbaseControllerImpl() {
		
		rawDebitDao = RawDebitDaoImpl.getInstance();
		itemsRead = 0;
		sheetsRead = 0;
	}
	
	public void addCollection(Workbook workbook) {
		
		Iterator<Sheet> sheetIterator = workbook.iterator();
		
		while(sheetIterator.hasNext()) {
						
			Sheet sheet = sheetIterator.next();
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			int numRows = sheet.getPhysicalNumberOfRows();
			
			int numRowsRemaining = numRows;
			
			log.info("Iterating over sheet # {} with {} rows", ++sheetsRead, numRows);
			
			List<Row> rowList = bufferRows(rowIterator).get();
			
			//TODO enhance this class
			rawDebitDao.saveAll(rowList);
			
		}// end sheet iteration
		
	}// end addCollection()

	
	private Optional<List<Row>> bufferRows(Iterator<Row> rowIterator) {

		List<Row> rowList = new FastList<>();
		
		while(rowIterator.hasNext()) {
			
			rowList.add(rowIterator.next());
		}
		
		return Optional.of(rowList);
	}
	
	

	public static DbaseController getInstance() {
		return instance;
	}

}
