package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();//seconda parola: eventuale parametro
		try {
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
			nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’
			nomeClasse += nomeComando.substring(1);
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
			comando = (Comando)Class.forName(nomeClasse).newInstance();
			// POSSIBILE ALTERNATIVA basata sul rendere il tipo Class<Comando> esplicito:
			// comando = ((Class<Comando>)Class.forName(nomeClasse.toString())).newInstance();
			comando.setParametro(parametro);
//	
		}
		catch (Exception e) {
			comando = new ComandoNonValido();
		}
		return comando;
	}
}
