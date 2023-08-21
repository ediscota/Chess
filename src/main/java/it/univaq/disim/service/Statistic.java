package it.univaq.disim.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;

import it.univaq.disim.datamodel.Piece;

public class Statistic implements Serializable {
	
	
	private static final long serialVersionUID = -8374799742368830313L;
	public void statistic(Game game) throws ClassNotFoundException {
		String directoryPath = "C:\\Users\\matte\\OneDrive\\Documenti\\GitHub\\scacchi-ddc\\partite"; 

		File directory = new File(directoryPath);
    
		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();
			
			for(int i = 0; i < files.length; ++i) {
				
				try {
					
					FileInputStream fis = new FileInputStream(files[i]);
					ObjectInputStream ois = new ObjectInputStream(fis);
					Game g = (Game) ois.readObject();
					
					
					
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
			
		} else {
			System.out.println("La directory non esiste o non è valida.");
		}
	

	}
}
