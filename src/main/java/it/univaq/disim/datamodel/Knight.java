package it.univaq.disim.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Knight extends Piece implements Serializable{
	
	private static final long serialVersionUID = -5219964985927524803L;
	
	public Knight(Color color, int xCord, int yCord, int value) {
		super(color, xCord, yCord, value);
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "c_b";
		} else {
			return "c_n";
		}
	}

	@Override
	public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {
		List<Move> availableMoves = new ArrayList<>();

		int[][] moves = { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 } };

		for (int[] move : moves) {
			int x = xCord + move[0];
			int y = yCord + move[1];
			if (board.isValidLocation(x, y)) {
				Piece pieceAtDestination = board.getPieceAt(x, y);
				if (pieceAtDestination == null) {

					availableMoves.add(new Move(xCord, yCord, x, y, false));
				}
				else if(pieceAtDestination.getColor() != this.getColor()) {
					availableMoves.add(new Move(xCord, yCord, x, y, true));
				}
			}
		}

		return availableMoves;
	}

}
