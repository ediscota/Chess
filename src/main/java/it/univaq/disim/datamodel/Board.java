package it.univaq.disim.datamodel;

import lombok.Data;

@Data
public class Board {
	
	private Piece [][] board = new Piece[8][8];

	//prende in input una posizione e controlla se è valida
	public boolean isValidLocation (Location location){
		int x= location.getX();
		int y = location.getY();
		if (x < 0 || x > 8  || y < 0 || y > 8)
			return false;
		else
			return getPieceat(location) == null;



	
	
}
    //prende in input una locazione, prima controlla se è valida tramite isValidLocation, poi restituisce il pezzo che
	//corrisponde a quelle coordinate, oppure se la posizione non è valida lancia una eccezione
      public Piece getPieceat  (Location location) throws IllegalArgumentException {
		int x = location.getX();

		int y = location.getY();

		if (isValidLocation(location))
			return board[x][y];

		else
			throw new IllegalArgumentException("posizione non valida");


	}
}