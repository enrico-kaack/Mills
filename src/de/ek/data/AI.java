package de.ek.data;

import java.util.ArrayList;
import java.util.Random;

public class AI {
	public Game game;
	public int aiId;
	
	public AI(Game game, int id) {
		this.aiId = id;
		this.game = game;
	}
	public void makeNextMove(){
		Move m = new Move();
		
		ArrayList<Move> poss = game.logic.getAllPossibleNextMoves();
		System.out.println(poss.size());
		m = poss.get(new Random().nextInt(poss.size()));
		
		
		if (game.data.activePlayer.isPutPhase()){
			Move resp = game.logic.put(m);
			game.logic.switchPlayer();
		}else{
			Move resp = game.logic.move(m);
			game.logic.switchPlayer();
		}
	}
	
		
	

}
