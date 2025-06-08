package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	


	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		String azione = null;
		if(this.haSalutato()) {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzeAdiacentiOrdinatePerNumeroDiAttrezzi().getLast());
			azione = "Dato che hai salutato la Strega ti ritrovi in: " + partita.getStanzaCorrente().toString();
		}
		else {
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzeAdiacentiOrdinatePerNumeroDiAttrezzi().getFirst());
			azione = "Dato che non hai salutato la Strega ti ritrovi in: " + partita.getStanzaCorrente().toString();
		}
		return azione;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		
		return "la strega si affeziona subito al tuo dono, temo che non lo potrai mai riavere indietro";
	}

}
