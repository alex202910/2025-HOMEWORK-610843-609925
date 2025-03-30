import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

	
	/*
	@BeforeEach
	void setUp() throws Exception{
		this.stanza = new Stanza("");
		this.spada = new Attrezzo("spada", 10);
	}
	
	void testHasAttrezzi(){
		
	}
	
	@Test
	void testHasAttrezziVuota(){
		assertFalse(this.stanza.hasAttrezzo("spada"));
	}
	@Test
	void testRemoveAttrezzo() {
		
		assertFalse(this.stanza.removeAttrezzo(spada));
		this.stanza.addAttrezzo(this.spada);
		assertTrue(this.stanza.removeAttrezzo(spada));
	}
	*/
	


	import static org.junit.jupiter.api.Assertions.*;

	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

	import it.uniroma3.diadia.ambienti.Stanza;
	import it.uniroma3.diadia.attrezzi.Attrezzo;

	class TestStanza {

		private Stanza stanza;
		private Attrezzo spada;

		@BeforeEach
		void setUp() throws Exception {
			this.stanza = new Stanza("N11");
			this.spada = new Attrezzo("spada", 4);
		}
		
		@Test
		void testHasAttrezzoStanzaVuota() {
			assertFalse(this.stanza.hasAttrezzo("spada"));
		}

		@Test
		void testHasAttrezzo() {
			assertFalse(this.stanza.hasAttrezzo("spada"));
			this.stanza.addAttrezzo(this.spada);
			assertTrue(this.stanza.hasAttrezzo("spada"));
		}
		
		@Test
		void testRemoveAttrezzo() {
			assertFalse(this.stanza.hasAttrezzo("spada"));
			this.stanza.addAttrezzo(this.spada);
			assertTrue(this.stanza.removeAttrezzo(spada));
		}
	}
	

