package de.ek.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
		
		m = evaluateBestMoveOption(poss);		
		
		if (game.data.activePlayer.isPutPhase()){
			
			Move resp = game.logic.put(m);
			game.logic.switchPlayer();
		}else{
			Move resp = game.logic.move(m);
			if (resp.muehle){
				kickStone();
			}
			game.logic.switchPlayer();
		}
	}
	private Move evaluateBestMoveOption(ArrayList<Move> poss) {
		/*+10 for fields with 3-4 neighboors
		* +15 for rows with just one own stone in row
		* +30 for making a mill
		* +30 for preventing an enemy mill
		* -5 for breaking an own mill
		*/
		for (Move m:poss){
			if (hasMoreThanThreeNeighboors(m.to)){
				m.score += 10;
			}
			if (wouldHaveTwoStoneInRow(m.to)){
				m.score += 15;
			}
			if (wouldMakeAMill(m.to)){
				m.score += 30;
			}
			if (wouldPreventAMill(m.to)){
				m.score += 30;
			}
			if (wouldBreakMill(m.from)){
				m.score -= 5;
			}
			
			
		}
		poss.sort(((m1, m2) -> m2.score - m1.score));
		
		System.out.println("-------");
		for (Move m:poss){
			System.out.println(m);
		}
		System.out.println("-------");
		
		return poss.get(0);
	}
	private boolean wouldBreakMill(Field from) {
		if (from == null) return false;
		for (Row r:from.inRow){
			if (r.isMuehle){
				return true;
			}
		}
		return false;
	}
	private boolean wouldPreventAMill(Field to) {
		for (Row r:to.inRow){
			if (numberOfPlayersStonesInRow(r, game.data.players.get(game.data.humanPlayerIndex)) > 1){
				return true;
			}
		}
		return false;
	}
	private boolean wouldMakeAMill(Field to) {
		for (Row r:to.inRow){
			if (numberOfPlayersStonesInRow(r, game.data.players.get(game.data.kiPlayerIndex)) > 1){
				return true;
			}
		}
		return false;
	}
	private boolean wouldHaveTwoStoneInRow(Field to) {
		for (Row r:to.inRow){
			if (numberOfPlayersStonesInRow(r, game.data.players.get(game.data.kiPlayerIndex)) == 2){
				return true;
			}
		}
		return false;
	}
	private boolean hasMoreThanThreeNeighboors(Field to) {
		return to.neighboors.size() >= 3;
	}
	
	private int numberOfPlayersStonesInRow(Row r, Player p){
		int number = 0;
		for (Field f:r.fields){
			if (p.equals(f.player)){
				number++;
			}
		}
		
		return number;
		
	}
	
	
	public void kickStone() {
		ArrayList<Field> enemyFields = game.data.getAllPlayerFields(this.game.data.players.get(game.data.humanPlayerIndex));
		ArrayList<Move> poss = new ArrayList<>();
		for (Field f:enemyFields){
			boolean allowed = true;
			for (Row r: f.inRow){
				if (r.isMuehle){
					allowed = false;
				}
			}
			if (allowed){
				Move m = new Move();
				m.to = f;
				poss.add(m);
			}
		}
		
		Move m = new Move();
		m = evaluateBestKickOption(poss);
		Move resp = game.logic.kickStone(m);
		game.logic.switchPlayer();
		
	}
	
	private Move evaluateBestKickOption(ArrayList<Move> poss){
		for (Move m:poss){

			if (wouldPreventAMill(m.to)){
				m.score += new Random().nextInt(10);
			}
			
			
		}
		poss.sort(((m1, m2) -> m2.score - m1.score));
		
		System.out.println("----KICKING---");
		for (Move m:poss){
			System.out.println(m);
		}
		System.out.println("-------");
		
		return poss.get(0);
	}
	
		
	

}
