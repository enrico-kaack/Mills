package de.ek.data;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameFactory {
	public static GameData initializeGameArea(){
		GameData game = new GameData();
		
		
		//generate player
		Player white = new Player(Color.WHITE);
		Player black = new Player(Color.BLACK);
		game.players.add(white);
		game.players.add(black);
		game.activePlayer = white;
		game.activePlayerIndex = 0;
		
		//generate fields
		for (int i=0;i<24;i++){
			Field f = new Field(i);
			game.fields.put(i, f);
		}
		
		//add neighbours based on file
		try (BufferedReader br = new BufferedReader(new FileReader("neighboors.txt"))) {
			int lineNumber = 0;
		    String line;
		    while ((line = br.readLine()) != null) {
		       String[] neighboors = line.split(",");
		       for(int i=0;i<neighboors.length;i++){
		    	   game.fields.get(lineNumber).neighboors.add(game.fields.get(Integer.parseInt(neighboors[i])));
		       }
		       lineNumber++;
		    }
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//add rows based on file
		try (BufferedReader br = new BufferedReader(new FileReader("rows.txt"))) {
			int lineNumber = 0;
		    String line;
		    while ((line = br.readLine()) != null) {
		       String[] rows = line.split(",");
		       Row r = new Row();
		       for(int i=0;i<rows.length;i++){
		    	   r.fields.add(game.fields.get(Integer.parseInt(rows[i])));
		    	   game.fields.get(Integer.parseInt(rows[i])).inRow.add(r);
		       }
		       game.rows.add(r);
		       lineNumber++;
		    }
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		
		
		return game;
		
	}
}
