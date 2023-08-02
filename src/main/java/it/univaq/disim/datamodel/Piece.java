package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data public abstract class Piece {
	
	protected int xCord;
	protected int yCord;
	protected Color color;
	public List <Move> availableMoves;
}

	
	