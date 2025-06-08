package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando{

	
	
	public ComandoPrendi() {
		super("prendi");
		
	}

	@Override
	public void esegui(Partita partita, IO io) {
		//Attrezzo attrezzo = partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo);
		
		
		if(!partita.getStanzaCorrente().hasAttrezzo(this.getParametro())) {
			// printero che l'attrezzo non è presente
			io.mostraMessaggio("l'oggetto" + " " + this.getParametro() + " " + "non è presente nella stanza");
			return;
		}
		
		if(partita.getStanzaCorrente().hasAttrezzo(this.getParametro())) {
			
			Attrezzo attrezzoDaRimuovere = partita.getStanzaCorrente().getAttrezzo(this.getParametro());
			Attrezzo attrezzoDaMettere = partita.getStanzaCorrente().getAttrezzo(this.getParametro());
			partita.getStanzaCorrente().removeAttrezzo(attrezzoDaRimuovere);
			partita.getGiocatore().addAttrezzo(attrezzoDaMettere);
			io.mostraMessaggio("l'attrezzo " + this.getParametro() + " è stato preso!");
		}else {
			io.mostraMessaggio("l'attrezzo " + this.getParametro() + "non è presente nella stanza!");
		}
		
	}
	
}