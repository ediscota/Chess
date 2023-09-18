package it.univaq.disim.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Bishop extends Piece implements Serializable {

	private static final long serialVersionUID = 9022810192548993956L;

	public Bishop(Color color, int xCord, int yCord, int value) {
		super(color, xCord, yCord, value);
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "a_b";
		} else {
			return "a_n";
		}
	}

	@Override
	public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {
		List<Move> availableMoves = new ArrayList<>();

		int[][] directions = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

		for (int[] dir : directions) {
			int x = xCord + dir[0];
			int y = yCord + dir[1];

			while (board.isValidLocation(x, y)) {
				Piece pieceAtDestination = board.getPieceAt(x, y);

				if (pieceAtDestination == null) {
					availableMoves.add(new Move(xCord, yCord, x, y, false));
				} else if (pieceAtDestination.getColor() != this.getColor()) {
					availableMoves.add(new Move(xCord, yCord, x, y, true));
					break;
				} else {
					break; // Esci dal ciclo se trovi un pezzo dello stesso colore
				}

				x += dir[0];
				y += dir[1];
			}
		}

		return availableMoves;
	}

	public int getValue() {

		return 3;
	}

}
