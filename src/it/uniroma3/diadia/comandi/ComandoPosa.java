package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando {
	
	public ComandoPosa() {
		super("posa");
	}

	@Override
	public void esegui(Partita partita, IO io) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
		
		if(attrezzo == null) {
			// printero che l'attrezzo non è presente
			io.mostraMessaggio("l'oggetto" + " " + this.getParametro() + " " + "non è presente nella tua borsa");
			return ;
		}
		
		if(partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
			borsa.removeAttrezzo(this.getParametro());
			io.mostraMessaggio("Attrezzo " + this.getParametro() + " posato!");
		}else {
			io.mostraMessaggio("la stanza è troppo piena, prova altrove");
		}
		
	}
	
	
}
