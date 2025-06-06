package it.uniroma3.diadia.ambienti;


import java.util.HashMap;

import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Map<String, Stanza> nome2stanza;
	private Stanza ultimaStanza;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.nome2stanza = new HashMap<>();
	}

	public LabirintoBuilder addStanza(String nome) {
		Stanza stanza = new Stanza(nome);
		this.addStanza2Map(stanza);
		this.ultimaStanza = stanza;
		return this;
	}

	public void addStanza2Map(Stanza stanza) {
		nome2stanza.put(stanza.getNome(), stanza);
	}
	
	public LabirintoBuilder addStanzaIniziale(String nome) {
		if(nome2stanza.containsKey(nome)) {
			this.labirinto.setStanzaIniziale(nome2stanza.get(nome));
		}
		else {
			Stanza stanza = new Stanza(nome);
			this.labirinto.setStanzaIniziale(stanza);
			this.addStanza2Map(stanza);
			this.ultimaStanza = stanza;
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nome) {
		if(nome2stanza.containsKey(nome)) {
			this.labirinto.setStanzaFinale(nome2stanza.get(nome));
		}
		else {
			Stanza stanza = new Stanza(nome);
			this.labirinto.setStanzaFinale(stanza);
			this.addStanza2Map(stanza);
			this.ultimaStanza = stanza;
		}
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int weight) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, weight);
		this.ultimaStanza.addAttrezzo(attrezzo);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String stanzaDiPartenza, String stanzaDiArrivo, String direzione) {
		Stanza stanzaIn = this.nome2stanza.get(stanzaDiPartenza);
		if(stanzaIn.getDirezioni().size()>=4 || stanzaIn==null) {
			return this;
		}
		Stanza stanzaArrivo = this.nome2stanza.get(stanzaDiArrivo);
		if(stanzaArrivo!=null) {
			stanzaIn.impostaStanzaAdiacente(direzione, stanzaArrivo);			
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
		Stanza stanzaMagica = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
		this.addStanza2Map(stanzaMagica);
		this.ultimaStanza = stanzaMagica;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzoPerVedere) {
		Stanza stanzaBuia = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoPerVedere);
		this.addStanza2Map(stanzaBuia);
		this.ultimaStanza = stanzaBuia;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzioneBloccata, String chiave) {
		Stanza stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata, direzioneBloccata, chiave);
		this.addStanza2Map(stanzaBloccata);
		this.ultimaStanza = stanzaBloccata;
		return this;
	}

	public Labirinto getLabirinto() {
		return labirinto;
	}

	public Map<String, Stanza> getMappaStanza() {
		return nome2stanza;
	}
	
	public LabirintoBuilder build() {
		LabirintoBuilder labBase=new LabirintoBuilder();
        labBase.addStanzaBloccata("Atrio", "nord", "chiave").addStanzaIniziale("Atrio").addAttrezzo("osso",1).addAttrezzo("lancia", 4).addAttrezzo("scudo", 7).addStanzaMagica("Laboratorio Campus",3)
        .addStanzaBuia("Aula N10", "lanterna").addAttrezzo("lanterna",3).addStanzaVincente("Biblioteca").addStanza("Aula N11").addAttrezzo("chiave", 1)
        .addAdiacenza("Atrio", "Biblioteca", "nord").addAdiacenza("Atrio", "Aula N11", "est").addAdiacenza("Atrio", "Aula N10", "sud").addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
        .addAdiacenza("Aula N11", "Laboratorio Campus", "est").addAdiacenza("Aula N11", "Atrio", "ovest")
        .addAdiacenza("Aula N10", "Aula N11", "est").addAdiacenza("Aula N10", "Laboratorio Campus", "ovest").addAdiacenza("Aula N10", "Atrio", "nord")
        .addAdiacenza("Laboratorio Campus","Atrio", "est").addAdiacenza("Laboratorio Campus","Aula N11", "ovest")
        .addAdiacenza("Biblioteca","Atrio", "sud");
        return labBase;
	}


	
	
	
	
	
	

}