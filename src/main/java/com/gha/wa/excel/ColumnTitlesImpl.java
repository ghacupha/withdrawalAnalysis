package com.gha.wa.excel;

import java.util.Set;
import java.util.TreeSet;

/**
 * This class work after the convention that the column title resonates with the fields in
 * java bean into which the excel data is supposed to be mapped.
 * This provides a way for clients to declare fields to be mapped at runtime reducing the
 * dependency on reflection to acquire such fields
 * On creation varArgs are fed into the constructor and those then consist the fields of the
 * object which is in turn created at runtime
 * The backing collection used is the treeSet which has a better performance than the linkedHashSet.
 * while still maintaining order.
 * However this means we have  to implement the comparable interface which is why we
 * are decorating the string with ColumnTitle which implements the comparable.
 * That detail is however hidden as we use the ColumnTitles an clients will never know about
 * the decoration and comparable implementation
 * 
 * @author edwin.njeru
 *
 */
public class ColumnTitlesImpl implements ColumnTitles {
	
	public static ColumnTitlesImpl createColumnTitles(String... titles) {
		return new ColumnTitlesImpl(titles);
	}

	private final Set<ColumnTitle> titleSet = new TreeSet<>();

	/**
	 * Creates a collection of ColumnTitles with VarArgs for fields as arguments
	 * 
	 * @param titles
	 */
	ColumnTitlesImpl(String... titles) {
		
		for(String title : titles) {
			
			titleSet.add(new ColumnTitle(title));
		}
	}
	
	/**
	 * Add additional title to the collection
	 * 
	 * @param titleName
	 */
	public void addTitle(String titleName) {
		
		titleSet.add(new ColumnTitle(titleName));
	}
	
	/**
	 * Remove a title from the collection
	 * 
	 * @param titleName
	 */
	public void removeTitle(String titleName) {
		
		titleSet.remove(new ColumnTitle(titleName));
	}
	
	/**
	 * Set of the columns
	 * 
	 * @return Set<ColumnTitle>
	 */
	public Set<ColumnTitle> getTitleSet(){
		
		return titleSet;
	}

}
