package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{

	private IO io;
	private String nomeAttrezzo;
	private Attrezzo AttrezzoDaPrendere;
	private Partita partita;
	
	@Override
	public void esegui(Partita partita) {
		//Attrezzo attrezzo = partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo);
		
		
		if(!partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			// printero che l'attrezzo non è presente
			this.io.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "non è presente nella stanza");
			return;
		}
		
		if(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			
			Attrezzo attrezzoDaRimuovere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			Attrezzo attrezzoDaMettere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			partita.getStanzaCorrente().removeAttrezzo(attrezzoDaRimuovere);
			partita.getGiocatore().addAttrezzo(attrezzoDaMettere);
			io.mostraMessaggio("l'attrezzo " + nomeAttrezzo + " è stato preso!");
		}else {
			io.mostraMessaggio("l'attrezzo " + nomeAttrezzo + "non è presente nella stanza!");
		}
		
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
	
/*private void prendi(String nomeAttrezzo) {
		
		if(nomeAttrezzo != null) {
			
			if(this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo) != null) {
				
				Attrezzo AttrezzoDaEliminare = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
				if(this.partita.getGiocatore().getBorsa().addAttrezzo(AttrezzoDaEliminare)) {
					this.partita.getStanzaCorrente().removeAttrezzo(AttrezzoDaEliminare);
					this.io.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "è stato preso dalla stanza");
				}
				else
					this.io.mostraMessaggio("la borsa è troppo piena, svuotala");
			}
			else
				this.io.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "non è stato trovato nella stanza");
		}
		else
			this.io.mostraMessaggio("non hai inserito il nome dell'oggetto da prendere!");
		
		
	}
	
	*/
	
	@Override
	public void setParametro(String parametro){
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "prendi";
	}
}