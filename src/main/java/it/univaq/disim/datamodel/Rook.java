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
//@Override
	//public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {return null;}
		/*List<Move> availableMoves = new ArrayList<>();
	

		// Mosse verso l'alto
		for (int x = xCord + 1; x < board.getColumnsnumber(); x++) {
			//if (board.isValidLocation(x, yCord) || board.getPieceAt(x-1, yCord).getColor() != this.getColor()) {
				if(!(this.hasAdjacentPieceOfSameColor(x-1, yCord, this.getColor(), board)))
					availableMoves.add(new Move(xCord, yCord, x, yCord));
				else break;
			//}
			//else break;
		}

		// Mosse verso il basso
		for (int x = xCord - 1; x >= 0; x--) {
			//if (board.isValidLocation(x, yCord) || board.getPieceAt(x+1, yCord).getColor() != this.getColor()) {
				if(!(this.hasAdjacentPieceOfSameColor(x+1, yCord, this.getColor(), board)))	
					availableMoves.add(new Move(xCord, yCord, x, yCord));
				else break;
			//}
			//else break;
		}

		// Mosse verso destra
		for (int y = yCord + 1; y < Board.getLinesnumber(); y++) {
			//if (board.isValidLocation(xCord, y) || board.getPieceAt(xCord, y-1).getColor() != this.getColor()) {
				if(!(this.hasAdjacentPieceOfSameColor(xCord, y-1, this.getColor(), board)))
					availableMoves.add(new Move(xCord, yCord, xCord, y));
				else break;
			//}
			//else break;
		}

		// Mosse verso sinistra
		for (int y = yCord - 1; y >= 0; y--) {
			//if (board.isValidLocation(xCord, y) || board.getPieceAt(xCord, y+1).getColor() != this.getColor()) {
				if(!(this.hasAdjacentPieceOfSameColor(xCord, y+1, this.getColor(), board)))
					availableMoves.add(new Move(xCord, yCord, xCord, y));
				else break;
			//}
			//else break;
		}

		return availableMoves;
	}*/


    @Override
    public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {
        List<Move> availableMoves = new ArrayList<>();
        int currentRow = getXCord();
        int currentCol = getYCord();

        // Verifica mosse verso l'alto
        for (int row = currentRow - 1; row >= 0; row--) {
            if (!addMoveIfValid(row, currentCol, board, availableMoves)) {
                break;
            }
        }

        // Verifica mosse verso il basso
        for (int row = currentRow + 1; row <= 7; row++) {
            if (!addMoveIfValid(row, currentCol, board, availableMoves)) {
                break;
            }
        }

        // Verifica mosse verso sinistra
        for (int col = currentCol - 1; col >= 0; col--) {
            if (!addMoveIfValid(currentRow, col, board, availableMoves)) {
                break;
            }
        }

        // Verifica mosse verso destra
        for (int col = currentCol + 1; col <= 7; col++) {
            if (!addMoveIfValid(currentRow, col, board, availableMoves)) {
                break;
            }
        }

        return availableMoves;
    }

    private boolean addMoveIfValid(int row, int col, Board board, List<Move> moves) {
        Piece targetPiece = board.getPieceAt(row, col);

        if (targetPiece == null) {
            moves.add(new Move(getXCord(), getYCord(), row, col));
            return true;
        } else if (targetPiece.getColor() != getColor()) {
            moves.add(new Move(getXCord(), getYCord(), row, col));
            return false;
        } else {
            return false;
        }
    }

}
