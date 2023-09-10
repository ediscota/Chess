package it.univaq.disim.service;

import java.util.LinkedList;

import it.univaq.disim.datamodel.Color;

import it.univaq.disim.datamodel.Move;
import it.univaq.disim.datamodel.Pawn;
import it.univaq.disim.datamodel.Piece;

import java.io.Serializable;

public class Game implements Serializable {

	private static final long serialVersionUID = -3457753419113559690L;
	private Player whitePlayer;
	private Player blackPlayer;
	private Board board;
	private LinkedList<Move> whiteMoves = new LinkedList<>();
	private LinkedList<Move> blackMoves = new LinkedList<>();
	private Player currentPlayer;
	private LinkedList<Piece> deadWhitePieces = new LinkedList<>();
	private LinkedList<Piece> deadBlackPieces = new LinkedList<>();
	private int drawMovesCounter;
	boolean isGameOver;

	public LinkedList<Move> getBlackMoves() {
		return this.blackMoves;
	}

	public LinkedList<Move> getWhiteMoves() {
		return this.whiteMoves;
	}

	public LinkedList<Piece> getDeadWhitePieces() {
		return this.deadWhitePieces;
	}

	public LinkedList<Piece> getDeadBlackPieces() {
		return this.deadBlackPieces;
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * Crea istanze HumanPlayer e CPUPlayer, crea istanza Board e la inizializza, e
	 * con questi parametri chiama il metodo playGame
	 */
	public void startNewCPUGame() {
		Player whitePlayer = new HumanPlayer(Color.BIANCO);
		Player blackPlayer = new CPUPlayer(Color.NERO);
		Board board = new Board();
		board.initializeBoard();
		playGame(board, whitePlayer, blackPlayer);
	}

	/**
	 * Crea due istanze HumanPlayer, crea istanza Board e la inizializza, e con
	 * questi parametri chiama il metodo playGame
	 */
	public void startNewHumanGame() {
		Player whitePlayer = new HumanPlayer(Color.BIANCO);
		Player blackPlayer = new HumanPlayer(Color.NERO);
		Board board = new Board();
		board.initializeBoard();
		playGame(board, whitePlayer, blackPlayer);
	}

	/**
	 * Metodo che gestisce la logica del gioco degli scacchi
	 */
	public void playGame(Board board, Player whitePlayer, Player blackPlayer) {
		isGameOver = false;
		currentPlayer = whitePlayer;
		Player winner = null;
		drawMovesCounter = 0;

		while (!isGameOver) {
			board.displayBoard();
			// Controlla se nel turno precedente l'avversario non sia riuscito a salvare il
			// proprio
			// Re
			if (board.isKingInCheck(currentPlayer.getColor().oppositeColor(), board)) {
				System.out.println("Giocatore " + currentPlayer.getColor().oppositeColor().toString()
						+ " non sei riuscito a difendere il tuo re");
				winner = (currentPlayer == whitePlayer) ? whitePlayer : blackPlayer;
				isGameOver = true;
			}
			if (board.isKingInCheck(currentPlayer.getColor(), board)) {
				System.out.println(
						"Giocatore " + currentPlayer.getColor().toString() + " il tuo re è sotto scacco, attenzione!");
			}
			if (board.isCheckMate(currentPlayer.getColor(), board)) {
				System.out.println("Scacco matto! partita terminata.");
				winner = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
				isGameOver = true;
			}
			if (!isGameOver) {
				System.out.println("Giocatore " + currentPlayer.getColor().toString() + ", è il tuo turno.");

				try {
					currentPlayer.makeMove(board, this);
					currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer; // cambia turno
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
			if (this.drawMovesCounter >= 50) {
				isGameOver = true;
				System.out.println("partita finita in patta per la regola delle 50 mosse");
			}
		}

		if (winner != null) {
			System.out.println("Il giocatore " + winner.getColor().toString() + " ha vinto.");

		}
	}

	/**
	 * prende come parametro la board e il numero N di mosse da annullare, e annulla
	 * le ultime N mosse effettuate
	 * 
	 * @param board
	 * @param numberMoves
	 */
	public void undoMoves(Board board, int numberMoves) {
		for (int i = 0; i < numberMoves; i++) {
			Move lastMoveWhite, lastMoveBlack, undoMoveBlack = new Move(), undoMoveWhite = new Move();
			Piece lastDeadPiece;
			if (!blackMoves.isEmpty() && !whiteMoves.isEmpty()) {
				lastMoveWhite = whiteMoves.remove(whiteMoves.size() - 1);
				lastMoveBlack = blackMoves.remove(blackMoves.size() - 1);

				/*
				 * vengono settatate le coordinate della mossa da annullare, al contrario
				 * dell'ultima mossa effettuata
				 */
				undoMoveWhite.setStartXCord(lastMoveWhite.getEndXCord());
				undoMoveWhite.setStartYCord(lastMoveWhite.getEndYCord());
				undoMoveWhite.setEndXCord(lastMoveWhite.getStartXCord());
				undoMoveWhite.setEndYCord(lastMoveWhite.getStartYCord());
				undoMoveBlack.setStartXCord(lastMoveBlack.getEndXCord());
				undoMoveBlack.setStartYCord(lastMoveBlack.getEndYCord());
				undoMoveBlack.setEndXCord(lastMoveBlack.getStartXCord());
				undoMoveBlack.setEndYCord(lastMoveBlack.getStartYCord());
				if (currentPlayer.getColor() == Color.BIANCO) {
					board.applyMove(undoMoveBlack);
					if (lastMoveBlack.isCapture()) {
						lastDeadPiece = deadWhitePieces.remove(deadWhitePieces.size() - 1);
						board.getBoard()[lastMoveBlack.getEndXCord()][lastMoveBlack.getEndYCord()] = lastDeadPiece;
						lastDeadPiece.setXCord(lastMoveBlack.getEndXCord());
						lastDeadPiece.setYCord(lastMoveBlack.getEndYCord());
						lastDeadPiece.setValue(lastDeadPiece.getValueFromInstanceOf());
					}

					board.applyMove(undoMoveWhite);
					if (lastMoveWhite.isCapture()) {
						lastDeadPiece = deadBlackPieces.remove(deadBlackPieces.size() - 1);
						board.getBoard()[lastMoveWhite.getEndXCord()][lastMoveWhite.getEndYCord()] = lastDeadPiece;
						lastDeadPiece.setXCord(lastMoveWhite.getEndXCord());
						lastDeadPiece.setYCord(lastMoveWhite.getEndYCord());
						lastDeadPiece.setValue(lastDeadPiece.getValueFromInstanceOf());
					}
				} else {
					board.applyMove(undoMoveWhite);
					if (lastMoveWhite.isCapture()) {
						lastDeadPiece = deadBlackPieces.remove(deadBlackPieces.size() - 1);
						board.getBoard()[lastMoveWhite.getEndXCord()][lastMoveWhite.getEndYCord()] = lastDeadPiece;
						lastDeadPiece.setXCord(lastMoveWhite.getEndXCord());
						lastDeadPiece.setYCord(lastMoveWhite.getEndYCord());
						lastDeadPiece.setValue(lastDeadPiece.getValueFromInstanceOf());
					}

					board.applyMove(undoMoveBlack);
					if (lastMoveBlack.isCapture()) {
						lastDeadPiece = deadWhitePieces.remove(deadWhitePieces.size() - 1);
						board.getBoard()[lastMoveBlack.getEndXCord()][lastMoveBlack.getEndYCord()] = lastDeadPiece;
						lastDeadPiece.setXCord(lastMoveBlack.getEndXCord());
						lastDeadPiece.setYCord(lastMoveBlack.getEndYCord());
						lastDeadPiece.setValue(lastDeadPiece.getValueFromInstanceOf());
					}
				}

			}

		}

		currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
	}

	/**
	 * resetta il count delle mosse per il pareggio
	 */
	public void resetDrawMovesCounter() {
		this.drawMovesCounter = 0;
	}

	/**
	 * aggiorna il count delle mosse per il pareggio
	 */
	public void addDrawMovesCounter() {
		this.drawMovesCounter++;
	}

	/**
	 * restituisce true se la mossa 'move' è di cattura e se il pezzo che si trova
	 * nella casella che ha coordinate uguali alle coordinate iniziali di 'move', è
	 * un pedone
	 * 
	 * @param move
	 * @param board
	 * @return true o false
	 */
	public boolean isCapturedOrPawnMove(Move move, Board board) {
		if (move.isCapture() || board.getPieceAt(move.getStartXCord(), move.getStartYCord()) instanceof Pawn)
			return true;
		return false;
	}

	/**
	 * termina la partita con l'arresa del giocatore corrente
	 */
	public void surrender() {
		isGameOver = true;
		System.out.print("Giocatore " + this.getCurrentPlayer().getColor() + " ti sei arreso, " + "Giocatore "
				+ this.getCurrentPlayer().getColor().oppositeColor() + " hai vinto!");
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public int valuePieces(Game game, int x) {

		int c = 48;

		for (Piece elemento : game.getDeadBlackPieces()) {

			switch (elemento.getValue()) {

			case 1:

				c = c - 1;
				break;

			case 3:

				c = c - 3;
				break;

			case 5:

				c = c - 5;
				break;

			case 9:

				c = c - 9;
				break;

			}

		}

		return c;

	}
}
