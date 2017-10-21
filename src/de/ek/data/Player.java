package de.ek.data;

import java.awt.Color;

public class Player {
	
	public int stonesOnField = 0;
	public int stonesInHand = 9;
	public int kickedStone = 0;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + kickedStone;
		result = prime * result + stonesInHand;
		result = prime * result + stonesOnField;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (kickedStone != other.kickedStone)
			return false;
		if (stonesInHand != other.stonesInHand)
			return false;
		if (stonesOnField != other.stonesOnField)
			return false;
		return true;
	}
	
	
	
}
