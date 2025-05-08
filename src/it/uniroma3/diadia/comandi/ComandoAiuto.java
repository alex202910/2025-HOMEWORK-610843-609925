package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	private IO io;
	
	public ComandoAiuto() {

	}
	
	@Override
	public void esegui(Partita partita) {
		
		for(int i=0; i< ComandoAiuto.elencoComandi.length; i++) 
			this.io.mostraMessaggio(ComandoAiuto.elencoComandi[i]+" ");
		this.io.mostraMessaggio("");
	}

	@Override
	public void setParametro(String parametro){
		return;
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
		return "aiuto";
	}
	
}
