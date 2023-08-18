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
    protected LinkedList<Move> whiteMoves = new LinkedList<>();
    protected LinkedList<Move> blackMoves = new LinkedList<>();
    private Player currentPlayer;
    private LinkedList<Piece> deadPieces;
    private int movesCount = 0;

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
        boolean isGameOver = false;
        currentPlayer = whitePlayer;
        Player winner = null;

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
                    System.out.println("Mossa non valida. Riprova.");
                }
            }
        }

        if (winner != null) {
            System.out.println("Il giocatore " + winner.getColor().toString() + " ha vinto.");

        }
    }

    public void undoMoves(Board board) {
        this.movesCount++;
        Move lastMoveWhite, lastMoveBlack, undoMoveBlack = new Move(), undoMoveWhite = new Move();
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
            board.undoLastMove(undoMoveBlack);
            board.undoLastMove(undoMoveWhite);
            currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
        }
    }

    public int getMovesCount() {
        return this.movesCount;
    }
    
    
}
