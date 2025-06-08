package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoRegala;
import it.uniroma3.diadia.personaggi.Cane;

class CaneTest {

	private Partita partita;
	private IO io;
	private LabirintoBuilder builder;
	private ComandoInteragisci interagisci;
	private ComandoRegala regala;

	@BeforeEach
	void setUp() throws Exception {
		this.builder = new LabirintoBuilder();
		this.builder.addStanzaIniziale("Atrio")
		.addPersonaggio(new Cane("Cane", "Salve sono Buld il cane parlante", null))
		.addStanzaVincente("Atrio")
		.addAdiacenza("Atrio", "Uscita", Direzione.NORD)
		.addAdiacenza("Atrio", "Uscita", Direzione.SUD);
		this.io = new IOConsole(null);
		this.partita = new Partita(this.builder.getLabirinto());
		this.partita.getGiocatore().setCfu(7);
	}

	@Test
	void testAgisci() {
		assertEquals(this.partita.getGiocatore().getCfu(), 7);

		this.interagisci = new ComandoInteragisci();

		interagisci.esegui(partita, io);

		assertEquals(this.partita.getGiocatore().getCfu(), 6);
	}
	
	
	@Test
	void testRiceviRegaloSbagliato() {
		ComandoRegala attrezzoSbagliato = new ComandoRegala();
		
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("martello", 2));		

		attrezzoSbagliato.setParametro("martello");
		attrezzoSbagliato.esegui(partita, io);

		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("escrementi"));
		assertEquals(this.partita.getGiocatore().getCfu(), 6);
	}
	

	@Test
	void testRiceviRegaloGiusto() {
		this.regala = new ComandoRegala();

		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("osso", 1));

		regala.setParametro("osso");
		regala.esegui(partita, io);

		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("escrementi"));
		assertEquals(this.partita.getGiocatore().getCfu(), 7);
	}



}
