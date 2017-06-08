package exceptions;

public class MYSQLException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MYSQLException(String message){
		super(message);
	}

}
