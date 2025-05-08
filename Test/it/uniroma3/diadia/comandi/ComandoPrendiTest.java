package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPrendiTest {

	private Partita partita;
	private ComandoPrendi comandoPrendi;


	@BeforeEach
	public void setUp() throws Exception {
		this.comandoPrendi = new ComandoPrendi();
		this.comandoPrendi.setIO(new IOConsole());
		this.partita = new Partita();
		Attrezzo attrezzoNuovo = new Attrezzo("arco", 1);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzoNuovo);
	}
	
	@Test
	public void testEseguiAttrezzoPresente() {
		this.comandoPrendi.setParametro("arco");
		this.comandoPrendi.esegui(partita);
		
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("arco"));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("arco"));
	}
	
	@Test
	public void testEseguiAttrezzoNonPresente() {
		String nonPresente = "attrezzoNonPresente";
		this.comandoPrendi.setParametro(nonPresente);
		this.comandoPrendi.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(nonPresente));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("arco"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("arco"));
	}

}
