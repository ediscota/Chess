package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data public class Pawn extends Piece {


    @Override
    public String toString() {
        if (getColor() == Color.BIANCO) {
            return "\u2659"; // Simbolo pedone bianco: ♙
        } else {
            return "\u265F"; // Simbolo pedone nero: ♟
        }
    }
    
   
    public List <Move> getAvaibleMoves (Board board, int xCord, int yCord) {
		return null;
	} 
    List<Move> availableMoves = new ArrayList<>();

    int direction = (this.getColor() == Color.BIANCO) ? 1: -1;
     
    int endYCord = yCord + direction;

    if (board.isValidlocation(x , newY)) {
        availableMoves.add(new Move(xCord, yCord, xCord, endYCord));
    }
	 // Controllo mosse di cattura
    int newX1 = xCord + 1;
    int newX2 = xCord - 1;
    int newYCattura = yCord + direction;

    if (board.getPieceat(newX1, newYCattura) && scacchiera.getPezzoAt(newX1, newYCattura).getColor() != getColor()) {
       avaibleMoves.add(new Move(xCord, yCord, newX1, newYCattura));
    }

    if (board.getPieceat(newX2, newYCattura) && scacchiera.getPezzoAt(newX2, newYCattura).getColor() != getColor()) {
       avaibleMoves.add(new Move(xCord, yCord, newX2, newYCattura));
    }
	
}