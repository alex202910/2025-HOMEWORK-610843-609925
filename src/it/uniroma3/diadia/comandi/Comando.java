package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public interface Comando {

	public void esegui(Partita partita, IO io);
	public void setParametro(String parametro);
	
	public String getParametro(); 
	public String getNome();

	
	
}
