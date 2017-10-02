/**
 * 
 */
package com.gha.wa.excel;

import org.apache.poi.ss.usermodel.Row;

import com.google.common.base.Optional;

/**
 * This interface provides methods that can be used to create objects
 * dynamically at runtime from an excel file
 * 
 * @author edwin.njeru
 *
 */
public interface DynamicExcelReader {
	
	/**
	 * Creates an optional rowObject from a row
	 * 
	 * @param row as defined in the excel POI library
	 * @return Optional.of(rowObject);
	 */
	public Optional<InteractiveRow> readRowObject(Row row);

}
