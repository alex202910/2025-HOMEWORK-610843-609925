
package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
		public final static int DEFAULT_PESO_MAX_BORSA = 10;
		private Map<String, Attrezzo> nome2attrezzo;
		private int pesoAttuale;
		private int pesoMax;
		
		public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
		
		
		
		public Borsa(int pesoMax) {
				this.pesoMax = pesoMax;
				this.nome2attrezzo = new HashMap<String, Attrezzo>(); // speriamo bastino...
				this.pesoAttuale = 0;
		}
		
		
		
		public boolean addAttrezzo(Attrezzo attrezzo) {
			
			if(this.getPeso() + attrezzo.getPeso() > this.pesoMax) {
				return false;
			}
			
			nome2attrezzo.put(attrezzo.getNome(), attrezzo);
			this.pesoAttuale = this.pesoAttuale + attrezzo.getPeso();
			return true;
		}
		
		
		
		public int getPesoMax() {
			return pesoMax;
		}
		
		
		
		public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return nome2attrezzo.get(nomeAttrezzo);
		}



		public int getPeso() {
			return this.pesoAttuale;
		}



	public boolean isEmpty() {
		return this.nome2attrezzo.isEmpty();
	}



	public boolean hasAttrezzo(String nomeAttrezzo) {
			return this.getAttrezzo(nomeAttrezzo)!=null;
	}



	public Attrezzo removeAttrezzo(String wanted) {
		if(nome2attrezzo.containsKey(wanted)) {
			this.pesoAttuale = this.pesoAttuale - this.nome2attrezzo.get(wanted).getPeso();
		}
		return nome2attrezzo.remove(wanted);
	}



	public String toString() {
		StringBuilder s = new StringBuilder();
		if(!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
//			for (Attrezzo attrezzo : this.nome2attrezzo.values()) {
//				s.append(attrezzo.toString()+" ");
//			}
			s.append(this.nome2attrezzo.values().toString());
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	
	}
	
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> listaAttrezzi = new LinkedList<Attrezzo>(this.nome2attrezzo.values());
		Collections.sort(listaAttrezzi, new ComparatorePerPeso());
		return listaAttrezzi;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		
		SortedSet<Attrezzo> SortedAttrezzi = new TreeSet<>(new ComparatorePerNome());
		SortedAttrezzi.addAll(this.nome2attrezzo.values());
		return SortedAttrezzi;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();
		Set<Attrezzo> tmp;
		
		for(Attrezzo a : this.nome2attrezzo.values()) {
			if(mappa.containsKey(a.getPeso())) {
				tmp = mappa.get(a.getPeso());
				tmp.add(a);
			}else {
				tmp = new HashSet<Attrezzo>();
				tmp.add(a);
				mappa.put(a.getPeso(), tmp);
			}
		}
		return mappa;
	}
	
public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		
		SortedSet<Attrezzo> sortedSet = new TreeSet<>(new ComparatorePerPeso());
		sortedSet.addAll(this.nome2attrezzo.values());
		
		return sortedSet;
	}
	
}