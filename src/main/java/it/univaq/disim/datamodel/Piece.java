package it.univaq.disim.datamodel;

import java.util.List;

import it.univaq.disim.service.Board;
import lombok.Data;

@Data
public abstract class Piece implements Cloneable {

	private int xCord;
	private int yCord;
	protected Color color;
	private List<Move> availableMoves;

	public abstract List<Move> getAvailableMoves(Board board, int xCord, int yCord);

	@Override
	public Piece clone() {
		try {
			return (Piece) super.clone();
		} catch (CloneNotSupportedException e) {
			// Gestisci l'eccezione se la classe non supporta la clonazione
			e.printStackTrace();
			return null;
		}
	}

	public abstract boolean hasAdjacentPieceOfSameColor(int xCord, int yCord, Color pieceColor, Board board);
	
	public Piece(Color color, int xCord, int yCord) {
		this.color=color;
		this.xCord=xCord;
		this.yCord=yCord;
	}
	
}
