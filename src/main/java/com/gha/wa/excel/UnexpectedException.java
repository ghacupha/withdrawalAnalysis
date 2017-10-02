package com.gha.wa.excel;

public class UnexpectedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1806846245318329663L;

	public UnexpectedException(){
        super();
    }

    public UnexpectedException(String message){
        super(message);
    }

    public UnexpectedException(String message, Object... args){
        super(fmt(message, args));
    }

    public UnexpectedException(Throwable cause){
        super(cause);
    }

    public UnexpectedException(Throwable cause, String message, Object... args) {
        super(fmt(message, args), cause);
    }
    
    /**
     * A handy alias for {@link String#format(String, Object...)}
     *
     * @param tmpl the message template
     * @param args the message arguments
     * @return the formatted string
     */
    public final static String fmt(String tmpl, Object... args) {
        if (0 == args.length) return tmpl;
        return String.format(tmpl, args);
    }
}
