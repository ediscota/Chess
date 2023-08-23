package it.univaq.disim.service;

import java.util.List;
import java.util.LinkedList;

import it.univaq.disim.datamodel.Bishop;
import it.univaq.disim.datamodel.Color;
import it.univaq.disim.datamodel.Knight;
import it.univaq.disim.datamodel.Move;
import it.univaq.disim.datamodel.Pawn;
import it.univaq.disim.datamodel.Piece;
import it.univaq.disim.datamodel.Queen;
import it.univaq.disim.datamodel.Rook;

import java.io.Serializable;

import org.apache.commons.collections4.*;

public class Game implements Serializable{
	
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
    /*
     * public Game(Player whitePlayer, Player blackPlayer, Board board) {
     * this.whitePlayer = whitePlayer;
     * this.BlackPlayer = BlackPlayer;
     * this.board = board;
     * }
     */
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

    public void startNewCPUGame() {
        Player whitePlayer = new HumanPlayer(Color.BIANCO);
        Player blackPlayer = new CPUPlayer(Color.NERO);
        Board board = new Board();
        board.initializeBoard();
        playGame(board, whitePlayer, blackPlayer);
    }

    public void startNewHumanGame() {
        Player whitePlayer = new HumanPlayer(Color.BIANCO);
        Player blackPlayer = new HumanPlayer(Color.NERO);
        Board board = new Board();
        board.initializeBoard();
        playGame(board, whitePlayer, blackPlayer);
    }

    public void playGame(Board board, Player whitePlayer, Player blackPlayer) {
        isGameOver = false;
        currentPlayer = whitePlayer;
        Player winner = null;
        drawMovesCounter = 0;

        while (!isGameOver) {
            board.displayBoard();
            // Controlla se nel turno precedente l'avversario non abbia salvato il proprio
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
            if(this.drawMovesCounter >= 50) {
            	isGameOver = true;
            	System.out.println("partita finita in patta per la regola delle 50 mosse");
            }
        }
        
        if (winner != null) {
            System.out.println("Il giocatore " + winner.getColor().toString() + " ha vinto.");

        }
    }

    public void undoMoves(Board board, int numberMoves) {
        for (int i = 0; i < numberMoves; i++) {
	        Move lastMoveWhite, lastMoveBlack, undoMoveBlack = new Move(), undoMoveWhite = new Move();
	        Piece lastDeadPiece;
	        if (!blackMoves.isEmpty() && !whiteMoves.isEmpty()) {
	            lastMoveWhite = whiteMoves.remove(whiteMoves.size() - 1);
	            lastMoveBlack = blackMoves.remove(blackMoves.size() - 1);
	            undoMoveWhite.setStartXCord(lastMoveWhite.getEndXCord());
	            undoMoveWhite.setStartYCord(lastMoveWhite.getEndYCord());
	            undoMoveWhite.setEndXCord(lastMoveWhite.getStartXCord());
	            undoMoveWhite.setEndYCord(lastMoveWhite.getStartYCord());
	            undoMoveBlack.setStartXCord(lastMoveBlack.getEndXCord());
	            undoMoveBlack.setStartYCord(lastMoveBlack.getEndYCord());
	            undoMoveBlack.setEndXCord(lastMoveBlack.getStartXCord());
	            undoMoveBlack.setEndYCord(lastMoveBlack.getStartYCord());
	            if(currentPlayer.getColor() == Color.BIANCO) {
		            board.undoLastMove(undoMoveBlack);
		            if(lastMoveBlack.isCapture()) {
		            	lastDeadPiece = deadWhitePieces.remove(deadWhitePieces.size()-1);
		            	board.getBoard()[lastMoveBlack.getEndXCord()][lastMoveBlack.getEndYCord()] = lastDeadPiece;
		            	lastDeadPiece.setXCord(lastMoveBlack.getEndXCord());
		            	lastDeadPiece.setYCord(lastMoveBlack.getEndYCord());
		            	if(lastDeadPiece instanceof Pawn)
		            		lastDeadPiece.setValue(1);
		            	else if(lastDeadPiece instanceof Rook)
		            		lastDeadPiece.setValue(5);
		            	else if(lastDeadPiece instanceof Knight || lastDeadPiece instanceof Bishop)
		            		lastDeadPiece.setValue(3);
		            	else if(lastDeadPiece instanceof Queen)
		            		lastDeadPiece.setValue(9);
			        }
		            
		            
		            board.undoLastMove(undoMoveWhite);		            
		            if(lastMoveWhite.isCapture()) {
		            	lastDeadPiece = deadBlackPieces.remove(deadBlackPieces.size()-1);
		            	board.getBoard()[lastMoveWhite.getEndXCord()][lastMoveWhite.getEndYCord()] = lastDeadPiece;
		            	lastDeadPiece.setXCord(lastMoveWhite.getEndXCord());
		            	lastDeadPiece.setYCord(lastMoveWhite.getEndYCord());
		            	if(lastDeadPiece instanceof Pawn)
		            		lastDeadPiece.setValue(1);
		            	else if(lastDeadPiece instanceof Rook)
		            		lastDeadPiece.setValue(5);
		            	else if(lastDeadPiece instanceof Knight || lastDeadPiece instanceof Bishop)
		            		lastDeadPiece.setValue(3);
		            	else if(lastDeadPiece instanceof Queen)
		            		lastDeadPiece.setValue(9);
			        }
	            }
	            else {
	            	board.undoLastMove(undoMoveWhite);
	            	if(lastMoveWhite.isCapture()) {
		            	lastDeadPiece = deadBlackPieces.remove(deadBlackPieces.size()-1);
		            	board.getBoard()[lastMoveWhite.getEndXCord()][lastMoveWhite.getEndYCord()] = lastDeadPiece;
		            	lastDeadPiece.setXCord(lastMoveWhite.getEndXCord());
		            	lastDeadPiece.setYCord(lastMoveWhite.getEndYCord());
		            	if(lastDeadPiece instanceof Pawn)
		            		lastDeadPiece.setValue(1);
		            	else if(lastDeadPiece instanceof Rook)
		            		lastDeadPiece.setValue(5);
		            	else if(lastDeadPiece instanceof Knight || lastDeadPiece instanceof Bishop)
		            		lastDeadPiece.setValue(3);
		            	else if(lastDeadPiece instanceof Queen)
		            		lastDeadPiece.setValue(9);
			        }
	            	
	            	board.undoLastMove(undoMoveBlack);
	            	if(lastMoveBlack.isCapture()) {
		            	lastDeadPiece = deadWhitePieces.remove(deadWhitePieces.size()-1);
		            	board.getBoard()[lastMoveBlack.getEndXCord()][lastMoveBlack.getEndYCord()] = lastDeadPiece;
		            	lastDeadPiece.setXCord(lastMoveBlack.getEndXCord());
		            	lastDeadPiece.setYCord(lastMoveBlack.getEndYCord());
		            	if(lastDeadPiece instanceof Pawn)
		            		lastDeadPiece.setValue(1);
		            	else if(lastDeadPiece instanceof Rook)
		            		lastDeadPiece.setValue(5);
		            	else if(lastDeadPiece instanceof Knight || lastDeadPiece instanceof Bishop)
		            		lastDeadPiece.setValue(3);
		            	else if(lastDeadPiece instanceof Queen)
		            		lastDeadPiece.setValue(9);
			        }
	            }
	            
	        }
	        
	        	
	        	
        }
        currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }
    
    public void resetDrawMovesCounter() {
    	this.drawMovesCounter = 0;
    }
    
    public void addDrawMovesCounter() {
    	this.drawMovesCounter++;
    }
    public boolean isCapturedOrPawnMove(Move move, Board board) {
    	if(move.isCapture() || board.getPieceAt(move.getStartXCord(), move.getStartYCord()) instanceof Pawn)
    		return true;
    	return false;
    }
    
    public void surrender() {
    	isGameOver = true;
    	System.out.print("Giocatore " + this.getCurrentPlayer().getColor() + " ti sei arreso, "
    			+ "Giocatore " + this.getCurrentPlayer().getColor().oppositeColor() + " hai vinto!");
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
    
	public int valuePieces(Game game,int x) {
    	
    	int c = 48;
    	
    	for (Piece elemento : game.getDeadBlackPieces()) {
	            
	            switch(elemento.getValue()) {
	            
	            case 1 :
	            	
	            	c = c - 1;
	            	break;
	            	
	            case 3 :
	            	
	            	c = c - 3;
	            	break;
	            	
	            case 5 :
	            	
	            	c = c - 5;
	            	break;
	            	
	            case 9 :
	            	
	            	c = c - 9;
	            	break;
	            
	            }
	            
	        }
    	
    	return c;
    		
    }
}
