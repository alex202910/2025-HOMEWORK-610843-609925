package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{

	final static private String NOME_ATTREZZO_DEFAULT = "lanterna";
	private String attrezzoPerVedere;
	
	
	public StanzaBuia(String nome, String attrezzo){
		super(nome);
		this.attrezzoPerVedere = attrezzoPerVedere;
	}
	
	public StanzaBuia(String nome){
		this(nome, NOME_ATTREZZO_DEFAULT);
	}
	
	
	
	@Override
	public String getDescrizione() {
		if( this.hasAttrezzo(attrezzoPerVedere)) {
			return this.getDescrizione();
		}else {
			
			return "\nqui c'è buio pesto, ritorna con qualcosa che fa luce...";
	}
	
	}
}
