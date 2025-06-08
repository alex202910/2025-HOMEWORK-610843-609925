package it.uniroma3.diadia;

import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

class IOSimulatorTest {

	@Test
	void testPartita1() {
		String[] input = {"vai nord","vai sud", "guarda", "prendi lanterna", "guarda", "vai nord", "prendi osso", "prendi lancia", "prendi scudo",
				"vai est", "prendi chiave", "vai ovest", "vai nord", "posa chiave", "guarda", "vai nord"};

		List<String> input1 = new ArrayList<>();
		for(String stringa : input) {
			input1.add(stringa);
		}

		IOSimulator io = new IOSimulator(input1);
		DiaDia gioco = new DiaDia(io, new LabirintoBuilder().build().getLabirinto());
		System.out.println("PARTITA 1 (per provare stanzaBloccata e stanzaBuia)\n");
		gioco.gioca();

		String[] expected = {""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.",
				
				"Atrio\n" + "Uscite:  nord sud est ovest\n" + "Attrezzi nella stanza: scudo (7kg) osso (1kg) lancia (4kg) \n" + "la porta a nord è chiusa e per aprirla serve chiave\n" + "cfu: 20\n" + "Borsa vuota",
				"Aula N10\n" + "Uscite:  nord est ovest\n" + "Attrezzi nella stanza: lanterna (3kg) \n" + "cfu: 19\n" + "Borsa vuota" ,
				"Aula N10\n" + "Uscite:  nord est ovest\n" + "Attrezzi nella stanza: lanterna (3kg) \n" + "cfu: 19\n" + "Borsa vuota" ,
				"oggetto preso",
				"qui c'è buio pesto\n" + "cfu: 19\n" + "Contenuto borsa (3kg/10kg): [lanterna (3kg)]",
				"Atrio\n" + "Uscite:  nord sud est ovest\n" + "Attrezzi nella stanza: scudo (7kg) osso (1kg) lancia (4kg) \n" + "la porta a nord è chiusa e per aprirla serve chiave\n" + "cfu: 18\n" + "Contenuto borsa (3kg/10kg): [lanterna (3kg)]",
				"oggetto preso",
				"oggetto preso",
				"borsa piena",
				"Aula N11\n" + "Uscite:  est ovest\n" + "Attrezzi nella stanza: chiave (1kg) \n" + "cfu: 17\n" + "Contenuto borsa (8kg/10kg): [osso (1kg), lancia (4kg), lanterna (3kg)]",
				"oggetto preso",
				"Atrio\n" + "Uscite:  nord sud est ovest\n" + "Attrezzi nella stanza: scudo (7kg) \n" + "la porta a nord è chiusa e per aprirla serve chiave\n" + "cfu: 16\n" + "Contenuto borsa (9kg/10kg): [osso (1kg), chiave (1kg), lancia (4kg), lanterna (3kg)]",
				"Atrio\n" + "Uscite:  nord sud est ovest\n" + "Attrezzi nella stanza: scudo (7kg) \n" + "la porta a nord è chiusa e per aprirla serve chiave\n" + "cfu: 16\n" + "Contenuto borsa (9kg/10kg): [osso (1kg), chiave (1kg), lancia (4kg), lanterna (3kg)]",
				"oggetto posato",
				"Atrio\n" + "Uscite:  nord sud est ovest\n" + "Attrezzi nella stanza: scudo (7kg) chiave (1kg) \n" + "la porta a nord è chiusa e per aprirla serve chiave\n" + "cfu: 16\n" + "Contenuto borsa (8kg/10kg): [osso (1kg), lancia (4kg), lanterna (3kg)]",
				"Biblioteca\n" + "Uscite:  sud\n" + "Attrezzi nella stanza: \n" + "cfu: 15\n" + "Contenuto borsa (8kg/10kg): [osso (1kg), lancia (4kg), lanterna (3kg)]",
		"Hai vinto!"};

		List<String> expected1 = new ArrayList<>();
		for(String stringa : expected) {
			expected1.add(stringa);
		}
		
		for(int i=0; i<io.getOutput().size(); i++) {
			assertEquals(expected1.get(i), (io.getOutput().get(i)));
		}
		
		assertTrue(expected1.equals(io.getOutput()));
	}


	@Test
	void testPartita2() {
		String[] input = {"prendi lancia", "vai est", "prendi chiave", "vai est", "posa lancia", "prendi lancia", "posa lancia", "prendi lancia",
				"posa lancia", "prendi lancia", "posa lancia", "prendi lancia", "prendi aicnal", "posa chiave", "prendi chiave", "prendi evaihc",
				"posa evaihc", "prendi chiave", "posa aicnal", "guarda", "prendi chiave", "vai est", "posa chiave", "vai nord"};

		List<String> input2 = new ArrayList<>();
		for(String stringa : input) {
			input2.add(stringa);
		}


		IOSimulator io = new IOSimulator(input2);
		DiaDia gioco = new DiaDia(io, new LabirintoBuilder().build().getLabirinto());
		System.out.println("\n\n\nPARTITA 2 (per provare StanzaMagica)\n");
		gioco.gioca();


		String[] expected = {""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.",
				
				"oggetto preso",
				"Aula N11\n" + "Uscite:  est ovest\n" + "Attrezzi nella stanza: chiave (1kg) \n" + "cfu: 19\n" + "Contenuto borsa (4kg/10kg): [lancia (4kg)]",
				"oggetto preso",
				"Laboratorio Campus\n" + "Uscite:  est ovest\n" + "Attrezzi nella stanza: \n" + "cfu: 18\n" + "Contenuto borsa (5kg/10kg): [chiave (1kg), lancia (4kg)]",
				"oggetto posato",
				"oggetto preso",
				"oggetto posato",
				"oggetto preso",
				"oggetto posato",
				"oggetto preso",
				"oggetto posato",
				"oggetto non trovato nella stanza",
				"oggetto preso",
				"oggetto posato",
				"oggetto non trovato nella stanza",
				"oggetto preso",
				"oggetto posato",
				"borsa piena",
				"oggetto posato",
				"Laboratorio Campus\n" + "Uscite:  est ovest\n" + "Attrezzi nella stanza: chiave (4kg) lancia (16kg) \n" + "cfu: 17\n" + "Borsa vuota",
				"oggetto preso",
				"Atrio\n" + "Uscite:  nord sud est ovest\n" + "Attrezzi nella stanza: scudo (7kg) osso (1kg) \n" + "la porta a NORD è chiusa e per aprirla serve chiave\n" + "cfu: 16\n" + "Contenuto borsa (4kg/10kg): [chiave (4kg)]",
				"oggetto posato",
				"Biblioteca\n" + "Uscite:  sud\n" + "Attrezzi nella stanza: \n" + "cfu: 3\n" + "Borsa vuota",
		"Hai vinto!"};

		List<String> expected2 = new ArrayList<>();
		for(String stringa : expected) {
			expected2.add(stringa);
		}

		for(int i=0; i<io.getOutput().size(); i++) {
			assertEquals(expected2.get(i), (io.getOutput().get(i)));
		}
		
		assertTrue(expected2.equals(io.getOutput()));
	}

	@Test
	void testPartita3() {
		String[] input= {"aiuto", "", "vai", "vai nordEst", "prendi lancia", "prendi scudo", "prendi lanterna", "prendi", "posa lancia", "posa scudo", "fine"};

		List<String> input3 = new ArrayList<>();
		for(String stringa : input) {
			input3.add(stringa);
		}

		IOSimulator io = new IOSimulator(input3);
		DiaDia gioco = new DiaDia(io, new LabirintoBuilder().build().getLabirinto());
		System.out.println("\n\n\nPARTITA 3 (per provare gli altri comandi)\n");
		gioco.gioca();

		String[] expected = {""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissa!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.",

				"vai\n" + "aiuto\n" + "fine\n" + "prendi\n" + "posa\n" + "guarda\n" + "interagisci\n" + "saluta\n",
				"comando non valido",
				"Dove vuoi andare?\nDevi specificare una direzione",
				"la direzione scelta non è valida",
				"oggetto preso",
				"borsa piena",
				"oggetto non trovato nella stanza",
				"devi scegliere un oggetto da prendere",
				"oggetto posato",
				"oggetto non presente in borsa",
		"Grazie di aver giocato!"};

		List<String> expected3 = new ArrayList<>();
		for(String stringa : expected) {
			expected3.add(stringa);
		}
		
		for(int i=0; i<io.getOutput().size(); i++) {
			assertEquals(expected3.get(i), (io.getOutput().get(i)));
		}

		assertTrue(expected3.equals(io.getOutput()));

		//		String[] output = io.getOutput();
		//		int i=1;
		//		while(i<output.length && i<expected.length && expected[i]!=null && output[i]!=null) {
		//			assertEquals(expected[i], output[i]);
		//			i++;
		//		}
	}

}