package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.assertFalse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractPersonaggioTest {
	
	private FakePersonaggio fakePersonaggio;

	@BeforeEach
	void setUp() throws Exception {
		this.fakePersonaggio = new FakePersonaggio("Carpentiere", " sono Asdrubale, e ti faccio le scarpe quelle belle");
	}

	@Test
	void testGetNome() {
		assertEquals(fakePersonaggio.getNome(), "Carpentiere");
	}
	
	@Test
	void testSaluta() {
		assertFalse(fakePersonaggio.haSalutato());
		assertEquals(fakePersonaggio.saluta(), "Ciao, io sono Carpentiere. sono Asdrubale, e ti faccio le scarpe quelle belle");
		assertTrue(fakePersonaggio.haSalutato());
		assertEquals(fakePersonaggio.saluta(), "Ciao, io sono Carpentiere. Ci siamo gia' presentati!");
	}
	
	@Test
	void testToString() {
		assertEquals(fakePersonaggio.toString(), "Carpentiere");
	}

}