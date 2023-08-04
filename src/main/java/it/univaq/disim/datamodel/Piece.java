package it.univaq.disim.datamodel;

import java.util.List;

import it.univaq.disim.service.Board;
import lombok.Data;

@Data
public abstract class Piece {

	private int xCord;
	private int yCord;
	private Color color;
	private List<Move> availableMoves;

	public abstract List <Move> getAvailableMoves (Board board, int xCord, int yCord);
}
