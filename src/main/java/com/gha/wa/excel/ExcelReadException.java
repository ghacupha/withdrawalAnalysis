package com.gha.wa.excel;

/**
 * 
 * @author OSGL authors
 * 
 * @author modified by edwin.njeru
 *
 */
public class ExcelReadException extends UnexpectedException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3823367425086305662L;

	public ExcelReadException(String message) {
        super(message);
    }

    public ExcelReadException(String message, Object... args) {
        super(message, args);
    }

    public ExcelReadException(Throwable cause) {
        super(cause);
    }

    public ExcelReadException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }
}
