package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.List;

public enum Direzione {
	
	NORD("nord"){
		@Override
		public Direzione getDirezioneOpposta() {
			return SUD;
		}
		
		@Override
		public String toString() {
			return this.nome;
		}
	},
	
	SUD("sud"){
		@Override
		public Direzione getDirezioneOpposta() {
			return NORD;
		}
		@Override
		public String toString() {
			return this.nome;
		}
	},
	
	EST("est"){
		@Override
		public Direzione getDirezioneOpposta() {
			return OVEST;
		}
		
		@Override
		public String toString() {
			return this.nome;
		}
	}, 
	
	OVEST("ovest"){
		@Override
		public Direzione getDirezioneOpposta() {
			return EST;
		}
		
		@Override
		public String toString() {
			return this.nome;
		}
	};
	
	protected String nome;
	
	private Direzione(String nome) {
		this.nome = nome;
	}
	
	public static List<String> getDirezioni() {
		List<String> list = new ArrayList<>();
		for(Direzione direzione : Direzione.values()) {
			list.add(direzione.nome);
		}
		return list;
	}
	
	abstract public Direzione getDirezioneOpposta();
}
