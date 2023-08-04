package it.univaq.disim.service;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.datamodel.Bishop;
import it.univaq.disim.datamodel.Color;
import it.univaq.disim.datamodel.King;
import it.univaq.disim.datamodel.Knight;
import it.univaq.disim.datamodel.Move;
import it.univaq.disim.datamodel.Pawn;
import it.univaq.disim.datamodel.Piece;
import it.univaq.disim.datamodel.Queen;
import it.univaq.disim.datamodel.Rook;
import lombok.Data;

@Data
public class Board {
	private static final int columnsNumber = 8;
	private static final int linesNumber = 8;

	private Piece[][] board = new Piece[8][8];

	public static int getLinesnumber() {
		return linesNumber;
	}

	public static int getColumnsnumber() {
		return columnsNumber;
	}

	// prende in input una posizione e controlla se è valida
	public boolean isValidLocation(int x, int y) {

		if (x < 0 || x > getColumnsnumber() || y < 0 || y > getLinesnumber())
			return false;
		else
			return true;

	}

	// prende in input una locazione, prima controlla se è valida tramite
	// isValidLocation, poi restituisce il pezzo che
	// corrisponde a quelle coordinate, oppure se la posizione non è valida lancia
	// una eccezione
	public Piece getPieceat(int x, int y) throws IllegalArgumentException {

		if (isValidLocation(x, y))
			return board[x][y];

		else
			throw new IllegalArgumentException("posizione non valida");

	}

	public void initializeBoard() {
		// Inizializza la scacchiera posizionando i pezzi correttamente
		board[0][0] = new Rook(Color.BIANCO);
		board[0][1] = new Knight(Color.BIANCO);
		board[0][2] = new Bishop(Color.BIANCO);
		board[0][3] = new Queen(Color.BIANCO);
		board[0][4] = new King(Color.BIANCO);
		board[0][5] = new Bishop(Color.BIANCO);
		board[0][6] = new Knight(Color.BIANCO);
		board[0][7] = new Rook(Color.BIANCO);

		for (int i = 0; i < getColumnsnumber(); i++) {
			board[1][i] = new Pawn(Color.BIANCO);
		}

		board[7][0] = new Rook(Color.NERO);
		board[7][1] = new Knight(Color.NERO);
		board[7][2] = new Bishop(Color.NERO);
		board[7][3] = new Queen(Color.NERO);
		board[7][4] = new King(Color.NERO);
		board[7][5] = new Bishop(Color.NERO);
		board[7][6] = new Knight(Color.NERO);
		board[7][7] = new Rook(Color.NERO);

		for (int i = 0; i < getColumnsnumber(); i++) {
			board[6][i] = new Pawn(Color.NERO);
		}

		// Inizializza il resto della scacchiera con pezzi vuoti
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < getColumnsnumber(); j++) {
				board[i][j] = null;
			}
		}
	}

	public void displayBoard() {

		System.out.println("  a b c d e f g h");
		System.out.println("  ----------------");
		for (int i = 0; i < getLinesnumber(); i++) {
			System.out.print((8 - i) + "|");
			for (int j = 0; j < getColumnsnumber(); j++) {
				Piece piece = board[i][j];
				if (piece == null) {
					System.out.print(" ");
				} else {
					System.out.print(piece);
				}
				System.out.print(" ");
			}
			System.out.println("|" + (8 - i));
		}
		System.out.println("  ----------------");
		System.out.println("  a b c d e f g h");
	}

	public List<Piece> getPiecesByColor(Color playerColor) {
		List<Piece> piecesByColor = new ArrayList<>();
		for (int x = 0; x < Board.columnsNumber; x++) {
			for (int y = 0; y < Board.linesNumber; y++) {
				Piece piece = board[x][y];
				if (piece != null && piece.getColor() == playerColor) {
					piecesByColor.add(piece);
				}
			}
		}

		return piecesByColor;
	}

    public void applyMove(Move move) {
		int startX = move.getStartXCord();
        int startY = move.getStartYCord();
        int endX = move.getEndXCord();
        int endY = move.getEndYCord();

    }
}
