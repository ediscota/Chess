package it.univaq.disim.service;

import it.univaq.disim.datamodel.Color;

public class Game {
    private Player whitePlayer;
    private Player BlackPlayer;
    private Board board;
   
    public Game(Player whitePlayer, Player blackPlayer, Board board) {
        this.whitePlayer = whitePlayer;
        this.BlackPlayer = BlackPlayer;
        this.board = board;
    }

    public void startNewCPUGame (){
        Player whitePlayer = new HumanPlayer(Color.BIANCO);
        Player blackPlayer = new CPUPlayer(Color.NERO);
        Board board = new Board();
        board.initializeBoard();
        playGame(board, whitePlayer, blackPlayer);
    }
    


    public void playGame (Board board, Player whitePlayer, Player blackPlayer){
        //whitePlayer= new HumanPlayer(Color.BIANCO);
        // non penso ci sia bisogno, le istanze di HumanPlayer vengono create nel
        //metodo startNewGame, e poi vengono passate a questo metodo
        boolean isGameOver = false;
        Player currentPlayer = whitePlayer;
        Player winner= null;

        while (!isGameOver){
            board.displayBoard();
            if (board.isKingInCheck(currentPlayer.getColor(), board)){
                System.out.println("Giocatore " + currentPlayer.getColor().toString() + "il tuo re è sotto scacco, attenzione!");
            }
            if (board.isCheckMate(currentPlayer.getColor(), board)){
                System.out.println("Scacco matto! partita terminata.");
                winner = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
                isGameOver = true;
            }
            System.out.println ("Giocatore " + currentPlayer.getColor().toString() + "è il tuo turno.");
            currentPlayer.makeMove(board);
            //cambia turno
            currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;

        }
        System.out.println("Il giocatore " + winner.getColor().toString() + "ha vinto.");
    }
   
}
