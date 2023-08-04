package it.univaq.disim.service;

import it.univaq.disim.datamodel.Color;
import lombok.Data;

@Data
public abstract class Player implements Engine {
    private final Color color;

}
