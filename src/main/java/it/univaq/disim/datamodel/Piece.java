package it.univaq.disim.datamodel;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public abstract class Piece {

	private int xCord;
	private int yCord;
	private Color color;
	public List<Move> availableMoves;
}
