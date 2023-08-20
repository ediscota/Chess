package it.univaq.disim.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.service.Board;

public class Rook extends Piece implements Serializable {

	private static final long serialVersionUID = 689266078434702663L;
	
	public Rook(Color color, int xCord, int yCord, int value) {
        super(color, xCord, yCord, value);
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
        /*int currentRow = getXCord();
        int currentCol = getYCord();
*/
        // Verifica mosse verso l'alto
        for (int i = xCord - 1; i>= 0; i--) {
            if (!addMoveIfValid(i, yCord, board, availableMoves)) {
                break;
            }
        }

        // Verifica mosse verso il basso
        for (int i = xCord + 1; i<= 7; i++) {
            if (!addMoveIfValid(i, yCord, board, availableMoves)) {
                break;
            }
        }

        // Verifica mosse verso sinistra
        for (int i = yCord - 1; i >= 0; i--) {
            if (!addMoveIfValid(xCord, i, board, availableMoves)) {
                break;
            }
        }

        // Verifica mosse verso destra
        for (int i = yCord + 1; i <= 7; i++) {
            if (!addMoveIfValid(xCord, i, board, availableMoves)) {
                break;
            }
        }

        return availableMoves;
    }

    private boolean addMoveIfValid(int xCord, int yCord, Board board, List<Move> moves) {
        Piece targetPiece = board.getPieceAt(xCord, yCord);

        if (targetPiece == null) {
            moves.add(new Move(getXCord(), getYCord(), xCord, yCord, false));
            return true;
        } else if (targetPiece.getColor() != getColor()) {
            moves.add(new Move(getXCord(), getYCord(), xCord, yCord, true));
            return false;
        } else {
            return false;
        }
    }

}

