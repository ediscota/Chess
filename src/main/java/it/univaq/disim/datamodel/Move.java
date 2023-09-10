package it.univaq.disim.datamodel;

import java.io.Serializable;

import lombok.Data;

@Data
public class Move implements Serializable {

	private static final long serialVersionUID = -2053292465717212393L;
	private int startXCord;
	private int startYCord;
	private int endXCord;
	private int endYCord;
	private boolean isCapture;

	public Move(int xCord, int yCord, int endXCord, int endYCord, boolean isCapture) {
		this.startXCord = xCord;
		this.startYCord = yCord;
		this.endXCord = endXCord;
		this.endYCord = endYCord;
		this.isCapture = isCapture;
	}

	public Move() {

	}

	@Override
	public String toString() {
		return "(" + startXCord + ", " + startYCord + ") -> (" + endXCord + ", " + endYCord + ")";

	}

}
