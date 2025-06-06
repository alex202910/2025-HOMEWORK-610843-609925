package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {

	private String direzione;
	private Partita partita;
	private Stanza stanzaIniziale;
	private Stanza stanzaSecondaria;
	private Comando comando;
	
	@BeforeEach
	void SetUp() throws Exception{
		
		this.comando = new ComandoVai();
		this.comando.setIO(new IOConsole());
		this.partita = new Partita(new LabirintoBuilder().build().getLabirinto());
		this.direzione = "nord";
		this.stanzaIniziale = new Stanza("N10");
		this.stanzaSecondaria = new Stanza("N11");
		stanzaIniziale.impostaStanzaAdiacente("nord", stanzaSecondaria);
	}

	@Test
	void TestDirezioneNulla() {
		
		this.direzione = null;
		this.comando.setParametro(direzione);
		this.comando.esegui(partita);
		assertNotEquals(stanzaIniziale, stanzaSecondaria);
		
	}
	
	@Test
	void TestDirezioneNord() {
		
		this.direzione = "nord";
		this.comando.setParametro(direzione);
		this.comando.esegui(partita);
		assertEquals(stanzaIniziale.getStanzaAdiacente(direzione), stanzaSecondaria);
		
	}
	
	@Test
	void TestDirezioneSud() {
		
		this.direzione = "sud";
		this.comando.setParametro(direzione);
		this.comando.esegui(partita);
		assertNotEquals(stanzaIniziale, stanzaSecondaria);
		
	}
	
	@Test
	void TestDirezioneNordSud() {
		
		this.direzione = "nord";
		this.comando.setParametro(direzione);
		this.direzione = "sud";
		this.comando.setParametro(direzione);
		this.comando.esegui(partita);
		assertEquals(stanzaIniziale, stanzaIniziale);
		
	}
	
}
