package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class IOSimulator implements IO{


	private String[] input;
	private int inputIndice;
	private String[] output; 
	private int outputIndice;
	
	
	public IOSimulator(String[] input) {
		this.input = input;
		this.inputIndice = 0;
		this.output = new String[input.length + 12];
		this.outputIndice = 0;
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		if(outputIndice> output.length )
			return;
		output[outputIndice++] = msg;
	}
	
	
	@Override
	public String leggiRiga() {
		if(inputIndice < input.length)
			return input[inputIndice++];
		return "";
		
	}
	
	public String[] getOutput() {
		return output;
	}
	
}
