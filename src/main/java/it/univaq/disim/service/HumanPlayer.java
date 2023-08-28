package it.univaq.disim.service;

import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

import it.univaq.disim.datamodel.Color;
import it.univaq.disim.datamodel.Move;
import it.univaq.disim.datamodel.Piece;

public class HumanPlayer extends Player implements Serializable {
	

    public HumanPlayer(Color color) {
        super(color);

    }
    
    private static final long serialVersionUID = 1L;
/** 
* Richiede la selezione di coordinate, crea la mossa con tali informazioni e successivamente la esegue
*/
    @Override
    public void makeMove(Board board, Game game) throws IllegalArgumentException{
        Scanner scanner = new Scanner(System.in);
        
        // Step 1: Richiedi all'utente di selezionare un pezzo
        if(!game.getBlackMoves().isEmpty() && !game.getWhiteMoves().isEmpty())
        	System.out.print("premi 9 per annullare le mosse, oppure ");
        System.out.println("Seleziona il pezzo da muovere (riga colonna): ");
        System.out.println("premi 8 per salvare la partita");
        System.out.println("premi 10 per arrenderti");
        int x = scanner.nextInt();
        if(x == 10) {
        	game.surrender();
        	return;
        }
        if(x == 9 && !game.getBlackMoves().isEmpty() && !game.getWhiteMoves().isEmpty()) {
        	System.out.println("Quante mosse vuoi annullare? (al massimo le ultime 5)");
        	int numberMoves = scanner.nextInt();
        	if (numberMoves < 1 || numberMoves > 5)
        		throw new IllegalArgumentException("numero di mosse da annullare non valido");
        	else if(numberMoves > game.getBlackMoves().size() )
        		throw new IllegalArgumentException("non ci sono abbastanza mosse da annullare");        		
        	game.undoMoves(board, numberMoves);
        	return;
        }
        
        if(x == 8) {
        	Save save = new Save();
			try {
				save.serialize(game,board,this,this);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			return;
        }
        
        
        
        int y = scanner.nextInt();
    
        Piece selectedPiece = board.getPieceAt(x, y);
    
        if (selectedPiece == null || selectedPiece.getColor() != this.getColor()) {
            throw new IllegalArgumentException("La casella selezionata non contiene un pezzo valido.");
        }
    
        // Step 2: Ottieni le mosse disponibili per il pezzo selezionato
        List<Move> availableMoves = selectedPiece.getAvailableMoves(board, x, y);
        
        if(availableMoves.isEmpty())
        	throw new IllegalArgumentException("nessuna mossa disponibile per il pezzo selezionato");
    
        // Step 3: Chiedi all'utente di selezionare una mossa dalla lista
        System.out.println("Mosse disponibili:");
        for (int i = 0; i < availableMoves.size(); i++) {
            System.out.println(i + ": " + availableMoves.get(i).toString());
        }
        
        System.out.print("Seleziona la mossa da eseguire (indice): ");
        int selectedMoveIndex = scanner.nextInt();
    
        if (selectedMoveIndex < 0 || selectedMoveIndex >= availableMoves.size()) {
            throw new IllegalArgumentException("Mossa selezionata non valida.");
        }
    
        Move selectedMove = availableMoves.get(selectedMoveIndex);
        
        game.addDrawMovesCounter();
        if(game.isCapturedOrPawnMove(selectedMove, board))
        	game.resetDrawMovesCounter();
    
        // Step 4: Esegui la mossa
        
        
        
        if (game.getCurrentPlayer().getColor() == Color.BIANCO) {
            game.getWhiteMoves().add(selectedMove);  // Aggiungi mossa bianca
            if(selectedMove.isCapture()) {
            	Piece deadPiece = board.getPieceAt(selectedMove.getEndXCord(), selectedMove.getEndYCord());
            	game.getDeadBlackPieces().add(deadPiece); // aggiunta pezzo nero mangiato
            }
        } else {
            game.getBlackMoves().add(selectedMove);  // Aggiungi mossa nera
            if(selectedMove.isCapture()) {
            	Piece deadPiece = board.getPieceAt(selectedMove.getEndXCord(), selectedMove.getEndYCord());
            	game.getDeadWhitePieces().add(deadPiece); //aggiunta pezzo bianco mangiato
            }
        }
        board.applyMove(selectedMove);
    }
    }
      
    


