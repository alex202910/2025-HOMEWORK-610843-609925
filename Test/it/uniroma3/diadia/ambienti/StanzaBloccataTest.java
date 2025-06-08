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
		this.stanzaBloccata = new StanzaBloccata("N11", "chiave", Direzione.NORD);
		this.stanzaIniziale = new Stanza("N9");
		this.stanzaLibera = new Stanza("N10");
		this.chiave = new Attrezzo("chiave", 1);
		this.lanterna = new Attrezzo("lanterna", 3);
		stanzaIniziale.impostaStanzaAdiacente(Direzione.NORD, stanzaBloccata);
		stanzaIniziale.impostaStanzaAdiacente(Direzione.SUD, stanzaLibera);
	}
	
	@Test
	public void testGetStanzaAdiacenteBloccata() {
		
		assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testGetStanzaAdicenteSbloccata() {
		this.stanzaIniziale.addAttrezzo(lanterna);
		assertEquals(this.stanzaIniziale.getStanzaAdiacente(Direzione.NORD), this.stanzaBloccata);
	}
	
	@Test
	public void testGetStanzaAdicenteBloccata1() {
		
		assertNotEquals(this.stanzaIniziale.getStanzaAdiacente(Direzione.NORD).getNome(), this.stanzaBloccata);
	}
	
	@Test
	public void testGetStanzaAdicenteLibera() {
		assertEquals(this.stanzaIniziale.getStanzaAdiacente(Direzione.SUD), this.stanzaLibera);
	}
	
}
