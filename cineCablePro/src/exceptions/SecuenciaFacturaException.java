package exceptions;

public class SecuenciaFacturaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5818389485793128954L;
	private String message;

	public SecuenciaFacturaException() {
		super();
		message = "Desconocido";
	}

	public SecuenciaFacturaException(String message) {
		super(message);
		this.message = message;
	}

	public SecuenciaFacturaException(Throwable cause) {
		super(cause);
	}

	public SecuenciaFacturaException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public SecuenciaFacturaException(String message, Throwable cause,
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
