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

//TODO overloading di playGame. A dire il vero non sono così sicuro ci sia bisogno di fare overloading
  //penso sia necessario solo per le scritte a schermo a ogni turno
    public void playGame (Board board, HumanPlayer whitePlayer, CPUPlayer blackPlayer){
        //player1= new HumanPlayer(Color.BIANCO);
        // non penso ci sia bisogno, le istanze di HumanPlayer vengono create nel
        //metodo startNewGame, e poi vengono passate a questo metodo
        boolean isGameOver = false;
        Player currentPlayer = whitePlayer;
    }
    public void playGame (Board board, HumanPlayer whitePlayer, HumanPlayer blackPlayer){

    }
}
