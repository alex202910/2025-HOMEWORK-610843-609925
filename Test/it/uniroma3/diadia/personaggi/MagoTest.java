package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoRegala;
import it.uniroma3.diadia.personaggi.Mago;

class MagoTest {

	private Partita partita;
	private IO io;
	private LabirintoBuilder builder;
	private ComandoInteragisci interagisci;

	@BeforeEach
	void setUp() throws Exception {
		this.builder = new LabirintoBuilder();
		this.builder.addStanzaIniziale("Atrio")
		.addPersonaggio(new Mago("Mago", "Salve sono il MagoDeiPolli", new Attrezzo("pollo", 4)))
		.addStanzaVincente("Atrio")
		.addAdiacenza("Atrio", "Uscita", Direzione.NORD)
		.addAdiacenza("Atrio", "Uscita", Direzione.SUD);
		this.io = new IOConsole(null);
		this.partita = new Partita(this.builder.getLabirinto());
	}

	@Test
	void testAgisci() {
		this.interagisci = new ComandoInteragisci();

		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("pollo"));

		interagisci.esegui(partita, io);

		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("pollo"));
	}

	@Test
	void testRiceviRegalo() {

		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("armatura", 9));

		ComandoRegala regala = new ComandoRegala();

		regala.setParametro("armatura");
		regala.esegui(partita, io);

		assertEquals(this.partita.getStanzaCorrente().getAttrezzo("armatura").getPeso(), 4);
	}

}
