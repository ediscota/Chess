package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Bishop extends Piece {

	public Bishop(Color color) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "♗";
		} else {
			return "♝";
		}
	}
@Override
	public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {
		List<Move> availableMoves = new ArrayList<>();

		int[][] directions = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

		for (int[] dir : directions) {
			int x = xCord + dir[0];
			int y = yCord + dir[1];
			Piece pieceAtDestination = board.getPieceat(x, y);
			while (board.isValidLocation(x, y) || pieceAtDestination.getColor() != this.getColor()) {

				availableMoves.add(new Move(xCord, yCord, x, y));

				x += dir[0];
				y += dir[1];
				pieceAtDestination = board.getPieceat(x, y);
			}
		}

		return availableMoves;
	}

}
