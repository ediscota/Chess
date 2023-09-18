package it.univaq.disim.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Queen extends Piece implements Serializable {
	public Queen(Color color, int xCord, int yCord, int value) {
		super(color, xCord, yCord, value);
	}

	private static final long serialVersionUID = 574327588593668986L;

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "d_b";
		} else {
			return "d_n";
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
			Piece destinationPiece = null;
			while (board.isValidLocation(newX, newY)) {
				destinationPiece = board.getPieceAt(newX, newY);

				if (destinationPiece == null) {

					availableMoves.add(new Move(xCord, yCord, newX, newY, false));
				} else if (destinationPiece.getColor() != this.getColor()) {
					availableMoves.add(new Move(xCord, yCord, newX, newY, true));
				}

				if (destinationPiece != null) {
					break; // appena incontra un ostacolo si ferma
				}

				newX += dir[0];
				newY += dir[1];

			}
		}

		return availableMoves;
	}

	public int getValue() {

		return 9;
	}

}
