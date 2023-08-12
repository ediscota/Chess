package it.univaq.disim.datamodel;

import it.univaq.disim.service.Board;

import it.univaq.disim.service.Game;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Scegli la modalità di gioco\n"
				+ "1 - inizia una nuova partita\n"
				+ "2 - carica una partita\n");
		int choice = scanner.nextInt();
		
		switch(choice)
		{
		case 1:
			Game game = new Game();
			System.out.print("1 -  1 vs 1\n"
					+ "2 -  1 vs CPU\n");
			int choice2 = scanner.nextInt();
			switch(choice2)
			{
			case 1:
				game.startNewHumanGame();
				break;
			case 2:
				game.startNewCPUGame();
				break;
			}			
			break;
		case 2:
			//gestire il caricamento di una partita esistente
			break;
		}

		
		
		
          
	}                

}
