package it.univaq.disim.datamodel;

import lombok.Data;

@Data public abstract class Piece {
	
	protected Location location;

	protected Color color;

}