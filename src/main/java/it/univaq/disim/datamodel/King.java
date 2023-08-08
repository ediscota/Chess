package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class King extends Piece {

	public King(Color color) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "r_b";
		} else {
			return "r_n";
		}
	}
@Override
	public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {
		List<Move> availableMoves = new ArrayList<>();

		int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

		for (int[] dir : directions) {
			int x = xCord + dir[0];
			int y = yCord + dir[1];
			Piece pieceAtDestination = board.getPieceAt(x, y);
			if (board.isValidLocation(x, y) || pieceAtDestination.getColor() != this.getColor()) {

				availableMoves.add(new Move(xCord, yCord, x, y));
			}
		}

		return availableMoves;
	}
}
