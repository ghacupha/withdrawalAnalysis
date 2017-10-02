package com.gha.wa.model.data;

import org.apache.poi.ss.usermodel.Row;

public class RowObjectTransformer {

	public RowObjectTransformer() {
	}
	
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	private <T>T makeInstanceOf(Class<T> clazz){
		
		T rowObject = null;
		
		try {
			rowObject = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return rowObject;	
	}

}
