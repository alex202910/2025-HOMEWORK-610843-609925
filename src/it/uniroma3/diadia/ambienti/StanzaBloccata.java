package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Partita;

public class StanzaBloccata extends Stanza{

	final static private String NOME_ATTREZZO_DEFAULT = "chiave";
	private String attrezzo;
	private String direzioneBloccata;
	

	public StanzaBloccata(String nome, String attrezzo, String direzione){
		super(nome);
		this.attrezzo = attrezzo;
		this.direzioneBloccata = direzione;
	}
	
	@Override
	public String getDescrizione() {
		return "questa stanza può essere aperta solo se si ha un piede di porco";
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione){
		if( !hasAttrezzo(NOME_ATTREZZO_DEFAULT) && direzione.equals(direzioneBloccata) ) {
			return this;
		}
			
		return super.getStanzaAdiacente(direzione);
	}
	
}
