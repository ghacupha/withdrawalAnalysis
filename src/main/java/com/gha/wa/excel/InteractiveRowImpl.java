package com.gha.wa.excel;

import java.util.Map;

import org.eclipse.collections.impl.map.mutable.UnifiedMap;

class InteractiveRowImpl implements InteractiveRow{
	
	public static InteractiveRow createInteractiveRow(ColumnTitles titles) {
		return new InteractiveRowImpl(titles);
	}

	private final ColumnTitles columnTitles;
	
	private Map<ColumnTitle,Object> rowMap = null;

	public InteractiveRowImpl(ColumnTitles titles) {
		
		this.columnTitles = titles;
		
		rowMap = new UnifiedMap<>(columnTitles.getTitleSet().size());
		
	}

	@Override
	public void addFieldItem(String title, Object value) {
		
		rowMap.put(new ColumnTitle(title), value);
	}

	@Override
	public void removeFieldItem(String title) {
		
		rowMap.remove(new ColumnTitle(title));
	}

	@Override
	public Object getCellValue(String title) {
		
		return rowMap.get(new ColumnTitle(title));
	}
	
	

}
