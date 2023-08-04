package it.univaq.disim.service;

import java.util.List;
import java.util.Random;

import it.univaq.disim.datamodel.Color;
import it.univaq.disim.datamodel.Move;
import it.univaq.disim.datamodel.Piece;

public class CPUPlayer extends Player {

    public CPUPlayer(Color color) {
        super(color);

    }

    @Override
    public void makeMove(Board board) {
  Color playerColor = this.getColor();
        List<Piece> pieces = board.getPiecesByColor(playerColor);
          Random random = new Random();
        for (Piece piece : pieces) {
            int xCord = piece.getXCord();
            int yCord = piece.getYCord();

            // Ottieni le mosse disponibili per il pezzo corrente
            List<Move> availableMoves = piece.getAvailableMoves(board, xCord, yCord);

            // Se ci sono mosse disponibili per questo pezzo, scegliene una a caso e applicala
            if (!availableMoves.isEmpty()) {
                int randomIndex = random.nextInt(availableMoves.size());
                Move randomMove = availableMoves.get(randomIndex);
                board.applyMove(randomMove);
                return; 
            }
        }
    }

}
