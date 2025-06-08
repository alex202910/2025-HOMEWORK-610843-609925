package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private Stanza stanza;
	private Attrezzo spada;
	private Attrezzo lanterna;
	private static final String MESSAGGIO = "qui c'è buio pesto, ritorna con qualcosa che fa luce...";
	
	@BeforeEach
	void setUp() throws Exception {
		this.stanza = new StanzaBuia("N11");
		this.spada = new Attrezzo("spada", 4);
		this.lanterna = new Attrezzo("lanterna", 3);
	}
	
	
	@Test
	void testDescrizioneStanzaBuia() {
		
		assertEquals(this.stanza.getDescrizione(), MESSAGGIO);
		
		}
	
	@Test
	void testDescrizioneConLanterna() {
		
		this.stanza.addAttrezzo(lanterna);
		assertEquals(this.stanza.getDescrizione(), MESSAGGIO);
		
	}
	
	@Test
	void testGetAttrezzoInesistente() {
		this.stanza.addAttrezzo(spada);
		assertNull(this.stanza.getAttrezzo(""));
	}
	

}
