package it.univaq.disim.datamodel;

import lombok.Data;

@Data
public class Board {
	 static final int columnsNumber = 8;
	 static final int linesNumber = 8;
	
	private Piece [][] board = new Piece[8][8];
	 

	//prende in input una posizione e controlla se è valida
	public boolean isValidLocation (int x, int y){

		if (x < 0 || x > columnsNumber || y < 0 || y > linesNumber)
			return false;
		else
			return true;



	
	
}
    //prende in input una locazione, prima controlla se è valida tramite isValidLocation, poi restituisce il pezzo che
	//corrisponde a quelle coordinate, oppure se la posizione non è valida lancia una eccezione
      public Piece getPieceat  (int x, int y) throws IllegalArgumentException {


		if (isValidLocation(x,y))
			return board[x][y];

		else
			throw new IllegalArgumentException("posizione non valida");


	}
}