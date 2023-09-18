package it.univaq.disim.datamodel;

import java.io.Serializable;

public enum Color implements Serializable {
	BIANCO, NERO;

	/**
	 * Restituisce il colore opposto a quello preso in considerazione
	 */
	public Color oppositeColor() {
		return this == BIANCO ? NERO : BIANCO;
	}

}
