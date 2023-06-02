package it.univaq.disim.datamodel;

import lombok.Data;

@Data
public class Move {
    private int startXCord;
    private int startYCord;
    private int endXCord;
    private int endYCord;
}
