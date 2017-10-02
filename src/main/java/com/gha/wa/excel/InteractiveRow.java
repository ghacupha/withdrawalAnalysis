package com.gha.wa.excel;

import org.apache.poi.ss.usermodel.CellType;

/**
 * This object maintains a collection of fields fetched from an excel row, indexed by a 
 * unique key of titleNames
 * 
 * @author edwin.njeru
 *
 */
public interface InteractiveRow {

	/**
	 * Adds a new field to the row object for every cell
	 * 
	 * @param cellIndex
	 * @param object
	 */
	void addFieldItem(String title, Object value);

	/**
	 * Removes a field for the cellIndex specified
	 * 
	 * @param cellIndex
	 */
	void removeFieldItem(String title);
	

	/**
	 * Returns the object at a given cell whole column title is specified
	 * by the argument parameter
	 * 
	 * @param title
	 * @return
	 */
	Object getCellValue(String title);

}