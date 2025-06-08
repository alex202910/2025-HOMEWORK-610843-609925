package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class CaricatoreProprieta {
	private static final String CFU_MARKER = "CFUIniziali:";
	private static final String BORSA_MARKER = "PesoBorsa:";
	private static final String ATTREZZI_STANZA_MARKER = "MaxStanzaAttrezzi:";
	private static final String STANZA_MAGICA_SOGLIA = "MaxSogliaMagica:";
	private static int cfu=20;
	private static int pesoBorsa=10;
	private static int AttrezziStanza = 10;
	private static int StanzaMagicaSoglia = 3;
	private LineNumberReader reader;
	
	
	public CaricatoreProprieta(String nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	public CaricatoreProprieta() {
	}
	
	public static int getCFU() {
		return cfu;
	}
	
	public static int getPBorsa() {
		return pesoBorsa;
	}
	
	public static int getAttrezziStanza() {
		return AttrezziStanza;
	}
	
	public static int getSoglia() {
		return StanzaMagicaSoglia;
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiCFU();
			this.leggiPesoBorsa();
			this.leggiSoglia();
			this.leggiAttrezzi();
			
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}
	
	private void leggiCFU() throws FormatoFileNonValidoException {
		String stringa= this.leggiRigaCheCominciaPer(CFU_MARKER);
		this.cfu = Integer.parseInt(stringa);
	}
	
	private void leggiPesoBorsa() throws FormatoFileNonValidoException {
		String stringa = this.leggiRigaCheCominciaPer(BORSA_MARKER);
		this.pesoBorsa= Integer.parseInt(stringa);
	}
	
	private void leggiAttrezzi() throws FormatoFileNonValidoException {
		String stringa= this.leggiRigaCheCominciaPer(ATTREZZI_STANZA_MARKER);
		this.AttrezziStanza = Integer.parseInt(stringa);
	}
	
	private void leggiSoglia() throws FormatoFileNonValidoException {
		String stringa= this.leggiRigaCheCominciaPer(STANZA_MAGICA_SOGLIA);
		this.StanzaMagicaSoglia = Integer.parseInt(stringa);
	}
	
	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}
	
	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore)
			throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException(
					"Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
	}
	
	private List<String> separaStringheAlleVirgole(String string) {

		String[] parti = string.split(",");
		return new LinkedList<String>(Arrays.asList(parti));
	}
	
}