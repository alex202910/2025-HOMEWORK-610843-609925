package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai (direzione), ", "aiuto, ", "fine, ", "prendi (oggetto), ", "posa(oggetto)"};

	private Partita partita;
	private IO io;
	
	public DiaDia(IO io, Labirinto labirinto) {
		this.partita = new Partita(labirinto);
		this.io = io;
	}

	public void gioca() {
		String istruzione; 

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   
  

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione, io);
		comandoDaEseguire.setIO(io);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())

		this.io.mostraMessaggio("Hai vinto!");
		if (this.partita.persa())

		this.io.mostraMessaggio("Hai esaurito i CFU...");

		return this.partita.isFinita();
	}   		

	
	public static void main(String[] argc) {
		IO ioConsole = new IOConsole();
		LabirintoBuilder builder = new LabirintoBuilder();
		Labirinto labirinto = builder.build().getLabirinto();
		
		DiaDia gioco = new DiaDia(ioConsole, labirinto);
		gioco.gioca();
    }
}