package it.univaq.disim.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections4.*;

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
public class Board implements Cloneable {
	private static final int columnsNumber = 8;
	private static final int linesNumber = 8;
	private Move lastMove;

	private Piece[][] board = new Piece[8][8];
	private List<Move> getAcailableMovesByColor;

	public static int getLinesnumber() {
		return linesNumber;
	}

	public static int getColumnsnumber() {
		return columnsNumber;
	}

	// prende in input una posizione e controlla se è valida
	public boolean isValidLocation(int x, int y) {

		if (x < 0 || x >= Board.columnsNumber || y < 0 || y >= Board.linesNumber )
			return false;
		else
			return true;

	}

	// prende in input una locazione, prima controlla se è valida tramite
	// isValidLocation, poi restituisce il pezzo che
	// corrisponde a quelle coordinate, oppure se la posizione non è valida lancia
	// una eccezione
	public Piece getPieceAt(int x, int y) throws IllegalArgumentException {

		if (isValidLocation(x, y))
			return board[x][y];

		else
			throw new IllegalArgumentException("posizione non valida");

	}

	public void initializeBoard() {
		// Inizializza la scacchiera posizionando i pezzi correttamente
		board[0][0] = new Rook(Color.BIANCO, 0, 0);
		board[0][1] = new Knight(Color.BIANCO, 0, 1);
		board[0][2] = new Bishop(Color.BIANCO, 0, 2);
		board[0][3] = new Queen(Color.BIANCO, 0, 3);
		board[0][4] = new King(Color.BIANCO, 0, 4);
		board[0][5] = new Bishop(Color.BIANCO, 0, 5);
		board[0][6] = new Knight(Color.BIANCO, 0, 6);
		board[0][7] = new Rook(Color.BIANCO, 0, 7);
	
		for (int i = 0; i < getColumnsnumber(); i++) {
			board[1][i] = new Pawn(Color.BIANCO, 1, i);
		}
	
		board[7][0] = new Rook(Color.NERO, 7, 0);
		board[7][1] = new Knight(Color.NERO, 7, 1);
		board[7][2] = new Bishop(Color.NERO, 7, 2);
		board[7][3] = new Queen(Color.NERO, 7, 3);
		board[7][4] = new King(Color.NERO, 7, 4);
		board[7][5] = new Bishop(Color.NERO, 7, 5);
		board[7][6] = new Knight(Color.NERO, 7, 6);
		board[7][7] = new Rook(Color.NERO, 7, 7);
	
		for (int i = 0; i < getColumnsnumber(); i++) {
			board[6][i] = new Pawn(Color.NERO, 6, i);
		}
	
		// Inizializza il resto della scacchiera con pezzi vuoti
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < getColumnsnumber(); j++) {
				board[i][j] = null;
			}
		}
	}

	public void displayBoard() { //TODO Display e try catch eccezione

		System.out.println("    0    1   2    3    4    5    6    7");
		System.out.println("  --------------------------------------  ");
		for (int i = 0; i < getLinesnumber(); i++) {
			System.out.print((0 + i) + "|");
			for (int j = 0; j < getColumnsnumber(); j++) {
				Piece piece = board[i][j];
				if (piece == null) {
					System.out.print("    ");
				} else {
					if( i > 0 && i < 7) {
						System.out.print(piece + "" + j);
					}else {
						if(j > 4) {
							System.out.print(piece + "2");
						}
						else {
							System.out.print(piece + "1");
						}
						
					}
				}
				System.out.print(" ");
			}
			System.out.println("|" + (0 + i));
		}
		System.out.println("  --------------------------------------  ");
		System.out.println("    0    1   2    3    4    5    6    7");
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
		Piece pieceToMove = board[startX][startY];
		Piece destinationPiece = board[endX][endY];
		board[startX][startY] = null; //rimuovi dalla posizione di partenza il pezzo
		board[endX][endY] = pieceToMove;
		pieceToMove.setXCord(endX);
		pieceToMove.setYCord(endY);
		// Se la mossa è una mossa di cattura, rimuovi il pezzo catturato dalla
		// scacchiera
		if (destinationPiece != null) {
			destinationPiece.setXCord(-1); // Rimuovi il pezzo dalla scacchiera impostando le coordinate a -1
			destinationPiece.setYCord(-1);
			// TODO destinationPiece.setValue=0; per la conta dei pezzi, value attualmente
			// non presente
		}
        lastMove = move;
	}
	
	public void undoLastMove(Move move) {
		Piece pieceToMove = board[move.getStartXCord()][move.getStartYCord()];
		board[move.getStartXCord()][move.getStartYCord()] = null;
		board[move.getEndXCord()][move.getEndYCord()] = pieceToMove;
	}
	
	public Move getLastMove() {
        return lastMove;
    }

	public List<Move> getAvailableMovesByColor(Color color) {
		List<Move> availableMoves = new ArrayList<>();

		for (int x = 0; x < columnsNumber; x++) {
			for (int y = 0; y < linesNumber; y++) {
				Piece piece = board[x][y];
				if (piece != null && piece.getColor() == color) {
					List<Move> pieceMoves = piece.getAvailableMoves(this, x, y);
					availableMoves.addAll(pieceMoves);
				}
			}
		}

		return availableMoves;
	}

	public boolean isKingInCheck(Color color, Board board) {
		// inizializzazione delle coordinate del re al di fuori della scacchiera
		int kingX = -1;
		int kingY = -1;
		boolean kingFound = false;
		boolean isInCheck = false;
		// doppio ciclo per trovare le coordinate del re
		for (int x = 0; x < Board.columnsNumber && !kingFound; x++) {
			for (int y = 0; y < Board.linesNumber && !kingFound; y++) {
				Piece piece = board.getPieceAt(x, y);
				if (piece instanceof King && piece.getColor() == color) {
					kingX = x;
					kingY = y;
					kingFound = true;
				}
			}
		}
		// prima si controlla se il re è nella scacchiera, in caso positivo si controlla
		// se è minacciato da mosse avversarie
		if (!kingFound)
			return false;
		List<Move> opponentMoves = board.getAvailableMovesByColor(color.oppositeColor());
		for (Move move : opponentMoves) {
			if (move.getEndXCord() == kingX && move.getEndYCord() == kingY) {
				isInCheck = true;
				break;
			}

		}
		return isInCheck;
	}
	//scacco matto
    public boolean isCheckMate (Color color, Board board) {
		//se il re non è in scacco non può esserci scacco matto
		if (!isKingInCheck(color, board)) {
			return false;
		}
		//ottieni tutte le mosse dei pezzi dello stesso colore del re
		List<Move> alliesMoves = getAvailableMovesByColor(color);
		for (Move move : alliesMoves) {
			Board cloneBoard = this.clone();
			cloneBoard.applyMove(move);
			if (!cloneBoard.isKingInCheck(color, cloneBoard)) {
				// Il re può muoversi in una casella sicura, quindi non c'è scacco matto
				return false;
			}
		}
		return true;
	}
	@Override
	// TODO non penso sia necessario il try-catch
	public Board clone() {
        try {
            Board cloneBoard = (Board) super.clone();

            // Clona l'array bidimensionale di Piece
            cloneBoard.board = new Piece[columnsNumber][linesNumber];
            for (int i = 0; i < columnsNumber; i++) {
                for (int j = 0; j < linesNumber; j++) {
                    if (this.board[i][j] != null) {
                        cloneBoard.board[i][j] = this.board[i][j].clone();
                    }
                }
            }

            // Ora gli oggetti Piece all'interno dell'array sono stati clonati

            return cloneBoard;
        } catch (CloneNotSupportedException e) {
            // Gestisci l'eccezione se la classe non supporta la clonazione
            e.printStackTrace();
            return null;

        }
    }
}

