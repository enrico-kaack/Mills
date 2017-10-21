package de.ek.data;

public class Player {
	public static int WHITE = 0;
	public static int BLACK = 1;
	
	public int stonesOnField = 0;
	public int stonesInHand = 9;
	public int color;
	
	public Player(int color) {
		color = color;
	}
	
	public void placeOneStoneOnField(){
		stonesOnField++;
		stonesInHand--;
	}
	
	public boolean isJumpPhase(){
		return (stonesOnField < 4 && stonesInHand == 0);
	}

	@Override
	public String toString() {
		return "Player [stonesOnField=" + stonesOnField + ", stonesInHand=" + stonesInHand + ", color=" + color + "]";
	}
	
	
	
}
