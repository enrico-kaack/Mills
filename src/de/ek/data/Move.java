package de.ek.data;

public class Move {
	public Field from;
	public Field to;
	public boolean allowed = false;
	public boolean muehle = false;
	public int score = 0;
	@Override
	public String toString() {
		String sFrom = "null";
		String sTo = "null";
		if (from != null){
			sFrom = String.valueOf(from.id);
		}
		if (to != null){
			sTo = String.valueOf(to.id);
		}
		
		return "Move [from=" + sFrom + " to=" + sTo + ", score=" + score + "]";
		
		
	}

}
