package it.uniroma3.diadia.ambienti;

import java.util.List;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import it.uniroma3.diadia.CaricatoreProprieta;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
//	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;

	private Map<String, Attrezzo> nome2attrezzo;

	private Map<Direzione, Stanza> direzione2stanzaAdiacente;

	private AbstractPersonaggio personaggio;


	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.nome2attrezzo = new HashMap<>();
		this.direzione2stanzaAdiacente = new HashMap<>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if(direzione2stanzaAdiacente.size() < NUMERO_MASSIMO_DIREZIONI) {
			direzione2stanzaAdiacente.put(direzione, stanza);
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return direzione2stanzaAdiacente.get(direzione);
	}

	/**
	 * Restituisce il nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Map<String, Attrezzo> getAttrezzi() {
		return this.nome2attrezzo;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.nome2attrezzo.size() < CaricatoreProprieta.getAttrezziStanza()) {
			this.nome2attrezzo.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		return false;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		
		risultato.append(this.nome);
		
		risultato.append("\nUscite: ");
//		for (Map.Entry<Direzione, Stanza> direzione : this.direzione2stanzaAdiacente.entrySet())
//			if (direzione!=null)
//				risultato.append(" " + direzione.getKey());
		
		//per stampare le direzioni ordinate secondo ordinal()
		TreeSet<Direzione> stampaDirezioni = new TreeSet<>(this.direzione2stanzaAdiacente.keySet());
		for(Direzione direzione : stampaDirezioni) {
			risultato.append(" " + direzione);
		}
		
		
		risultato.append("\nAttrezzi nella stanza: ");
		for (Map.Entry<String, Attrezzo> attrezzo : this.nome2attrezzo.entrySet()) {
			if(attrezzo!=null) {
				risultato.append(attrezzo.getValue()+" ");
			}
		}
		
		if(this.personaggio!=null) {
			risultato.append("\n" + "nella stanza c'è " + this.personaggio.toString());
		}
		
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.nome2attrezzo.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 *         null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return nome2attrezzo.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza.
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(nome2attrezzo.containsValue(attrezzo)) {
			nome2attrezzo.remove(attrezzo.getNome());
			return true;
		}
		return false;
	}


	public Collection<Direzione> getDirezioni() {

		List<Direzione> direzioni = new ArrayList<>(direzione2stanzaAdiacente.keySet());

		return direzioni;
	}

	
	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
		return direzione2stanzaAdiacente;
	}
	
	
	public Map<String, Attrezzo> getMapAttrezzi(){
		return nome2attrezzo;
	}
	
	
	public TreeSet<Stanza> getStanzeAdiacentiOrdinatePerNumeroDiAttrezzi() {
		
		class ComparatorePerNumeroDiAttrezzi implements Comparator<Stanza>{

			@Override
			public int compare(Stanza o1, Stanza o2) {
				if(o1.getMapAttrezzi().size()-o2.getMapAttrezzi().size()!=0) {
					return o1.getMapAttrezzi().size()-o2.getMapAttrezzi().size();					
				}
				return o1.getNome().compareTo(o2.getNome());
			}
			
		}
		
		TreeSet<Stanza> stanzeOrdinatePerNumAttrezzi = new TreeSet<Stanza>(new ComparatorePerNumeroDiAttrezzi());
		stanzeOrdinatePerNumAttrezzi.addAll(this.getMapStanzeAdiacenti().values());
		
		return stanzeOrdinatePerNumAttrezzi;
	}

	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	
	@Override
	public boolean equals(Object o) {
		Stanza stanza = (Stanza)o;
		return this.nome.equals(stanza.getNome());
	}
}