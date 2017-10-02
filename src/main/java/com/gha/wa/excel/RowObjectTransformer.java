package com.gha.wa.excel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;

public class RowObjectTransformer {

	public static RowObjectTransformer createRowObjectTransformer() {
		return new RowObjectTransformer();
	}

	RowObjectTransformer() {
	}

	public <T>T createRowObject(InteractiveRow rowObject,Class<T> clazz,ColumnTitles titles){

		T rowClass = makeInstanceOf(clazz);		
		Field[] classFields = getBeanFields(clazz);

		try {
			rowClass = populateFields(titles, rowClass, rowObject,classFields);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return rowClass;
	}

	/**
	 * Uses reflection to populate fields fetching data from the rowObject
	 * 
	 * @param titles
	 * @param rowClass
	 * @param classFields
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private <T>T populateFields(ColumnTitles titles, T rowClass,InteractiveRow rowObject, Field[] classFields)
			throws IllegalAccessException, InvocationTargetException {

		Iterator<ColumnTitle> titleIterator = titles.getTitleSet().iterator();

		while(titleIterator.hasNext()) {
			
			ColumnTitle colTitle = titleIterator.next();
			
			BeanUtils.setProperty(rowClass, colTitle.getTitle(), rowObject.getCellValue(colTitle.getTitle()));

		}
		
		return rowClass;
	}
	
	/**
	 * Creates a runtime instance of a provided class
	 * 
	 * @param clazz
	 * @return
	 */
	private <T>T makeInstanceOf(Class<T> clazz){

		T rowObject = null;

		try {
			rowObject = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return rowObject;	
	}

	/**
	 * Creates an array of the fields in a bean
	 * @param <T>
	 * 
	 * @param clazz
	 * @return
	 */
	private <T> Field[] getBeanFields(Class<T> clazz) {

		Field[] beanFields = null;

		try {
			beanFields = clazz.getDeclaredFields();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return beanFields;
	}

}
