package it.univaq.disim.datamodel;

public class King extends Piece {

	@Override
	public String toString() {
		if (this.getColor() == Color.BIANCO) {
			return "♔";
		} else {
			return "♚";
		}
	}
}
