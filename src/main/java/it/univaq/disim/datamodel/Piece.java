package it.univaq.disim.datamodel;

import lombok.Data;

@Data public abstract class Piece {
	
	protected int xCord;
	
	protected int yCord;
	
	protected Color color;

}