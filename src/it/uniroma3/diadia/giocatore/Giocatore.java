package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.CaricatoreProprieta;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {

    private int cfu;
    private Borsa borsa; // Riferimento alla borsa del giocatore

    public Giocatore() {
         this.cfu = CaricatoreProprieta.getCFU();
        this.borsa = new Borsa(); // Inizializza la borsa
    }

    public int getCfu() {
        return this.cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    /**
     * Restituisce la borsa del giocatore.
     * @return La borsa.
     */
    public Borsa getBorsa() {
        return this.borsa;
    }

    /**
     * Aggiunge un attrezzo alla borsa del giocatore.
     * @param attrezzo L'attrezzo da aggiungere.
     * @return true se l'attrezzo è stato aggiunto, false altrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        return this.borsa.addAttrezzo(attrezzo);
    }

    /**
     * Rimuove un attrezzo dalla borsa del giocatore.
     * @param nomeAttrezzo Il nome dell'attrezzo da rimuovere.
     * @return L'attrezzo rimosso, o null se non trovato.
     */
    public Attrezzo removeAttrezzo(String nomeAttrezzo) {
        return this.borsa.removeAttrezzo(nomeAttrezzo);
    }

    /**
     * Verifica se la borsa del giocatore contiene un attrezzo.
     * @param nomeAttrezzo Il nome dell'attrezzo da cercare.
     * @return true se l'attrezzo è presente, false altrimenti.
     */
    public boolean hasAttrezzo(String nomeAttrezzo) {
        return this.borsa.hasAttrezzo(nomeAttrezzo);
    }

    /**
     * Restituisce una descrizione della borsa del giocatore.
     * @return La descrizione della borsa.
     */
    public String getDescrizioneBorsa() {
        return this.borsa.toString();
    }
    
    
    public String toString() {
    	return this.cfu +"\n"+ this.borsa.toString();
    }
    
}