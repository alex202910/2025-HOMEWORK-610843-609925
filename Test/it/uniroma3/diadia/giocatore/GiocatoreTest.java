package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	
	private Giocatore P1;
	private Attrezzo osso;
	private Attrezzo lanterna;
	private Giocatore cfu;

	@BeforeEach
	void setUp() throws Exception {
		this.P1 = new Giocatore();
		this.osso = new Attrezzo("osso", 1);
		this.lanterna = new Attrezzo("lanterna", 3);
	}
	
	@Test
	void testCfuNonNegativi() {
		assertTrue(this.P1.getCfu() >=0 );
	}
	
	@Test
	void testAddAttrezzoEsistenteInStanza() {
		assertTrue(this.P1.addAttrezzo(osso));
	}
	
	@Test
	void testRemoveAttrezzoInesistenteDaBorsa() {
		assertNull(this.P1.removeAttrezzo("lanterna"));
	}
	
	@Test
	void testHasAttrezzoNellaBorsa() {
		//non esiste lanterna nella borsa
		assertFalse(this.P1.hasAttrezzo("lanterna"));
		
		//esiste lanterna nella borsa
		this.P1.addAttrezzo(lanterna);
		assertTrue(this.P1.hasAttrezzo("lanterna"));
	}
	
}
