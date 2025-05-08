package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabbricaDiComandi;

	@Before
	public void setUp() throws Exception {
		this.fabbricaDiComandi = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	public void ComandoVai() {
		
		String nomeComando = "vai";
		String comandoAtteso = "vai";
		String nomeParametro = "nord";
		
		Comando comando = this.fabbricaDiComandi.costruisciComando(nomeComando , new IOConsole());
		
		if (nomeParametro != null) {
			comando.setParametro(nomeParametro);
		}
			assertEquals(comandoAtteso, comando.getNome());
			assertEquals(nomeParametro, comando.getParametro());
			
		}
		
	@Test
	public void ComandoFine() {
		
		String nomeComando = "fine";
		String comandoAtteso = "fine";
		String nomeParametro = null;
		
		Comando comando = this.fabbricaDiComandi.costruisciComando(nomeComando , new IOConsole());
		
		if (nomeParametro != null) {
			comando.setParametro(nomeParametro);
		}
			assertEquals(comandoAtteso, comando.getNome());
			assertEquals(nomeParametro, comando.getParametro());
			
		}
	
	@Test
	public void ComandoPosa() {
		
		String nomeComando = "posa";
		String comandoAtteso = "posa";
		String nomeParametro = "osso";
		
		Comando comando = this.fabbricaDiComandi.costruisciComando(nomeComando , new IOConsole());
		
		if (nomeParametro != null) {
			comando.setParametro(nomeParametro);
		}
			assertEquals(comandoAtteso, comando.getNome());
			assertEquals(nomeParametro, comando.getParametro());
			
		}
	
	@Test
	public void ComandoPrendi() {
		
		String nomeComando = "prendi";
		String comandoAtteso = "prendi";
		String nomeParametro = "osso";
		
		Comando comando = this.fabbricaDiComandi.costruisciComando(nomeComando , new IOConsole());
		
		if (nomeParametro != null) {
			comando.setParametro(nomeParametro);
		}
			assertEquals(comandoAtteso, comando.getNome());
			assertEquals(nomeParametro, comando.getParametro());
			
		}
	
	@Test
	public void ComandoAiuto() {
		
		String nomeComando = "aiuto";
		String comandoAtteso = "aiuto";
		String nomeParametro = null;
		
		Comando comando = this.fabbricaDiComandi.costruisciComando(nomeComando , new IOConsole());
		
		if (nomeParametro != null) {
			comando.setParametro(nomeParametro);
		}
			assertEquals(comandoAtteso, comando.getNome());
			assertEquals(nomeParametro, comando.getParametro());
			
		}
	
	@Test
	public void ComandoNonValido() {
		
		String nomeComando = "aaa";
		String comandoAtteso = "non_valido";
		String nomeParametro = null;
		
		Comando comando = this.fabbricaDiComandi.costruisciComando(nomeComando , new IOConsole());
		
		if (nomeParametro != null) {
			comando.setParametro(nomeParametro);
		}
			assertEquals(comandoAtteso, comando.getNome());
			assertEquals(nomeParametro, comando.getParametro());
			
		}
	
	}




