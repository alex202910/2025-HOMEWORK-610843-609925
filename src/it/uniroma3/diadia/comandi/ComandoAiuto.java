package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", "interagisci", "saluta"};
	private IO io;
	
	public ComandoAiuto() {
		super("aiuto");
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		
		for(int i=0; i< ComandoAiuto.elencoComandi.length; i++) 
			io.mostraMessaggio(ComandoAiuto.elencoComandi[i]+" ");
		io.mostraMessaggio("");
	}



}
	
