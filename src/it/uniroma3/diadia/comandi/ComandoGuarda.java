package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{

	private IO io;
	private static final String NOME = "guarda";

	public ComandoGuarda() {
		super("guarda");
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		//this.io.mostraMessaggio("Stanza corrente: " + partita.getStanzaCorrente().toString());
		this.io.mostraMessaggio("Informazioni partita: " + partita.getStanzaCorrente().getDescrizione());
	}

	
}
