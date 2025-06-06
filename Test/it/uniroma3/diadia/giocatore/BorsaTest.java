package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {
	private Borsa borsa;
	private Attrezzo siringa;
	private Attrezzo lancia;
	private Attrezzo scudo;
	
	@BeforeEach
	void setUp() throws Exception {
		this.borsa = new Borsa();
		this.siringa = new Attrezzo("siringa", 1);
		this.lancia = new Attrezzo("lancia", 10);
		this.scudo = new Attrezzo("scudo", 5);
	}

	@Test
	void testAddAttrezzoInBorsa() {
		assertTrue(this.borsa.addAttrezzo(lancia));
		assertFalse(this.borsa.addAttrezzo(siringa));
	}
	
	@Test
	void testGetAttrezzoDaBorsa() {
		this.borsa.addAttrezzo(siringa);
		this.borsa.addAttrezzo(scudo);
		assertEquals(null, this.borsa.getAttrezzo("lancia"));
		assertEquals(scudo, this.borsa.getAttrezzo("scudo"));
		assertEquals(siringa, this.borsa.getAttrezzo("siringa"));
	}
	
	@Test
	void testGetPesoBorsa() {
		this.borsa.addAttrezzo(siringa);
		this.borsa.addAttrezzo(scudo);
		assertEquals(6, this.borsa.getPeso());
	}
	
	@Test
	void testRemoveAttrezzoDaBorsa() {
		assertEquals(null, this.borsa.removeAttrezzo("siringa"));
		this.borsa.addAttrezzo(siringa);
		this.borsa.addAttrezzo(scudo);
		assertEquals(siringa, this.borsa.removeAttrezzo("siringa"));
		assertEquals(scudo, this.borsa.removeAttrezzo("scudo"));
		
	}
	
	@Test
	void testGetContenutoOrdinatoPerPeso() {
		this.borsa.addAttrezzo(scudo);
		this.borsa.addAttrezzo(siringa);
		List<Attrezzo> expected = new ArrayList<>();
		expected.add(siringa);
		expected.add(scudo);
		assertEquals(expected, this.borsa.getContenutoOrdinatoPerPeso());
	}
	
	@Test
	void testGetContenutoOrdinatoPerNome() {
		this.borsa.addAttrezzo(scudo);
		this.borsa.addAttrezzo(siringa);
		Set<Attrezzo> expected = new TreeSet<>(new ComparatorePerNome());
		expected.add(siringa);
		expected.add(scudo);
		assertEquals(expected, this.borsa.getContenutoOrdinatoPerNome());
	}
	
	@Test
	void testGetContenutoRaggruppatoPerPeso() {
		borsa.addAttrezzo(new Attrezzo("martello", 3));
        borsa.addAttrezzo(new Attrezzo("chiave", 2));
        borsa.addAttrezzo(new Attrezzo("cacciavite", 2));

        Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();

        Set<Attrezzo> attrezziPeso2 = mappa.get(2);
        assertTrue(attrezziPeso2.contains(borsa.getAttrezzo("chiave")));
        assertTrue(attrezziPeso2.contains(borsa.getAttrezzo("cacciavite")));

        Set<Attrezzo> attrezziPeso3 = mappa.get(3);
        assertTrue(attrezziPeso3.contains(borsa.getAttrezzo("martello")));
	}
	
	@Test
	void testGetSortedSetOrdinatoPerPeso() {
		this.borsa.addAttrezzo(scudo);
		this.borsa.addAttrezzo(siringa);
		Set<Attrezzo> expected = new TreeSet<>(new ComparatorePerPeso());
		
		expected.add(scudo);
		expected.add(siringa);
		assertEquals(expected, this.borsa.getSortedSetOrdinatoPerPeso());
	}
	
}