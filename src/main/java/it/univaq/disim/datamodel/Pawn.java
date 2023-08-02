package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data public class Pawn extends Piece {


    @Override
    public String toString() {
        if (this.getColor() == Color.BIANCO) {
            return "♙"; 
        } else {
            return "♟"; 
        }
    }
    
   
    public List <Move> availableMoves (Board board, int xCord, int yCord) {
		
    List<Move> availableMoves = new ArrayList<>();

    int direction = (this.getColor() == Color.BIANCO) ? 1: -1;
     
    int endYCord = yCord + direction;

    if (board.isValidLocation(xCord , endYCord)) {
        availableMoves.add(new Move(xCord, yCord, xCord, endYCord));
    }
	 // Controllo mosse di cattura
    int newX1 = xCord + 1;
    int newX2 = xCord - 1;
    int newYCattura = yCord + direction;

    if (board.getPieceat(newX1, newYCattura).getColor() != this.getColor()) {
    	availableMoves.add(new Move(xCord, yCord, newX1, newYCattura));
    }

    if (board.getPieceat(newX2, newYCattura).getColor() != this.getColor()) {
    	availableMoves.add(new Move(xCord, yCord, newX2, newYCattura));
    }
	return availableMoves;
	
  }
}   