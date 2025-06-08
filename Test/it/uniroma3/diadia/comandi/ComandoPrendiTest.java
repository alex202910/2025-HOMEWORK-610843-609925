package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class ComandoPrendiTest {

	private Partita partita;
	private ComandoPrendi comandoPrendi;
	private IOConsole console;


	@BeforeEach
	public void setUp() throws Exception {
		this.comandoPrendi = new ComandoPrendi();
		this.console= new IOConsole(null);
		this.partita = new Partita(new LabirintoBuilder().build().getLabirinto());
		Attrezzo attrezzoNuovo = new Attrezzo("arco", 1);
		partita.getStanzaCorrente().addAttrezzo(attrezzoNuovo);
	}
	
	@Test
	public void testEseguiAttrezzoPresente() {
		this.comandoPrendi.setParametro("arco");
		this.comandoPrendi.esegui(partita, console);
		
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("arco"));
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("arco"));
	}
	
	@Test
	public void testEseguiAttrezzoNonPresente() {
		String nonPresente = "attrezzoNonPresente";
		this.comandoPrendi.setParametro(nonPresente);
		this.comandoPrendi.esegui(partita, console);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(nonPresente));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("arco"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("arco"));
	}

}
