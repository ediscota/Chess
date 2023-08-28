package it.univaq.disim.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class King extends Piece implements Serializable{
	
	private static final long serialVersionUID = 2642370088677062127L;
	
	public King(Color color, int xCord, int yCord, int value) {
        super(color, xCord, yCord, value);
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
			int newX = xCord + dir[0];
			int newY = yCord + dir[1];
			if (board.isValidLocation(newX, newY)){
			Piece pieceAtDestination = board.getPieceAt(newX, newY);
			if (pieceAtDestination == null) {

				availableMoves.add(new Move(xCord, yCord, newX, newY, false));
			}
			else if(pieceAtDestination.getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, newX, newY, true));
			}
		}
	}
		return availableMoves;
	}

	public int getValue() {
	
		return 9;
	}
}
