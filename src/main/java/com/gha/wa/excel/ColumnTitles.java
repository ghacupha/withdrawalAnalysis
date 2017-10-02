package com.gha.wa.excel;

import java.util.Set;

/**
 * Provides clients with a way to define the fields of their objects at
 * runtime, reducing dependency on reflection to effect the same.
 * The titles are set as string on initiation in the constructor whose
 * insertion order is maintained by the backing collection in the implementation
 * 
 * @author edwin.njeru
 *
 */
public interface ColumnTitles {
	
	/**
	 * Set of the columns
	 * 
	 * @return Set<ColumnTitle>
	 */
	public Set<ColumnTitle> getTitleSet();
}
