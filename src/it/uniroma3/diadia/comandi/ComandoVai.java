package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{

	private IO io;
	private String direzione;
	
	
	@Override
	public void esegui(Partita partita) {
	
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		
		if(this.direzione == null) {
			return;
		}
		
		Stanza prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(this.direzione);
		if(prossimaStanza == null) {
			return;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		if(stanzaCorrente != prossimaStanza) {
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1); 
			io.mostraMessaggio("Sei entrato nella stanza :" + partita.getStanzaCorrente().getNome());
		}
		io.mostraMessaggio(partita.toString());
	}
		
		@Override
		public void setParametro(String parametro){
			this.direzione = parametro;
		}
		
		@Override
		public void setIO(IO io) {
			this.io = io;
		}
		
		@Override
		public String getParametro() {
			return this.direzione;
		}
	
		@Override
		public String getNome() {
			return "vai";
		}

		
		
}
