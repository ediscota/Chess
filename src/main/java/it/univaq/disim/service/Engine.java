package it.univaq.disim.service;

/**
 * Esegue la mossa creata o generata casualmente
 */
public interface Engine {
	void makeMove(Board board, Game game);
}
