package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPosaTest {

	
	private Partita partita;
	private ComandoPosa posa;
	private Comando comandoPosa;
	private IOConsole console;
	private Attrezzo attrezzo;
	private Borsa borsa;
	
	@BeforeEach
	void SetUp() throws Exception{
		
		this.comandoPosa = new ComandoPosa();
		this.console= new IOConsole(null);
		this.partita = new Partita(new LabirintoBuilder().build().getLabirinto());
		this.borsa = partita.getGiocatore().getBorsa();
		this.attrezzo = new Attrezzo("arco", 5);
		borsa.addAttrezzo(attrezzo);
	}
	
	@Test
	void testPosaOggetto() {
		
		this.comandoPosa.setParametro("arco");
		this.comandoPosa.esegui(partita, console);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("arco"));
		assertFalse(partita.getGiocatore().hasAttrezzo("arco"));
	}
	
	
	@Test
	void testPosaOggettoNonEsistente() {
		
		this.comandoPosa.setParametro("boh!");
		this.comandoPosa.esegui(partita, console);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("boh!"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("boh!"));
		assertTrue(partita.getGiocatore().hasAttrezzo("arco"));
	}
	
	

}
