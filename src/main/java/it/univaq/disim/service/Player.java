package it.univaq.disim.service;

import java.io.Serializable;

import it.univaq.disim.datamodel.Color;
import lombok.Data;

@Data
public abstract class Player implements Engine, Serializable {
	private final Color color;
	private static final long serialVersionUID = -1180185078107663886L;

}
