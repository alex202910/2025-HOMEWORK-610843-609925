package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private Stanza stanzaBloccata;
	private Stanza stanzaIniziale;
	private Stanza stanzaLibera;
	private Attrezzo chiave;
	private Attrezzo lanterna;

	
	@BeforeEach
	void setUp() throws Exception {
		this.stanzaBloccata = new StanzaBloccata("N11", "chiave", "nord");
		this.stanzaIniziale = new Stanza("N9");
		this.stanzaLibera = new Stanza("N10");
		this.chiave = new Attrezzo("chiave", 1);
		this.lanterna = new Attrezzo("lanterna", 3);
		stanzaIniziale.impostaStanzaAdiacente("nord", stanzaBloccata);
		stanzaIniziale.impostaStanzaAdiacente("sud", stanzaLibera);
	}
	
	@Test
	public void testGetStanzaAdiacenteBloccata() {
		
		assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdicenteSbloccata() {
		this.stanzaIniziale.addAttrezzo(lanterna);
		assertEquals(this.stanzaIniziale.getStanzaAdiacente("nord"), this.stanzaBloccata);
	}
	
	@Test
	public void testGetStanzaAdicenteBloccata1() {
		
		assertNotEquals(this.stanzaIniziale.getStanzaAdiacente("nord").getNome(), this.stanzaBloccata);
	}
	
	@Test
	public void testGetStanzaAdicenteLibera() {
		assertEquals(this.stanzaIniziale.getStanzaAdiacente("sud"), this.stanzaLibera);
	}
	
}
