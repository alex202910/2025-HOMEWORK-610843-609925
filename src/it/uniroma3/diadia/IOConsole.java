package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole implements IO{
	
	private Scanner scanner; 
	
	public IOConsole(Scanner sc) {
		this.scanner = sc;
	}
	
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public String leggiRiga() {
		return scanner.nextLine();
	}
}