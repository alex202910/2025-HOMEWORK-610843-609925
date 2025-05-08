package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{

	
	private IO io;
	private static final String NOME = "fine";
	private static final String MESSAGGIO_FINALE = "Grazie di aver giocato!";
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		this.io.mostraMessaggio(MESSAGGIO_FINALE);
	}
	
	@Override
	public void setParametro(String parametro){
		//
	}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	@Override
	public String getNome() {
		return NOME;
	}
}
