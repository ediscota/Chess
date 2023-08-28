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
/**
 *    Restituisce l'elenco di mosse disponibili per il pezzo selezionato
 */
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

	public Piece(Color color, int xCord, int yCord, int value) {
		this.color = color;
		this.xCord = xCord;
		this.yCord = yCord;
		this.value = value;
	}
	
	public int getValueFromInstanceOf()
    {
    	if(this instanceof Pawn)
    		return 1;
    	else if(this instanceof Rook)
    		return 5;
    	else if(this instanceof Knight || this instanceof Bishop)
    		return 3;
    	else
    		return 9;
    }
}
