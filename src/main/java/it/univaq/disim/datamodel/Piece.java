package it.univaq.disim.datamodel;

import java.io.Serializable;
import java.util.List;

import it.univaq.disim.service.Board;
import lombok.Data;

@Data
public abstract class Piece implements Cloneable, Serializable {

	private static final long serialVersionUID = 5027965187758086632L;
	private int xCord;
	private int yCord;
	private int value;
	protected Color color;

	private List<Move> availableMoves;

	public abstract List<Move> getAvailableMoves(Board board, int xCord, int yCord);
	// public abstract List<Move> getAvailableMoves(Board board);

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

	public Piece(Color color, int xCord, int yCord, int value) {
		this.color = color;
		this.xCord = xCord;
		this.yCord = yCord;
		this.value = value;
	}

}
