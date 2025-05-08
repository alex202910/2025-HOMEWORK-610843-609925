package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.IO;

public class ComandoPosa implements Comando{

	private IO io;
	private String nomeAttrezzo;
	
	
	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		
		if(attrezzo == null) {
			// printero che l'attrezzo non è presente
			this.io.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "non è presente nella tua borsa");
			return ;
		}
		
		if(partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
			borsa.removeAttrezzo(this.nomeAttrezzo);
			this.io.mostraMessaggio("Attrezzo " + this.nomeAttrezzo + " posato!");
		}else {
			this.io.mostraMessaggio("la stanza è troppo piena, prova altrove");
		}
		
	}
	
	@Override
	public void setParametro(String parametro){
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
	@Override
	public String getNome() {
		return "posa";
	}
	
}
