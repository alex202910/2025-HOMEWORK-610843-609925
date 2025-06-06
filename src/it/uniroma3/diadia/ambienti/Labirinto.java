package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * questa classe crea la mappa di gioco
 */
public class Labirinto {

    private Stanza stanzainiziale;
    private Stanza stanzafinale;
    private Stanza stanzaIniziale;
    private Stanza stanzaFinale;


    public Labirinto() {
        //init();
    }

    /**
     * Crea tutte le stanze e le porte di collegamento
     */
    public void init() {
//
//        /* crea gli attrezzi */
//        Attrezzo lanterna = new Attrezzo("lanterna",3);
//        Attrezzo osso = new Attrezzo("osso",1);
//		Attrezzo lancia = new Attrezzo("lancia", 4);
//		Attrezzo scudo = new Attrezzo("scudo", 7);
//		Attrezzo chiave = new Attrezzo("chiave", 1);
//
//        /* crea stanze del labirinto */
//        Stanza atrio = new StanzaBloccata("Atrio", "nord", "chiave");
//        Stanza aulaN11 = new Stanza("Aula N11");
//        Stanza aulaN10 = new StanzaBuia("Aula N10");
//        Stanza laboratorio = new StanzaMagica("Laboratorio Campus");
//        Stanza biblioteca = new Stanza("Biblioteca");
//
//        /* collega le stanze */
//        atrio.impostaStanzaAdiacente("nord", biblioteca);
//        atrio.impostaStanzaAdiacente("est", aulaN11);
//        atrio.impostaStanzaAdiacente("sud", aulaN10);
//        atrio.impostaStanzaAdiacente("ovest", laboratorio);
//        aulaN11.impostaStanzaAdiacente("est", laboratorio);
//        aulaN11.impostaStanzaAdiacente("ovest", atrio);
//        aulaN10.impostaStanzaAdiacente("nord", atrio);
//        aulaN10.impostaStanzaAdiacente("est", aulaN11);
//        aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
//        laboratorio.impostaStanzaAdiacente("est", atrio);
//        laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
//        biblioteca.impostaStanzaAdiacente("sud", atrio);
//
//        /* pone gli attrezzi nelle stanze */
//        aulaN10.addAttrezzo(lanterna);
//        atrio.addAttrezzo(osso);
//        atrio.addAttrezzo(lancia);
//        atrio.addAttrezzo(scudo);
//        aulaN11.addAttrezzo(chiave);
//
//        // il gioco comincia nell'atrio
//        stanzaIniziale = atrio;  
//        stanzaFinale = biblioteca;
    }

    /**
     *restituisce la stanza iniziale
     */
    public Stanza getStanzaIniziale() {
        return this.stanzainiziale;
    }

    /**
     * restituisce la stanza finale
     */
    public Stanza getStanzaFinale() {
        return this.stanzafinale;
    }

    public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public void setStanzaFinale(Stanza stanzaFinale) {
		this.stanzaFinale = stanzaFinale;
	}
}