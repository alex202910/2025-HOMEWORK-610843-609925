package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ambienti.Labirinto;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

    // static final private int CFU_INIZIALI = 5;

    private Stanza stanzaCorrente;
    private boolean finita;
    private Labirinto labirinto;
    private Giocatore giocatore;

    public Partita() {
    	this.giocatore = new Giocatore();
        this.labirinto = new Labirinto();
        this.stanzaCorrente = labirinto.getStanzaIniziale();
        this.finita = false;
    }

    
    public Giocatore getGiocatore() {
        return this.giocatore;
    }
    
    public Stanza getStanzaVincente() {
        return labirinto.getStanzaFinale();
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public Stanza getStanzaCorrente() {
        return this.stanzaCorrente;
    }

    /**
     * Restituisce vero se e solo se la partita e' stata vinta
     * @return vero se partita vinta
     */
    public boolean vinta() {
        return this.getStanzaCorrente().equals(this.getStanzaVincente());
    }
    
    public boolean persa() {
    	return this.getGiocatore().getCfu() == 0;
    }

    /**
     * Restituisce vero se e solo se la partita e' finita
     * @return vero se partita finita
     */
    public boolean isFinita() {
        return finita || vinta() || persa();
    }

    /**
     * Imposta la partita come finita
     */
    public void setFinita() {
        this.finita = true;
    }

    public String toString() {
    	return this.getStanzaCorrente()	+ "\nCFU = " + this.getGiocatore().getCfu();
    }
    
    
    
}