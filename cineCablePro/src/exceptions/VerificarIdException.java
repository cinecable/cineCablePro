package exceptions;

public class VerificarIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3476128734937917216L;
	private String message;

	public VerificarIdException() {
		super();
		message = "Desconocido";
	}

	public VerificarIdException(String message) {
		super(message);
		this.message = message;
	}

	public VerificarIdException(Throwable cause) {
		super(cause);
	}

	public VerificarIdException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public VerificarIdException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
