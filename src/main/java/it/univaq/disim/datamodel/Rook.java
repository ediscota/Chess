package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

	@Override
    public String toString() {
        if (getColor() == Color.BIANCO) {
            return "♖"; 
        } else {
            return "♜ "; 
        }
    }
	
	public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {
        List<Move> availableMoves = new ArrayList<>();

        // Mosse verso l'alto
        for (int x = xCord + 1; x < Board.columnsNumber; x++) {
            if (board.isValidLocation(x, yCord)) {
                availableMoves.add(new Move(xCord, yCord, x, yCord));
            } else {
                break;
            }
        }

        // Mosse verso il basso
        for (int x = xCord - 1; x >= 0; x--) {
            if (board.isValidLocation(x, yCord)) {
                availableMoves.add(new Move(xCord, yCord, x, yCord));
            } else {
                break;
            }
        }

        // Mosse verso destra
        for (int y = yCord + 1; y < Board.linesNumber; y++) {
            if (board.isValidLocation(xCord, y) || board.getPieceat(xCord, y).getColor()!=this.getColor()) {
                availableMoves.add(new Move(xCord, yCord, xCord, y));
            } else {
                break;
            }
        }

        // Mosse verso sinistra
        for (int y = yCord - 1; y >= 0; y-- ) {
            if (board.isValidLocation(xCord, y)) {
                availableMoves.add(new Move(xCord, yCord, xCord, y));
            } else {
                break;
            }
        }

        return availableMoves;
    }
}







