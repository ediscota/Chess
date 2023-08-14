package it.univaq.disim.service;

import java.util.List;
import java.util.Scanner;

import it.univaq.disim.datamodel.Color;
import it.univaq.disim.datamodel.Move;
import it.univaq.disim.datamodel.Piece;

public class HumanPlayer extends Player {

    public HumanPlayer(Color color) {
        super(color);

    }

    @Override
    public void makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Richiedi all'utente di selezionare un pezzo
        System.out.print("Seleziona il pezzo da muovere (riga colonna): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
    
        Piece selectedPiece = board.getPieceAt(x, y);;
    
        if (selectedPiece == null || selectedPiece.getColor() != this.getColor()) {
            throw new IllegalArgumentException("La casella selezionata non contiene un pezzo valido.");
        }
    
        // Step 2: Ottieni le mosse disponibili per il pezzo selezionato
        List<Move> availableMoves = selectedPiece.getAvailableMoves(board, x, y);
    
        // Step 3: Chiedi all'utente di selezionare una mossa dalla lista
        System.out.println("Mosse disponibili:");
        for (int i = 0; i < availableMoves.size(); i++) {
            System.out.println(i + ": " + availableMoves.get(i).toString());
        }
        System.out.print("Seleziona la mossa da eseguire (index): ");
        int selectedMoveIndex = scanner.nextInt();
    
        if (selectedMoveIndex < 0 || selectedMoveIndex >= availableMoves.size()) {
            throw new IllegalArgumentException("Mossa selezionata non valida.");
        }
    
        Move selectedMove = availableMoves.get(selectedMoveIndex);
    
        // Step 4: Esegui la mossa
        board.applyMove(selectedMove);
    }
    }
      
    


