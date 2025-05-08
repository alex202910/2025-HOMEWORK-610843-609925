package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

    private Stanza stanzaIniziale;
    private Stanza stanzaFinale;

    public Labirinto() {
        creaStanze();
    }

    private void creaStanze() {
        /* crea gli attrezzi */
        Attrezzo lanterna = new Attrezzo("lanterna", 3);
        Attrezzo osso = new Attrezzo("osso", 1);
        Attrezzo chiave = new Attrezzo("chiave", 1);

        /* crea stanze del labirinto */
        Stanza atrio = new StanzaBloccata("Atrio", "chiave", "nord");
        Stanza aulaN11 = new StanzaBuia("Aula N11", "lanterna");
        Stanza aulaN10 = new Stanza("Aula N10");
        Stanza laboratorio = new StanzaMagica("Laboratorio Campus");
        Stanza biblioteca = new Stanza("Biblioteca");

        /* collega le stanze */
        atrio.impostaStanzaAdiacente("nord", biblioteca);
        atrio.impostaStanzaAdiacente("est", aulaN11);
        atrio.impostaStanzaAdiacente("sud", aulaN10);
        atrio.impostaStanzaAdiacente("ovest", laboratorio);
        aulaN11.impostaStanzaAdiacente("est", laboratorio);
        aulaN11.impostaStanzaAdiacente("ovest", atrio);
        aulaN10.impostaStanzaAdiacente("nord", atrio);
        aulaN10.impostaStanzaAdiacente("est", aulaN11);
        aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
        laboratorio.impostaStanzaAdiacente("est", atrio);
        laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
        biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
        aulaN10.addAttrezzo(lanterna);
        atrio.addAttrezzo(osso);
        aulaN11.addAttrezzo(chiave);

        // Imposta la stanza iniziale e finale
        this.stanzaIniziale = atrio;
        this.stanzaFinale = biblioteca;
    }

    public Stanza getStanzaIniziale() {
        return stanzaIniziale;
    }

    public Stanza getStanzaFinale() {
        return stanzaFinale;
    }
}