package it.univaq.disim.datamodel;

import lombok.Data;

@Data
public class Move {
    private int startXCord;
    private int startYCord;
    private int endXCord;
    private int endYCord;

    public Move(int xCord, int yCord, int endXCord, int endYCord) {
		this.startXCord=xCord;
    this.startYCord=yCord;
    this.endXCord=endXCord;
    this.endYCord=endYCord;
	}
	
    @Override
    public String toString () {
      return  "(" + startXCord + ", " + startYCord + ") -> (" + endXCord + ", " + endYCord + ")";

    }
   
}
