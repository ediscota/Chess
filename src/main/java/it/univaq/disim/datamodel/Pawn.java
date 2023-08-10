package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Pawn extends Piece {

	public Pawn(Color color, int xCord, int yCord) {
		super(color, xCord, yCord);
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

		int endYCord = yCord + direction;

		if (board.isValidLocation(xCord, endYCord)) {
			availableMoves.add(new Move(xCord, yCord, xCord, endYCord));
		}
		// Controllo mosse di cattura
		int newX1 = xCord + 1;
		int newX2 = xCord - 1;
		int newYCattura = yCord + direction;
		if (board.isValidLocation(newX1, newYCattura)) {
			Piece destinationPiece1 = board.getPieceAt(newX1, newYCattura);
			if (destinationPiece1 != null && destinationPiece1.getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, newX1, newYCattura));
			}
		}
		if (board.isValidLocation(newX1, newYCattura)) {
			Piece destinationPiece2 = board.getPieceAt(newX2, newYCattura);
			if (destinationPiece2 != null && destinationPiece2.getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, newX2, newYCattura));
			}
		}
		return availableMoves;
	}
}