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


	@Override
    public List<Move> getAvailableMoves(Board board, int xCord, int yCord) {
        List<Move> availableMoves = new ArrayList<>();
        		
        // Verifica mosse disponibili verso l'alto
        for (int i = xCord - 1; i>= 0; i--) {
            if (!addMoveIfValid(i, yCord, board, availableMoves)) {
                break;
            }
        }

        // Verifica mosse disponibili verso il basso
        for (int i = xCord + 1; i<= 7; i++) {
            if (!addMoveIfValid(i, yCord, board, availableMoves)) {
                break;
            }
        }

        // Verifica mosse disponibili verso sinistra
        for (int i = yCord - 1; i >= 0; i--) {
            if (!addMoveIfValid(xCord, i, board, availableMoves)) {
                break;
            }
        }

        // Verifica mosse disponibili verso destra
        for (int i = yCord + 1; i <= 7; i++) {
            if (!addMoveIfValid(xCord, i, board, availableMoves)) {
                break;
            }
        }

        return availableMoves;
    }
	
	/**
	 * restituisce true o false e aggiunge o meno la mossa alla lista passata per parametro, in base
	 * al pezzo che si trova nella casella di coordinate passate per paramtro
	 * @param xCord
	 * @param yCord
	 * @param board
	 * @param moves
	 * @return
	 */
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
    
    public int getValue() {
		
			return 5;
		}

}

