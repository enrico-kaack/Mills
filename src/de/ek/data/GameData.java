package de.ek.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class GameData {
	public HashMap<Integer, Field> fields = new HashMap<>(24, 1f);
	public ArrayList<Row> rows = new ArrayList<>(17);
	public ArrayList<Player> players = new ArrayList<>(2);
	
	public Player activePlayer;
	public int activePlayerIndex;
	public int humanPlayerIndex = 0;
	public int kiPlayerIndex = 1;
	
	public ArrayList<Field> getAllPlayerFields(Player player){
		ArrayList<Field> fields = new ArrayList<>();
		for (Field f:fields){
			if (player.equals(f.player)){
				fields.add(f);
			}
		}
		return fields;
	}
	


}
