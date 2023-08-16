package it.univaq.disim.service;

import java.util.List;
import java.util.LinkedList;
import it.univaq.disim.datamodel.Color;
import it.univaq.disim.datamodel.Move;
import it.univaq.disim.datamodel.Piece;

import org.apache.commons.collections4.*;

public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private Board board;
    private LinkedList<Move> whiteMoves = new LinkedList<>();
    private LinkedList<Move> blackMoves = new LinkedList<>();
    private Player currentPlayer;
    private LinkedList<Piece> deadPieces;
    private int movesCount = 0;
   /* 
    public Game(Player whitePlayer, Player blackPlayer, Board board) {
        this.whitePlayer = whitePlayer;
        this.BlackPlayer = BlackPlayer;
        this.board = board;
    }*/
    public LinkedList<Move> getBlackMoves(){
    	return this.blackMoves;
    }
    public LinkedList<Move> getWhiteMoves(){
    	return this.whiteMoves;
    }
    public void startNewCPUGame (){
        Player whitePlayer = new HumanPlayer(Color.BIANCO);
        Player blackPlayer = new CPUPlayer(Color.NERO);
        Board board = new Board();
        board.initializeBoard();
        playGame(board, whitePlayer, blackPlayer);
    }

    public void startNewHumanGame(){
         Player whitePlayer = new HumanPlayer(Color.BIANCO);
         Player blackPlayer = new HumanPlayer(Color.NERO);
         Board board = new Board();
         board.initializeBoard();
         playGame(board, whitePlayer, blackPlayer);
    }
    


    public void playGame (Board board, Player whitePlayer, Player blackPlayer){
        boolean isGameOver = false; 
        currentPlayer = whitePlayer;
        Player winner= null;

        while (!isGameOver){
            board.displayBoard();
            if (board.isKingInCheck(currentPlayer.getColor(), board)){
                System.out.println("Giocatore " + currentPlayer.getColor().toString() + " il tuo re è sotto scacco, attenzione!");
            }
            if (board.isCheckMate(currentPlayer.getColor(), board)){
                System.out.println("Scacco matto! partita terminata.");
                winner = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
                isGameOver = true;
            }
            System.out.println("Giocatore " + currentPlayer.getColor().toString() + ", è il tuo turno.");
            
            try {
                currentPlayer.makeMove(board, this);
                if (currentPlayer.getColor() == Color.BIANCO) {
                    whiteMoves.add(board.getLastMove());  // Aggiungi mossa bianca
                } else {
                    blackMoves.add(board.getLastMove());  // Aggiungi mossa nera
                }
                //currentPlayer.makeMove(board, this);
                currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer; // cambia turno
            } catch (IllegalArgumentException e) {
                System.out.println("Mossa non valida. Riprova.");
            }
        }
        
        if (winner != null) {
            System.out.println("Il giocatore " + winner.getColor().toString() + " ha vinto.");

        }
    }
    
    
    public void undoMoves (Board board) throws IllegalArgumentException {
    	this.movesCount++;
	    //int totalMoves = whiteMoves.size() + blackMoves.size();
	    //if (number > totalMoves) throw new IllegalArgumentException("Non ci sono abbastanza mosse da cancellare");
	    //for (int i = 0; i < number; i++) {
    	Move lastMove, undoMove = new Move();
	        if (!blackMoves.isEmpty() && !whiteMoves.isEmpty() ) {
	        	if(this.currentPlayer == whitePlayer) {
	        		lastMove = whiteMoves.getLast();
	        		undoMove.setStartXCord(lastMove.getEndXCord());
	        		undoMove.setStartYCord(lastMove.getEndYCord());
	        		undoMove.setEndXCord(lastMove.getStartXCord());
	        		undoMove.setEndYCord(lastMove.getStartYCord());
	        		board.applyMove(undoMove);
	            	whiteMoves.remove(whiteMoves.getLast());           	
	        	}
	        	else {
	        		lastMove = blackMoves.getLast();
	        		undoMove.setStartXCord(lastMove.getEndXCord());
	        		undoMove.setStartYCord(lastMove.getEndYCord());
	        		undoMove.setEndXCord(lastMove.getStartXCord());
	        		undoMove.setEndYCord(lastMove.getStartYCord());
	        		board.applyMove(undoMove);
	        		blackMoves.remove(blackMoves.getLast());
	        	}
	        } 
	    //}
	    //currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }
	public int getMovesCount() {
		return this.movesCount;
	}

   }

