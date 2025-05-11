package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	@Test
	void testPartita1() {
		String[] input1 = {"vai sud", "prendi lanterna", "vai nord", "vai est", "guarda", "posa lanterna", "prendi chiave", 
				 "vai ovest", "posa chiave", "vai nord"};
		

		IOSimulator io = new IOSimulator(input1);
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		//testo stanza buia e bloccata
		
		String[] preventivato = {"messaggioIni" ,"Sei entrato nella stanza :Aula N10\n" + "Aula N10\n" + "Uscite:  nord est ovest\n" + "Attrezzi nella stanza: lanterna (3kg) \n"+ "CFU = 19", 
				"l'attrezzo lanterna è stato preso!", "Sei entrato nella stanza :Atrio\n" + "Atrio\n" + "Uscite:  nord est sud ovest\n" + "Attrezzi nella stanza: osso (1kg) \n" + "CFU = 18", "Sei entrato nella stanza :Aula N11\n"
				+ "Aula N11\n"
				+ "Uscite:  est ovest\n"
				+ "Attrezzi nella stanza: chiave (1kg) \n"
				+ "CFU = 17" , "Informazioni partita: qui c'è buio pesto, ritorna con qualcosa che fa luce..." ,
				"Attrezzo lanterna posato!" , "l'attrezzo chiave è stato preso!" , "Sei entrato nella stanza :Atrio\n"
				+ "Atrio\n"
				+ "Uscite:  nord est sud ovest\n"
				+ "Attrezzi nella stanza: osso (1kg) \n"
				+ "CFU = 16" , "Attrezzo chiave posato!" , "Sei entrato nella stanza :Biblioteca\n"
				+ "Biblioteca\n"
				+ "Uscite:  sud\n"
				+ "Attrezzi nella stanza: \n"
				+ "CFU = 15" ,
				 "Hai vinto!"};
		
		String[] output = io.getOutput();
		int i = 1;
		
		while(i<output.length && i<preventivato.length && preventivato[i] != null && output[i] != null) {
			assertEquals(preventivato[i], output[i]);
			i++;
		}
		
	}
		


}
