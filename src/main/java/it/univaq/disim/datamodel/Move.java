package it.univaq.disim.datamodel;

import lombok.Data;

@Data
public class Move {
    public Move(int xCord, int yCord, int endXCord, int endYCord) {
		// TODO Auto-generated constructor stub
	}
	private int xCord;
    private int yCord;
    private int endXCord;
    private int endYCord;
}
