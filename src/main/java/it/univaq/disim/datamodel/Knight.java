package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "♘";
		} else {
			return "♞";
		}
	}

	public List<Move> availableMoves(Board board, int xCord, int yCord) {
		List<Move> availableMoves = new ArrayList<>();

		int[][] moves = { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 } };

		for (int[] move : moves) {
			int x = xCord + move[0];
			int y = yCord + move[1];
			Piece pieceAtDestination = board.getPieceat(x, y);
			if (board.isValidLocation(x, y) || pieceAtDestination.getColor() != this.getColor()) {

				availableMoves.add(new Move(xCord, yCord, x, y));

			}
		}

		return availableMoves;
	}
}
