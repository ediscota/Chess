package it.univaq.disim.datamodel;

import lombok.Data;

@Data
public class Board {
	static final int columnsNumber = 8;
	static final int linesNumber = 8;

	private Piece[][] board = new Piece[8][8];

	// prende in input una posizione e controlla se è valida
	public boolean isValidLocation(int x, int y) {

		if (x < 0 || x > columnsNumber || y < 0 || y > linesNumber)
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

		for (int i = 0; i < columnsNumber; i++) {
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

		for (int i = 0; i < columnsNumber; i++) {
			board[6][i] = new Pawn(Color.NERO);
		}

		// Inizializza il resto della scacchiera con pezzi vuoti
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < columnsNumber; j++) {
				board[i][j] = null;
			}
		}
	}

	public void displayBoard() {

		System.out.println("  a b c d e f g h");
		System.out.println("  ----------------");
		for (int i = 0; i < linesNumber; i++) {
			System.out.print((8 - i) + "|");
			for (int j = 0; j < columnsNumber; j++) {
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

}
