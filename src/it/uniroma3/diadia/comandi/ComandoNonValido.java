package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	
	public ComandoNonValido() {
		super(null);
	}

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("comando non valido");
	}
	
}
