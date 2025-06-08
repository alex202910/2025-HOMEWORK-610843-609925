package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {

	private String direzione;
	private Partita partita;
	private Stanza stanzaIniziale;
	private Stanza stanzaSecondaria;
	private Comando comando;
	private IOConsole console;
	
	@BeforeEach
	void setUp() throws Exception{
		this.comando = new ComandoVai();
		this.direzione = "nord";
		this.partita = new Partita(new LabirintoBuilder().build().getLabirinto());
		this.console = new IOConsole(null);
		this.stanzaIniziale = new Stanza("atrio");
		this.stanzaSecondaria = new Stanza("biblioteca");
		
		this.comando.setParametro(direzione);
		this.stanzaIniziale.impostaStanzaAdiacente(Direzione.valueOf(direzione.trim().toUpperCase()), stanzaSecondaria);
		this.partita.setStanzaCorrente(stanzaIniziale);
	}

	@Test
	void TestDirezioneNulla() {
		
		this.direzione = null;
		this.comando.setParametro(direzione);
		this.comando.esegui(partita, console);
		assertNotEquals(stanzaIniziale, stanzaSecondaria);
		
	}
	
	@Test
	void testComandoVaiNormale() {
		comando.esegui(partita, console);
		assertEquals(stanzaSecondaria,this.partita.getStanzaCorrente());
	}
	
	@Test
	void TestDirezioneNord() {
		
		this.direzione = "nord";
		this.comando.setParametro(direzione);
		this.comando.esegui(partita, console);
		assertEquals(stanzaIniziale.getStanzaAdiacente(Direzione.NORD), stanzaSecondaria);
		
	}
	
	@Test
	void TestDirezioneSud() {
		
		this.direzione = "sud";
		this.comando.setParametro(direzione);
		this.comando.esegui(partita, console);
		assertNotEquals(stanzaIniziale, stanzaSecondaria);
		
	}
	
	@Test
	void TestDirezioneNordSud() {
		
		this.direzione = "nord";
		this.comando.setParametro(direzione);
		this.direzione = "sud";
		this.comando.setParametro(direzione);
		this.comando.esegui(partita, console);
		assertEquals(stanzaIniziale, stanzaIniziale);
		
	}
	
}
