package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Knight extends Piece {
	public Knight(Color color) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "♘";
		} else {
			return "♞";
		}
	}
@Override
	public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {
		List<Move> availableMoves = new ArrayList<>();

		int[][] moves = { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 } };

		for (int[] move : moves) {
			int x = xCord + move[0];
			int y = yCord + move[1];
			Piece pieceAtDestination = board.getPieceAt(x, y);
			if (board.isValidLocation(x, y) || pieceAtDestination.getColor() != this.getColor()) {

				availableMoves.add(new Move(xCord, yCord, x, y));

			}
		}

		return availableMoves;
	}
}
