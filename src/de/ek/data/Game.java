package de.ek.data;
import de.ek.ui.FieldPositions;
import de.ek.ui.View;

public class Game {
	public boolean isPlayerTurn = true;
	
	public View view;
	public GameData data;
	public GameLogic logic;
	
	public Game(){
		new FieldPositions();
		data = GameFactory.initializeGameArea();
		logic = new GameLogic(this);
		this.view = new View(this);
	}
	

}
