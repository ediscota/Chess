package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Queen extends Piece {
	public Queen(Color color, int xCord, int yCord) {
        super(color, xCord, yCord);
    }

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "R_b";
		} else {
			return "R_n";
		}
	}

@Override
	public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {
		List<Move> availableMoves = new ArrayList<>();

		int[][] directions = { { 0, 1 }, // Spostamento verso destra
				{ 0, -1 }, // Spostamento verso sinistra
				{ 1, 0 }, // Spostamento verso l'alto
				{ -1, 0 }, // Spostamento verso il basso
				{ 1, 1 }, // Spostamento in alto a destra
				{ 1, -1 }, // Spostamento in alto a sinistra
				{ -1, 1 }, // Spostamento in basso a destra
				{ -1, -1 } // Spostamento in basso a sinistra
		};

		for (int[] dir : directions) {
			int newX = xCord + dir[0];
			int newY = yCord + dir[1];
			Piece destinationPiece = board.getPieceAt(newX, newY);
			while (board.isValidLocation(newX, newY) || destinationPiece.getColor() != this.getColor()) {

				availableMoves.add(new Move(xCord, yCord, newX, newY));

			

			newX += dir[0];
			newY += dir[1];
			destinationPiece = board.getPieceAt(newX, newY);
			}
		}

		return availableMoves;
	}
}
