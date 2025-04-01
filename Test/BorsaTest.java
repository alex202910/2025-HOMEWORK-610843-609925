package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo osso;
	private Attrezzo lanterna;
	private Attrezzo pianoforte;
	
	@BeforeEach
	void setUp() throws Exception {
		this.borsa = new Borsa();
		this.osso = new Attrezzo("osso",1);
		this.lanterna = new Attrezzo("lanterna",3);
		this.pianoforte = new Attrezzo("pianoforte", 20);
	}

	@Test
	void testAddAttrezzoPesante() {
		assertFalse(this.borsa.addAttrezzo(pianoforte));
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
