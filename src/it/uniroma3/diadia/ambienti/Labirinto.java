package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * questa classe crea la mappa di gioco
 */
public class Labirinto {
    
    private Stanza stanzaIniziale;
    private Stanza stanzaFinale;
    private Map<String, Stanza> nome2stanza;	//mappa in cui ci sono tutte le stanze
    
    
    private Labirinto() {
    	nome2stanza = new HashMap<>();
    }
    
    public Labirinto(CaricatoreLabirinto loader) {
    	
    	stanzaIniziale = loader.getStanzaIniziale();
    	stanzaFinale = loader.getStanzaVincente();
    	
    	if(stanzaFinale == null || stanzaIniziale == null) {
    		throw new IllegalStateException("non puoi creare un labirinto da un caricatore che non è stato ancora caricato");
    	}
	}

	/**
     *restituisce la stanza iniziale
     */
    public Stanza getStanzaIniziale() {
        return this.stanzaIniziale;
    }
    
    /**
     * restituisce la stanza finale
     */
    public Stanza getStanzaFinale() {
        return this.stanzaFinale;
    }
    
    public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public void setStanzaFinale(Stanza stanzaFinale) {
		this.stanzaFinale = stanzaFinale;
	}
	
	public Map<String, Stanza> getMappaStanze() {
		return this.nome2stanza;
	}
	
	public void setMappaStanze(Map<String, Stanza> nome2stanza) {
		this.nome2stanza = nome2stanza;
	}
	
	public void addStanza2Map(Stanza stanza) {
		nome2stanza.put(stanza.getNome(), stanza);
	}
	
	
	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanza;

		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
		}

		public LabirintoBuilder(CaricatoreLabirinto car) {
			this.labirinto = new Labirinto();
			this.labirinto.setStanzaIniziale(car.getStanzaIniziale());
			this.labirinto.setStanzaFinale(car.getStanzaVincente());
			this.labirinto.setMappaStanze(car.getNome2stanza());
		}

		public LabirintoBuilder addStanza(String nome) {
			Stanza stanza = new Stanza(nome);
			labirinto.addStanza2Map(stanza);
			this.ultimaStanza = stanza;
			return this;
		}

		
		public LabirintoBuilder addStanzaIniziale(String nome) {
			if(labirinto.getMappaStanze().containsKey(nome)) {
				this.labirinto.setStanzaIniziale(labirinto.getMappaStanze().get(nome));
			}
			else {
				Stanza stanza = new Stanza(nome);
				this.labirinto.setStanzaIniziale(stanza);
				labirinto.addStanza2Map(stanza);
				this.ultimaStanza = stanza;
			}
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String nome) {
			if(labirinto.getMappaStanze().containsKey(nome)) {
				this.labirinto.setStanzaFinale(labirinto.getMappaStanze().get(nome));
			}
			else {
				Stanza stanza = new Stanza(nome);
				this.labirinto.setStanzaFinale(stanza);
				labirinto.addStanza2Map(stanza);
				this.ultimaStanza = stanza;
			}
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int weight) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, weight);
			this.ultimaStanza.addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String stanzaDiPartenza, String stanzaDiArrivo, Direzione direzione) {
			Stanza stanzaIn = labirinto.getMappaStanze().get(stanzaDiPartenza);
			if(stanzaIn.getDirezioni().size()>=4 || stanzaIn==null) {
				return this;
			}
			Stanza stanzaArrivo = labirinto.getMappaStanze().get(stanzaDiArrivo);
			if(stanzaArrivo!=null) {
				stanzaIn.impostaStanzaAdiacente(direzione, stanzaArrivo);			
			}
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
			Stanza stanzaMagica = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
			labirinto.addStanza2Map(stanzaMagica);
			this.ultimaStanza = stanzaMagica;
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzoPerVedere) {
			Stanza stanzaBuia = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoPerVedere);
			labirinto.addStanza2Map(stanzaBuia);
			this.ultimaStanza = stanzaBuia;
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, Direzione direzioneBloccata, String chiave) {
			Stanza stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata, chiave, direzioneBloccata);
			labirinto.addStanza2Map(stanzaBloccata);
			this.ultimaStanza = stanzaBloccata;
			return this;
		}
		
		public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio) {
			if(this.ultimaStanza.getPersonaggio()!=null) {
				return this;
			}
			this.ultimaStanza.setPersonaggio(personaggio);
			return this;
		}

		public Labirinto getLabirinto() {
			return labirinto;
		}
		

		public LabirintoBuilder build() {
			LabirintoBuilder labBase=new LabirintoBuilder();
	        labBase.addStanzaBloccata("Atrio", Direzione.NORD, "chiave").addStanzaIniziale("Atrio").addAttrezzo("osso",1).addAttrezzo("lancia", 4).addAttrezzo("scudo", 7)
	        .addStanzaMagica("Laboratorio Campus",3)
	        .addStanzaBuia("Aula N10", "lanterna").addAttrezzo("lanterna",3).addStanzaVincente("Biblioteca").addStanza("Aula N11").addAttrezzo("chiave", 1)
	        .addAdiacenza("Atrio", "Biblioteca", Direzione.NORD).addAdiacenza("Atrio", "Aula N11", Direzione.EST).addAdiacenza("Atrio", "Aula N10", Direzione.SUD).addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
	        .addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.EST).addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
	        .addAdiacenza("Aula N10", "Aula N11", Direzione.EST).addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST).addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
	        .addAdiacenza("Laboratorio Campus","Atrio", Direzione.EST).addAdiacenza("Laboratorio Campus","Aula N11", Direzione.OVEST)
	        .addAdiacenza("Biblioteca","Atrio", Direzione.SUD);
	        return labBase;
		}
		
		
		public LabirintoBuilder buildBase() {
			LabirintoBuilder labBase=new LabirintoBuilder();
	        labBase.addStanzaBloccata("Atrio", Direzione.NORD, "chiave").addStanzaIniziale("Atrio").addAttrezzo("osso",1).addAttrezzo("lancia", 4).addAttrezzo("scudo", 7)
	        .addStanzaMagica("Laboratorio Campus",3).addPersonaggio(new Mago("Mago", " Sono il MagoDeiPolli", new Attrezzo("pollo", 4)))
	        .addStanzaBuia("Aula N10", "lanterna").addAttrezzo("lanterna",3).addPersonaggio(new Strega("Strega", " Sono Bulabula la permalosa"))
	        .addStanzaVincente("Biblioteca")
	        .addStanza("Aula N11").addAttrezzo("chiave", 1)
	        .addPersonaggio(new Cane("Cane", " Sono Buld il cane parlante", new Attrezzo("escrementi", 1)))
	        .addAdiacenza("Atrio", "Biblioteca", Direzione.NORD).addAdiacenza("Atrio", "Aula N11", Direzione.EST).addAdiacenza("Atrio", "Aula N10", Direzione.SUD).addAdiacenza("Atrio", "Laboratorio Campus", Direzione.OVEST)
	        .addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.EST).addAdiacenza("Aula N11", "Atrio", Direzione.OVEST)
	        .addAdiacenza("Aula N10", "Aula N11", Direzione.EST).addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.OVEST).addAdiacenza("Aula N10", "Atrio", Direzione.NORD)
	        .addAdiacenza("Laboratorio Campus","Atrio", Direzione.EST).addAdiacenza("Laboratorio Campus","Aula N11", Direzione.OVEST)
	        .addAdiacenza("Biblioteca","Atrio", Direzione.SUD);
	        return labBase;
		}
		
		
	}


	public static Labirinto fromLoader(CaricatoreLabirinto loader) {
		return new Labirinto(loader);
	}
}