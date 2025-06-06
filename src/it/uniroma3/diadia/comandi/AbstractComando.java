package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	
	private String nomeComando;
	private String parametro;
	
	
	public AbstractComando(String nomeComando) {
		this.nomeComando = nomeComando;
	}
	
	
	abstract public void esegui(Partita partita, IO io) ;
	
	
	public String getParametro() {
		return parametro;
	}
	
	
	public String getNome() {
		return nomeComando;
	}
	
	
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
}