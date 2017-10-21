package de.ek.data;
import de.ek.ui.FieldPositions;
import de.ek.ui.GameUiHandler;
import de.ek.ui.View;

public class Game {
	
	public View view;
	public GameData data;
	public GameLogic logic;
	public GameUiHandler uiHandler;
	public AI ai;
	
	public Game(){
		data = GameFactory.initializeGameArea();
		logic = new GameLogic(this);
		uiHandler = new GameUiHandler(this);
		ai = new AI(this, data.kiPlayerIndex);
		this.view = new View(this);
	}
	

}
