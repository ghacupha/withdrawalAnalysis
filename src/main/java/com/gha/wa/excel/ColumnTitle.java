/**
 * 
 */
package com.gha.wa.excel;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Encapsulates the title of a column in excel, which is really just a string
 * but decorated with implementation of the comparable interface
 * 
 * @author edwin.njeru
 *
 */
public class ColumnTitle implements Comparable<ColumnTitle> {

	static int titleCount;

	private final int id;

	private final String title;

	/**
	 * Only way to create a column title is to provide a string name
	 * 
	 * @param title
	 */
	ColumnTitle(String title) {

		titleCount++;

		id = ColumnTitle.titleCount + 1;

		this.title = title;
	}

	/**
	 * @return the id
	 */
	public synchronized int getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public synchronized String getTitle() {
		return title;
	}

	
	@Override
	public int compareTo(ColumnTitle o) {

		return ComparisonChain.start().compare(this.id, o.getId()).result();
	}

	
	@Override
	public String toString() {
		return "ColumnTitle [title=" + title + "]";
	}

	@Override
	public int hashCode() {
		
		return Objects.hashCode(id,title);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null ) return false;
		if(getClass() != obj.getClass()) return false;
		final ColumnTitle other = (ColumnTitle) obj;
		
		return Objects.equal(id, other.getId())
				&&Objects.equal(this.title,other.title);
	}
}
