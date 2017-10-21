package de.ek.data;

import java.awt.Color;

public class Player {
	
	public int stonesOnField = 0;
	public int stonesInHand = 9;
	public Color color;
	
	public Player(Color color) {
		this.color = color;
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
