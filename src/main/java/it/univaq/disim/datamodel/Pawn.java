package it.univaq.disim.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Pawn extends Piece implements Serializable {
	

	private static final long serialVersionUID = -511250831636830991L;
	
	public Pawn(Color color, int xCord, int yCord, int value) {
		super(color, xCord, yCord, value);
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "p_b";
		} else {
			return "p_n";
		}
	}

	@Override
	public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {

		List<Move> availableMoves = new ArrayList<>();

		int direction = (this.getColor() == Color.BIANCO) ? -1 : 1;
		
		int endXCord = xCord + direction;

		if (board.getPieceAt(endXCord,yCord) == null && board.isValidLocation(endXCord, yCord)) {
			availableMoves.add(new Move(xCord, yCord, endXCord, yCord, false));
			
			 // controllo per mossa doppia all'inizio
	        if ((direction == 1 && xCord == 1) || (direction == -1 && xCord == 6)) {
	            int doubleMove = xCord + 2 * direction;
	            if (board.getPieceAt(doubleMove, yCord) == null) {
	                availableMoves.add(new Move(xCord, yCord, doubleMove, yCord, false));
	            }
	        }
		}
		
		// Controllo mosse di cattura			
			
		int newX1 = yCord + 1;
		int newX2 = yCord - 1;
		int newYCattura = xCord + direction;
		if (board.isValidLocation(newYCattura, newX1)) {
			Piece destinationPiece1 = board.getPieceAt(newYCattura, newX1);
			if (destinationPiece1 != null && destinationPiece1.getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, newYCattura, newX1, true));
			}
		}
		if (board.isValidLocation(newYCattura, newX2)) {
			Piece destinationPiece2 = board.getPieceAt(newYCattura, newX2);
			if (destinationPiece2 != null && destinationPiece2.getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, newYCattura, newX2, true));
			}
				
				
		}
		return availableMoves;
	}
	
	public int getValue() {
		
			return 1;
		}

}