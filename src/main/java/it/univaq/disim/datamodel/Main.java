package it.univaq.disim.datamodel;

import it.univaq.disim.service.Board;
import it.univaq.disim.service.Save;
import java.io.File;
import it.univaq.disim.service.Statistic;

import it.univaq.disim.service.Game;
import java.util.*;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		
		 System.out.print("Scegli la modalità di gioco\n"
				+ "1 - inizia una nuova partita\n"
				+ "2 - carica una partita\n"
				+ "3 - statistiche partite\n");
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
			default:
				break;
			}			
			break;
		case 2:
			String directoryPath = "C:\\Users\\matte\\OneDrive\\Documenti\\GitHub\\scacchi-ddc\\partite"; 

	        File directory = new File(directoryPath);
	        
			if (directory.exists() && directory.isDirectory()) {
	            File[] files = directory.listFiles();

	            if (files != null) {
	                System.out.println("Lista di file nella directory:");
	                
	                int c=0;
	                for (File file : files) {
	                    if (file.isFile()) {
	                        System.out.println(c + ":" + file.getName());
	                        c++;
	                    }
	                }
	            } else {
	                System.out.println("La directory è vuota.");
	            }
	        } else {
	            System.out.println("La directory non esiste o non è valida.");
	        }
			
			System.out.println("Seleziona partita da recuperare");
			int choice3 = scanner.nextInt();
			File[] files = directory.listFiles();
			File f=null;
			for (int i = 0 ; i < files.length ; i++) {
				
				if(i == choice3) {
					f = files[i];
				}
				
			}
			
			System.out.println("hai selezionato : " + f.getName());
			
			Save save = new Save();
			try {
				save.deserialize(f);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			break;
		
		case 3: 
			
			Statistic statistic = new Statistic();
			statistic.statistic();
			break;
			
		default: 
			break;
			
		}
          
	}                

}
