package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO{


	private int inputIndice;
	private List<String > input;
	private List<String > output;
	
	public IOSimulator(List<String > input) {
		this.input = input;
		this.inputIndice = 0;
		this.output = new ArrayList<String>();
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		output.add(msg);
	}
	
	
	@Override
	public String leggiRiga() {
		if(inputIndice < input.size())
			return input.get(inputIndice++);
		return "";
		
	}
	
	public List<String> getOutput() {
		return output;
	}
	
}
