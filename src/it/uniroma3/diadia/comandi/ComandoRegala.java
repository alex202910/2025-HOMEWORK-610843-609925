package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{

	public ComandoRegala() {
		super("regala");
	}

	
	@Override
	public void esegui(Partita partita, IO io) {
		if(partita.getStanzaCorrente().getPersonaggio()==null) {
			return;
		}
		if(!partita.getGiocatore().getBorsa().hasAttrezzo(getParametro())) {
			io.mostraMessaggio("non posso regalare un oggetto non presente in borsa");
			return;
		}
			
		Attrezzo attrezzoInBorsa = partita.getGiocatore().getBorsa().getAttrezzo(getParametro());	
		io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzoInBorsa, partita));
	}
	
	

}
