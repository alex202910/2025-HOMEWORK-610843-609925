package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza biblioteca;

	@BeforeEach
	void setUp() throws Exception {
		this.labirinto = new Labirinto();
		this.biblioteca = new Stanza("Biblioteca");
	}

	@Test
	void testgetStanzaCorrenteNonNull() {
		assertNotNull(this.labirinto.getStanzaCorrente());
	}
	
	@Test
	void testStanzaVincenteBiblio() {
		assertEquals(this.biblioteca.getNome(), this.labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	void testStanzaCorrenteDiversoDaBiblio() {
		assertNotEquals(this.biblioteca.getNome(), this.labirinto.getStanzaCorrente().getNome());
	}

}
