package de.ek.data;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Field {
	public Field(int i) {
		id = i;
	}
	public int id;
	public Player player;
	public ArrayList<Field> neighboors = new ArrayList<>(3);
	public ArrayList<Row> inRow = new ArrayList<>(3);
	
	public ArrayList<Field> getFreeNeighboors(){
		ArrayList<Field> freeNeighboors = new ArrayList<>();
		for (Field f: neighboors){
			if (f.player == null){
				freeNeighboors.add(f);
			}
		}
		
		return freeNeighboors;
		
	}
	
	
	@Override
	public String toString() {
		return "Field [id=" + id + ", player=" + player + ", neighboors=" + neighboors + ", inRow=" + inRow + "]";
	}
	
	
	
}
