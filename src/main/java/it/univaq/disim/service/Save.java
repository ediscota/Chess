package it.univaq.disim.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

	
public class Save implements Serializable{
	
	private static final long serialVersionUID = 6194243878039641792L;

	public void serialize(Game g,Board b,HumanPlayer p1, HumanPlayer p2) throws ClassNotFoundException{
		
		String folderPath = "C:\\Users\\matte\\OneDrive\\Documenti\\GitHub\\scacchi-ddc\\partite";
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        String formattedDateTime = currentDateTime.format(dateFormatter);
        
        try {
        	FileOutputStream fos = new FileOutputStream ( folderPath + "\\" + formattedDateTime + ".txt");
        	ObjectOutputStream oos = new ObjectOutputStream(fos);
        	oos.writeObject(g);
        	oos.writeObject(b);
        	oos.writeObject(p1);
        	oos.writeObject(p2);
        	
        	oos.close();
        	
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        	
	}
	
	public void deserialize(File file) throws  ClassNotFoundException {
        
		try {
			
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Game g = (Game) ois.readObject();
			Board b = (Board) ois.readObject();
			HumanPlayer p1 = (HumanPlayer) ois.readObject();
			HumanPlayer p2 = (HumanPlayer) ois.readObject();
			
			
			
			g.playGame(b, p1, p2);
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void printFile(Scanner scanner) {
		
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
		
		try {
			this.deserialize(f);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
}