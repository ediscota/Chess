package it.univaq.disim.service;

import java.util.List;

import it.univaq.disim.datamodel.Color;
import it.univaq.disim.datamodel.Move;
import org.apache.commons.collections4.CollectionUtils;

public class Game {
    private Player whitePlayer;
    private Player BlackPlayer;
    private Board board;
    private List<Move> whiteMoves;
    private List<Move> blackMoves;
   
   /* 
    public Game(Player whitePlayer, Player blackPlayer, Board board) {
        this.whitePlayer = whitePlayer;
        this.BlackPlayer = BlackPlayer;
        this.board = board;
    }*/

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
        Player currentPlayer = whitePlayer;
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
                currentPlayer.makeMove(board);
                if (currentPlayer.getColor() == Color.BIANCO) {
                    whiteMoves.add(board.getLastMove());  // Aggiungi mossa bianca
                } else {
                    blackMoves.add(board.getLastMove());  // Aggiungi mossa nera
                }

                currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer; // cambia turno
            } catch (IllegalArgumentException e) {
                System.out.println("Mossa non valida. Riprova.");
            }
        }
        
        if (winner != null) {
            System.out.println("Il giocatore " + winner.getColor().toString() + " ha vinto.");

        }
    }
  /*  public void undoMoves (int number) throws IllegalArgumentException {
    int totalMoves = whiteMoves.size() + blackMoves.size();
    if (number > totalMoves) throw new IllegalArgumentException("Non ci sono abbastanza mosse da cancellare");
    for (int i = 0; i < number; i++) {
        if (!blackMoves.isEmpty() && !whiteMoves.isEmpty() ) {
            blackMoves.remove(blackMoves.getlast);
            whiteMoves.remove(whiteMoves.getLast);
            board.applyMove(blackMoves.getlast);
        } ;
        }
    }
    

    }*/

   }

