package de.ek.ui;

import de.ek.data.Field;
import de.ek.data.Game;
import de.ek.data.GameConfig;
import de.ek.data.Move;

public class GameUiHandler {
	public Game game;
	public LogicResponseListener listener;
	public boolean stateKickStone = false;
	
	public GameUiHandler(Game game) {
		this.game = game;
	}
	
	public void setListener(LogicResponseListener listener){
		this.listener = listener;
	}
	
	public void clickOnField(int x, int y){
		if (game.logic.isPlayersTurn() && !stateKickStone){
			int fieldNumber = calculateFieldIndexBasedOnMouseClick(x, y);
			if (fieldNumber > -1){
				if (game.logic.isPlayerInPutPhase()){
					Move m = new Move();
					m.to = game.data.fields.get(fieldNumber);
					handleLogicResponse(game.logic.put(m));
					
				}
			}
		}
		if (game.logic.isPlayersTurn() && stateKickStone){
			int fieldNumber = calculateFieldIndexBasedOnMouseClick(x, y);
			if (fieldNumber > -1){
					Move m = new Move();
					m.to = game.data.fields.get(fieldNumber);
					handleLogicResponse(game.logic.kickStone(m));
			}
		}
	}
	
	private void handleLogicResponse(Move m){
		if (m.allowed && m.muehle){
			listener.onMillDetected();
			stateKickStone = true;
		}else if (!m.allowed){
			listener.onMoveForbidden();
		}else{
			game.logic.switchPlayer();
			if (stateKickStone){
				stateKickStone = false;
			}
		}
		
	}

	private int calculateFieldIndexBasedOnMouseClick(int x, int y) {
		for (Field f : game.data.fields.values()){
			if (calcDistance(x, y, f) < GameConfig.STONE_DIAMETER){
				return f.id;
			}
		}
		return -1;
	}

	private double calcDistance(int x, int y, Field f) {
		double cleanX = x - GameConfig.GAME_AREA_OFFSET_X;
		double cleanY = y - GameConfig.GAME_AREA_OFFSET_Y;
		double fX = FieldPositions.fieldPosistionTable.get(f.id).x*GameConfig.GAME_AREA_WIDTH;
		double fY = FieldPositions.fieldPosistionTable.get(f.id).y*GameConfig.GAME_AREA_HEIGHT;
		
		double distance = Math.sqrt(Math.pow(cleanX-fX, 2)+Math.pow(cleanY-fY, 2));

		return distance;
	}

}
