package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze buie */
	private static final String STANZEBUIE_MARKER = "StanzeBuie:";    

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze magiche */
	private static final String STANZEMAGICHE_MARKER = "StanzeMagiche:";

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze bloccate */
	private static final String STANZEBLOCCATE_MARKER = "StanzeBloccate:";

	/* prefisso di una singola riga di testo contenente tutti i Personaggi */
	private static final String PERSONAGGI_MARKER = "Personaggi:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N11
		StanzeBuie: N10 lanterna
		StanzeMagiche: Laboratorio-Campus 3
		StanzeBloccate: atrio nord chiave
		Inizio: atrio
		Vincente: biblioteca  
		Attrezzi: martello 10 biblioteca, pinza 2 N10, osso 1 atrio, lancia 4 atrio, scudo 7 atrio, lanterna 3 N10, chiave 1 N11
		Personaggi: strega N10 Sono-Bulabula-la-permalosa, mago Laboratorio-Campus Sono-il-mago-dei-polli pollo 4, cane N11 Sono-Buld-il-cane-parlante escrementi 1
		Uscite: atrio nord biblioteca, atrio est N11, atrio sud N10, atrio ovest Laboratorio-Campus

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		File file = new File(nomeFile);
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader stringReader) {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(stringReader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			
			this.leggiECreaStanzeBuie();
	
			this.leggiECreaStanzeMagiche();

			this.leggiECreaStanzeBloccate();

			this.leggiInizialeEvincente();
	
			this.leggiECollocaAttrezzi();
	
			this.leggiEAggiungiPersonaggio();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	void leggiEAggiungiPersonaggio() throws FormatoFileNonValidoException {

		String robaDiPersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		AbstractPersonaggio personaggio = null;
		try (Scanner scannerDiLinea = new Scanner(robaDiPersonaggi)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("nome del personaggio"));
				String nome = scannerDiLinea.next().replace("-", " ");
				nome = Character.toUpperCase(nome.charAt(0)) + nome.substring(1);
				
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("collocazione del personaggio"));
				String collocazione = scannerDiLinea.next().replace("-", " ");
				
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("descrizione personaggio "+nome));
				String descrizione = scannerDiLinea.next().replace("-", " ");
				if(descrizione.endsWith(",")) {
					descrizione = descrizione.substring(0, descrizione.length()-1);
				}


				if(nome.equals("Strega")) {
					personaggio = new Strega(nome, descrizione);
				}
				else {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo del personaggio "+nome+" non è valido "));
					String attrezzo = scannerDiLinea.next().replace("-", " ");

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo del personaggio "+nome+" non è valido "));
					String pesoStringa = scannerDiLinea.next().replace(",", "");	//rimuove la prima virgola finale
					try {
						int peso = Integer.parseInt(pesoStringa);
						if(nome.equals("Mago")) {
							personaggio = new Mago(nome, descrizione, new Attrezzo(attrezzo, peso));
						}
						else {
							if(nome.equals("Cane")) {
								personaggio = new Cane(nome, descrizione, new Attrezzo(attrezzo, peso));
							}
							else {
								throw new FormatoFileNonValidoException("non esiste questo tipo di personaggio");
							}
						}
					}catch(NumberFormatException e){
						throw new FormatoFileNonValidoException(pesoStringa + "non è un numero");
					}

				}
				
				if(!this.nome2stanza.containsKey(collocazione)) {
					throw new FormatoFileNonValidoException("non puoi collocare un personaggio in una stanza inesistente");
				}
				this.nome2stanza.get(collocazione).setPersonaggio(personaggio);
				
			}
		}

	}

	void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException{

		String nomiStanzeMagiche = this.leggiRigaCheCominciaPer(STANZEMAGICHE_MARKER);
		Stanza stanza = null;

		try (Scanner scannerDiLinea = new Scanner(nomiStanzeMagiche)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza magica"));
				String nome = scannerDiLinea.next().replace("-", " ");
				
				if(nome.endsWith(",") || !scannerDiLinea.hasNext()) {
					nome.replaceFirst(",$", "");
					stanza = new StanzaMagica(nome);
				}
				else {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("soglia magica "+nome ));
					String nomeSoglia = scannerDiLinea.next().replaceFirst(",$", "");	//rimuove la prima virgola finale
					try {
						int soglia = Integer.parseInt(nomeSoglia);
						stanza = new StanzaMagica(nome, soglia);
					}catch(NumberFormatException e){
						throw new FormatoFileNonValidoException(nomeSoglia + "non è un numero");
					}

				}
				this.nome2stanza.put(nome, stanza);
			}
		}
	}

	void leggiECreaStanzeBuie() throws FormatoFileNonValidoException{

		String nomiStanzeMagiche = this.leggiRigaCheCominciaPer(STANZEBUIE_MARKER);
		Stanza stanza = null;
		try (Scanner scannerDiLinea = new Scanner(nomiStanzeMagiche)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza buia"));
				String nome = scannerDiLinea.next().replace("-", " ");
				
				if(nome.endsWith(",") || !scannerDiLinea.hasNext()) {
					nome.replaceFirst(",$", "");
					stanza = new StanzaBuia(nome);
				}
				else {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo per vedere in "+nome ));
					String attrezzo = scannerDiLinea.next().replaceFirst(",$", "");	//rimuove la prima virgola finale

					stanza = new StanzaBuia(nome, attrezzo);
				}
				this.nome2stanza.put(nome, stanza);
			}
		} 

	}

	void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException{

		String nomiStanzeBloccate = this.leggiRigaCheCominciaPer(STANZEBLOCCATE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(nomiStanzeBloccate)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza bloccata"));
				String nome = scannerDiLinea.next().replace("-", " ");
				
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata della stanza "+nome));
				String dir = scannerDiLinea.next();
				
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo sbloccante "+nome+" per la direzione "+dir));
				String attrezzo = scannerDiLinea.next().replaceFirst(",$", "");	//rimuove la prima virgola finale

				Stanza stanza = new StanzaBloccata(nome, attrezzo, Direzione.valueOf(dir.toUpperCase()));
				this.nome2stanza.put(nome, stanza);
			}
		} 
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		//		Scanner scanner = new Scanner(string);
		//		scanner.useDelimiter(",");
		//		try (Scanner scannerDiParole = scanner) {
		//			result.add(scannerDiParole.next());
		//		}
		try (Scanner scanner = new Scanner(string)) {
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				result.add(scanner.next().trim());
			}
		}
		return result;
	}


	//	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
	//		String nomeStanzaIniziale = null;
	//		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
	//		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
	//		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
	//		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
	//		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
	//		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	//	}

	void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).trim();
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");

		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).trim();
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");

		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}


	void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();

				
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
	
				
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
				
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
			
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next().replace("-", " ");
				
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next().replaceFirst(",$", "").replace("-", " ");	//rimuove la prima virgola finale

				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(Direzione.valueOf(dir.toUpperCase()), arrivoA);
		arrivoA.impostaStanzaAdiacente(Direzione.valueOf(dir.toUpperCase()).getDirezioneOpposta() , partenzaDa);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Map<String, Stanza> getNome2stanza() {
		return nome2stanza;
	}

}