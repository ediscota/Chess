package it.univaq.disim.datamodel;

import lombok.Data;

@Data
public class Move {
    private Location startLocation;

    private Location endLocation;
}
