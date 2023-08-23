package it.univaq.disim.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;

public class Statistic implements Serializable {
	
	
	private static final long serialVersionUID = -8374799742368830313L;
	
	public void statistic() throws ClassNotFoundException {
		
		String directoryPath = "C:\\Users\\matte\\OneDrive\\Documenti\\GitHub\\scacchi-ddc\\partite"; 

		File directory = new File(directoryPath);
    
		if (directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();
			Game[] deserializeGames = new Game[files.length];
			
			for(int i = 0; i < files.length; ++i) {
				
				try {
					
					FileInputStream fis = new FileInputStream(files[i]);
					ObjectInputStream ois = new ObjectInputStream(fis);
					Game g = (Game) ois.readObject();
					
					deserializeGames [ i ] = g;
			      
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
			
			mergeSort(deserializeGames, 0, deserializeGames.length - 1);

	        System.out.println("Partite ordinate in base alle mosse: " + Arrays.toString(deserializeGames));
	        
	        mergeSort1(deserializeGames, 0, deserializeGames.length - 1);
	        
	        System.out.println("Partite ordinate in base alle mosse: " + Arrays.toString(deserializeGames));

		}
	}
			
			
	    public static void mergeSort(Game[] deserializeGames, int left, int right) {
	        if (left < right) {
	            int middle = (left + right) / 2;

	            mergeSort(deserializeGames, left, middle);
	            mergeSort(deserializeGames, middle + 1, right);

	            merge(deserializeGames, left, middle, right);
	            
	        }
	    }

	    public static void merge(Game[] deserializeGames, int left, int middle, int right) {
	        int n1 = middle - left + 1;
	        int n2 = right - middle;

	        Game[] leftArray = new Game[n1];
	        Game[] rightArray = new Game[n2];

	        for (int i = 0; i < n1; i++) {
	            leftArray[i] = deserializeGames[left + i];
	        }
	        for (int j = 0; j < n2; j++) {
	            rightArray[j] = deserializeGames[middle + 1 + j];
	        }

	        int i = 0, j = 0, k = left;

	        while (i < n1 && j < n2) {
	            if (leftArray[i].getBlackMoves().size()+leftArray[i].getWhiteMoves().size() >= rightArray[j].getBlackMoves().size()+ rightArray[j].getWhiteMoves().size()) {
	            	deserializeGames[k] = leftArray[i];
	                i++;
	            } else {
	            	deserializeGames[k] = rightArray[j];
	                j++;
	            }
	            k++;
	        }

	        while (i < n1) {
	        	deserializeGames[k] = leftArray[i];
	            i++;
	            k++;
	        }

	        while (j < n2) {
	        	deserializeGames[k] = rightArray[j];
	            j++;
	            k++;
	        }
	    }
	    
	    public static void mergeSort1(Game[] deserializeGames, int left, int right) {
	        if (left < right) {
	            int middle = (left + right) / 2;

	            mergeSort(deserializeGames, left, middle);
	            mergeSort(deserializeGames, middle + 1, right);

	            merge1(deserializeGames, left, middle, right);
	        }
	    }

	    public static void merge1(Game[] deserializeGames, int left, int middle, int right) {
	        int n1 = middle - left + 1;
	        int n2 = right - middle;

	        Game[] leftArray = new Game[n1];
	        Game[] rightArray = new Game[n2];

	        for (int i = 0; i < n1; i++) {
	            leftArray[i] = deserializeGames[left + i];
	        }
	        for (int j = 0; j < n2; j++) {
	            rightArray[j] = deserializeGames[middle + 1 + j];
	        }

	        int i = 0, j = 0, k = left;

	        while (i < n1 && j < n2) {
	            if (leftArray[i].getDeadBlackPieces().size()+leftArray[i].getDeadWhitePieces().size() < rightArray[j].getDeadBlackPieces().size()+ rightArray[j].getDeadWhitePieces().size()) {
	            	deserializeGames[k] = leftArray[i];
	                i++;
	            } else {
	            	deserializeGames[k] = rightArray[j];
	                j++;
	            }
	            k++;
	        }

	        while (i < n1) {
	        	deserializeGames[k] = leftArray[i];
	            i++;
	            k++;
	        }

	        while (j < n2) {
	        	deserializeGames[k] = rightArray[j];
	            j++;
	            k++;
	        }
	    }
				
}	

