package it.univaq.disim.datamodel;

import lombok.Data;

@Data
public class Board {
	
	private Piece [][] board = new Piece[8][8];

	//prende in input una posizione e controlla se è valida
	public boolean isValidLocation (int x, int y){

		if (x < 0 || x > 8  || y < 0 || y > 8)
			return false;
		else
			return getPieceat(x,y) == null;



	
	
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