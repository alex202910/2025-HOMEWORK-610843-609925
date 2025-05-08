package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo osso;
	private Attrezzo lanterna;
	
	@BeforeEach
	void setUp() throws Exception {
		this.borsa = new Borsa();
		this.osso = new Attrezzo("osso",1);
		this.lanterna = new Attrezzo("lanterna",3);
	}

	@Test
	void testAddAttrezzoTroppoPesante() {
		assertFalse(this.borsa.addAttrezzo(new Attrezzo("pianoforte" , Borsa.DEFAULT_PESO_MAX_BORSA + 1)));
	}
	
	@Test
	void testBorsaVuotaPoiNonVuota() {
		assertFalse(this.borsa.hasAttrezzo("osso"));
		assertTrue(this.borsa.isEmpty());
		
		this.borsa.addAttrezzo(osso);
		assertTrue(this.borsa.hasAttrezzo("osso"));
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	void testRemoveAttrezzoInesistente() {
		assertNull(this.borsa.removeAttrezzo("osso"));
	}
	
	@Test
	void testRemoveAttrezzoNull() {
		assertNull(this.borsa.removeAttrezzo(null));
	}
	
	@Test
	void testRemoveAttrezzoEsistente() {
		assertTrue(this.borsa.addAttrezzo(osso));
		assertNotNull(this.borsa.removeAttrezzo("osso"));
		
		assertTrue(this.borsa.addAttrezzo(lanterna));
		assertNotNull(this.borsa.removeAttrezzo("lanterna"));
	}
}
