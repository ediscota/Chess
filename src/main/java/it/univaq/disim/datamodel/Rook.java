package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Rook extends Piece {

	public Rook(Color color) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "♖";
		} else {
			return "♜";
		}
	}

	public List<Move> availableMoves(Board board, int xCord, int yCord) {
		List<Move> availableMoves = new ArrayList<>();

		// Mosse verso l'alto
		for (int x = xCord + 1; x < Board.getColumnsnumber(); x++) {
			if (board.isValidLocation(x, yCord) || board.getPieceat(x, yCord).getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, x, yCord));
			} else {
				break;
			}
		}

		// Mosse verso il basso
		for (int x = xCord - 1; x >= 0; x--) {
			if (board.isValidLocation(x, yCord) || board.getPieceat(x, yCord).getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, x, yCord));
			} else {
				break;
			}
		}

		// Mosse verso destra
		for (int y = yCord + 1; y < Board.getLinesnumber(); y++) {
			if (board.isValidLocation(xCord, y) || board.getPieceat(xCord, y).getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, xCord, y));
			} else {
				break;
			}
		}

		// Mosse verso sinistra
		for (int y = yCord - 1; y >= 0; y--) {
			if (board.isValidLocation(xCord, y) || board.getPieceat(xCord, y).getColor() != this.getColor()) {
				availableMoves.add(new Move(xCord, yCord, xCord, y));
			} else {
				break;
			}
		}

		return availableMoves;
	}
}       //aaa
