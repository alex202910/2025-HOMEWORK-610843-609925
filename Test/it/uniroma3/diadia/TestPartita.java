package it.uniroma3.diadia;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class TestPartita {

	private Partita partita;
		
		@BeforeEach
		void setUp() throws Exception {
			this.partita = new Partita();
		}
		
		@Test
		void testNuovaPartitaNonFinita() {
			assertFalse(this.partita.isFinita());
		}
		
		@Test
		void testPartitaNonVintaEPoiVinta() {
			assertFalse(this.partita.isFinita());
			this.partita.setFinita();
			assertTrue(this.partita.isFinita());
		}
		@Test
		void testPartitaVinta() {
			partita.setStanzaCorrente(partita.getStanzaVincente());
			assertTrue(this.partita.vinta());
		}


	}
