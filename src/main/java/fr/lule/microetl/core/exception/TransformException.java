package fr.lule.microetl.core.exception;

public class TransformException extends Exception {

	private static final long serialVersionUID = 1L;

	public TransformException() {
		super();
	}

	public TransformException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TransformException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransformException(String message) {
		super(message);
	}

	public TransformException(Throwable cause) {
		super(cause);
	}

}
