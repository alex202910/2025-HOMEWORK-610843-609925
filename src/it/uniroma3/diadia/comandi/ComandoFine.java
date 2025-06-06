package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{

	
	public ComandoFine() {
		super("fine");
	}

	private IO io;
	private static final String NOME = "fine";
	private static final String MESSAGGIO_FINALE = "Grazie di aver giocato!";
	
	@Override
	public void esegui(Partita partita, IO io) {
		partita.setFinita();
		io.mostraMessaggio(MESSAGGIO_FINALE);
	}
	


	
}
