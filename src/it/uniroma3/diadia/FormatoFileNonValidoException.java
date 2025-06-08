package it.uniroma3.diadia;

public class FormatoFileNonValidoException extends Exception{
	
	/** se estenddo Exception la mia classe è serializzabile */
	private static final long serialVersionUID = 1L;
	
	public FormatoFileNonValidoException() {	
		super();
	}
	
	public FormatoFileNonValidoException(String messaggio) {
		super(messaggio);
	}
	
}