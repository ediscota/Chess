package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Rook extends Piece {

	public Rook(Color color, int xCord, int yCord) {
        super(color, xCord, yCord);
    }

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "t_b";
		} else {
			return "t_n";
		}
	}
@Override
	public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {
		List<Move> availableMoves = new ArrayList<>();

		// Mosse verso l'alto
		for (int x = xCord + 1; x < Board.getColumnsnumber(); x++) {
			if (board.isValidLocation(x, yCord) || board.getPieceAt(x, yCord).getColor() != this.getColor()) {
				if(!(this.hasAdjacentPieceOfSameColor(x, yCord, this.getColor(), board)))
					availableMoves.add(new Move(xCord, yCord, x, yCord));
			} else {
				break;
			}
		}

		// Mosse verso il basso
		for (int x = xCord - 1; x >= 0; x--) {
			if (board.isValidLocation(x, yCord) || board.getPieceAt(x, yCord).getColor() != this.getColor()) {
				if(!(this.hasAdjacentPieceOfSameColor(x, yCord, this.getColor(), board)))	
					availableMoves.add(new Move(xCord, yCord, x, yCord));
			} else {
				break;
			}
		}

		// Mosse verso destra
		for (int y = yCord + 1; y < Board.getLinesnumber(); y++) {
			if (board.isValidLocation(xCord, y) || board.getPieceAt(xCord, y).getColor() != this.getColor()) {
				if(!(this.hasAdjacentPieceOfSameColor(xCord, y, this.getColor(), board)))
					availableMoves.add(new Move(xCord, yCord, xCord, y));
			} else {
				break;
			}
		}

		// Mosse verso sinistra
		for (int y = yCord - 1; y >= 0; y--) {
			if (board.isValidLocation(xCord, y) || board.getPieceAt(xCord, y).getColor() != this.getColor()) {
				if(!(this.hasAdjacentPieceOfSameColor(xCord, y, this.getColor(), board)))
					availableMoves.add(new Move(xCord, yCord, xCord, y));
			} else {
				break;
			}
		}

		return availableMoves;
	}

@Override
public boolean hasAdjacentPieceOfSameColor(int xCord, int yCord, Color pieceColor, Board board) {
	if(xCord > 0) {
		if(board.isValidLocation(xCord-1,yCord)) {
			Piece adjacentPiece = board.getPieceAt(xCord-1, yCord);
			if(adjacentPiece != null && adjacentPiece.getColor() == pieceColor)
				return true;
		}
	}
	if(yCord > 0) {
		if(board.isValidLocation(xCord,yCord-1)) {
			Piece adjacentPiece = board.getPieceAt(xCord, yCord-1);
			if(adjacentPiece != null && adjacentPiece.getColor() == pieceColor)
				return true;
		}
	}
	
	if(xCord < board.getColumnsnumber()) {
		if(board.isValidLocation(xCord+1,yCord)) {
			Piece adjacentPiece = board.getPieceAt(xCord+1, yCord);
			if(adjacentPiece != null && adjacentPiece.getColor() == pieceColor)
				return true;
		}
	}
	
	if(yCord < board.getLinesnumber()) {
		if(board.isValidLocation(xCord,yCord+1)) {
			Piece adjacentPiece = board.getPieceAt(xCord, yCord+1);
			if(adjacentPiece != null && adjacentPiece.getColor() == pieceColor)
				return true;
		}
	}
	return false;
	}
}
