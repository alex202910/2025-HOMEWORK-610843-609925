package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Partita;

public class StanzaBloccata extends Stanza{

	final static private String NOME_ATTREZZO_DEFAULT = "chiave";
	private String attrezzo;
	private Direzione direzioneBloccata;
	

	public StanzaBloccata(String nome, String attrezzo, Direzione direzione){
		super(nome);
		this.attrezzo = attrezzo;
		this.direzioneBloccata = direzione;
	}
	
	@Override
	public String getDescrizione() {
		return "\nla porta a "+this.direzioneBloccata+" è chiusa e per aprirla serve "+this.attrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione){
		if( !hasAttrezzo(NOME_ATTREZZO_DEFAULT) && direzione.equals(direzioneBloccata) ) {
			return this;
		}
			
		return super.getStanzaAdiacente(direzione);
	}
	
	
	
}
