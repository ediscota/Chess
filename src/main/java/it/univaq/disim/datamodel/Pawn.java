package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Pawn extends Piece {
	
	private boolean hasMoved;

	public Pawn(Color color, int xCord, int yCord) {
		super(color, xCord, yCord);
		this.hasMoved = false;
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

		int direction = (this.getColor() == Color.BIANCO) ? 1 : -1;

		
		/*int endYCord = yCord + direction;

		if (board.isValidLocation(xCord, endYCord)) {
			availableMoves.add(new Move(xCord, yCord, xCord, endYCord));
		}*/
		
		int endXCord = xCord + direction;

		if (board.getPieceAt(endXCord,yCord) == null && board.isValidLocation(endXCord, yCord)) {
			availableMoves.add(new Move(xCord, yCord, endXCord, yCord));
		
			if (!this.hasMoved && board.isValidLocation(xCord + (2 * direction), yCord)) {
				availableMoves.add(new Move(xCord, yCord, xCord + (2 * direction), yCord));
				this.hasMoved = true;
			}
		
		}
		
		// Controllo mosse di cattura
		/*int newX1 = xCord + 1;
		int newX2 = xCord - 1;
		int newYCattura = yCord + direction;
		if (board.isValidLocation(newX1, newYCattura)) {
			Piece destinationPiece1 = board.getPieceAt(newX1, newYCattura);
			if (destinationPiece1 != null && destinationPiece1.getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, newX1, newYCattura));
			}
		}
		if (board.isValidLocation(newX2, newYCattura)) {
			Piece destinationPiece2 = board.getPieceAt(newX2, newYCattura);
			if (destinationPiece2 != null && destinationPiece2.getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, newX2, newYCattura));
			}*/
			
			
		int newX1 = yCord + 1;
		int newX2 = yCord - 1;
		int newYCattura = xCord + direction;
		if (board.isValidLocation(newYCattura, newX1)) {
			Piece destinationPiece1 = board.getPieceAt(newYCattura, newX1);
			if (destinationPiece1 != null && destinationPiece1.getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, newYCattura, newX1));
			}
		}
		if (board.isValidLocation(newYCattura, newX2)) {
			Piece destinationPiece2 = board.getPieceAt(newYCattura, newX2);
			if (destinationPiece2 != null && destinationPiece2.getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, newYCattura, newX2));
			}
				
				
		}
		return availableMoves;
	}

}