package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;


import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class StanzaTest {
	private Stanza stanza;
	private Stanza stanzaAdiacente;
	private Attrezzo spada;
	private Attrezzo chiave;
	
	@BeforeEach
	void setUp() throws Exception{
		this.stanza = new Stanza("n11");
		this.stanzaAdiacente = new Stanza("n12");
		this.spada = new Attrezzo("spada",5);
		this.chiave = new Attrezzo("chiave", 1);
	}
	
	@Test
	void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo("spada"));
		this.stanza.addAttrezzo(this.spada);
		assertTrue(this.stanza.hasAttrezzo("spada"));
	}
	@Test
	void testRemoveAttrezzo() {
		assertFalse(this.stanza.removeAttrezzo(spada));
		this.stanza.addAttrezzo(this.spada);
		this.stanza.addAttrezzo(this.chiave);
		assertTrue(this.stanza.removeAttrezzo(spada));
		assertTrue(this.stanza.removeAttrezzo(chiave));
	}
	
	@Test
	void testGetAttrezzo() {
		assertEquals(null, this.stanza.getAttrezzo("spada"));
		this.stanza.addAttrezzo(this.spada);
		this.stanza.addAttrezzo(this.chiave);
		assertEquals(this.spada , this.stanza.getAttrezzo("spada"));
		assertEquals(this.chiave , this.stanza.getAttrezzo("chiave"));
	}
	
	@Test
	void testGetStanzaAdiacenteANDtestImpostaStanzaAdiacente() {
		assertEquals(null, this.stanza.getStanzaAdiacente(Direzione.NORD));
		stanza.impostaStanzaAdiacente(Direzione.NORD, stanzaAdiacente);
		assertEquals(this.stanzaAdiacente, this.stanza.getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	void testGetStanzaAdiacentiOrdinatePerNumeroDiAttrezzi() {
		LabirintoBuilder builder = new LabirintoBuilder();
		Labirinto lab = builder.addStanzaIniziale("N10").addStanza("N11").addAttrezzo("martello", 1).addAttrezzo("maglio", 6)
		.addStanza("N12")
		.addStanza("N13").addAttrezzo("piccone", 7)
		.addStanza("N14").addAttrezzo("osso", 1).addAttrezzo("bazooka", 9).addAttrezzo("chiave", 1)
		.addAdiacenza("N10", "N11", Direzione.NORD).addAdiacenza("N11", "N10", Direzione.SUD)	//N11 2 
		.addAdiacenza("N10", "N12", Direzione.SUD).addAdiacenza("N12", "N10", Direzione.NORD)	//N12 0
		.addAdiacenza("N10", "N13", Direzione.EST).addAdiacenza("N13", "N10", Direzione.OVEST)	//N13 1
		.addAdiacenza("N10", "N14", Direzione.OVEST).addAdiacenza("N14", "N10", Direzione.EST)	//N14 3
		.getLabirinto();
		
		this.stanza= lab.getStanzaIniziale();
		assertEquals(this.stanza.getStanzeAdiacentiOrdinatePerNumeroDiAttrezzi().toString(), "[N12\n"
				+ "Uscite:  nord\n"
				+ "Attrezzi nella stanza: , N13\n"
				+ "Uscite:  ovest\n"
				+ "Attrezzi nella stanza: piccone (7kg) , N11\n"
				+ "Uscite:  sud\n"
				+ "Attrezzi nella stanza: martello (1kg) maglio (6kg) , N14\n"
				+ "Uscite:  est\n"
				+ "Attrezzi nella stanza: osso (1kg) bazooka (9kg) chiave (1kg) ]");
	}
	
}
	

