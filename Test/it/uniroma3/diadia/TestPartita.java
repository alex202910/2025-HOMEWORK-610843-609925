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
			this.partita = new Partita(new it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder().build().getLabirinto());
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
