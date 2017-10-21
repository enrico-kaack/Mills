package de.ek.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GameLogic {
	public Game game;
	public GameLogic(Game game) {
		this.game = game;
	}
	
	/**
	 * Places a stone on the field
	 * @return success
	 */
	public Move put(Move move){
		if (game.data.fields.get(move.to.id).player != null){
			move.allowed = false;
		}else if (game.data.activePlayer.stonesInHand > 0){
			game.data.fields.get(move.to.id).player = game.data.activePlayer;
			game.data.activePlayer.placeOneStoneOnField();
			move.allowed = true;
			move.muehle = checkForMuehle(game.data.fields.get(move.to.id));
		}
		return move;
	}
	
	/**
	 * Move a stone 
	 * @param move
	 * @return
	 */
	public Move move(Move move){
		if (game.data.fields.get(move.from.id).player == game.data.activePlayer && game.data.fields.get(move.to.id).player == null){
			//check if distance from "from" field to "to" field is 1
			if (game.data.fields.get(move.from.id).neighboors.contains(game.data.fields.get(move.to.id)) || game.data.activePlayer.isJumpPhase()){
				game.data.fields.get(move.from.id).player = null;
				game.data.fields.get(move.to.id).player = game.data.activePlayer;
				move.allowed = true;
				move.muehle = checkForMuehle(move.to);
				removeOldMuehle(move.from);
			}
		}
		return move;
	}
	
	public Move kickStone(Move m){
		if (game.data.activePlayer != m.to.player){
			if (!isInMill(m.to)){
				game.data.activePlayer.kickedStone++;
				m.to.player.stonesOnField--;
				m.to.player = null;
				m.allowed = true;
				return m;
			}
		}
		m.allowed = false;;
		return m;
	}
	
	private boolean isInMill(Field to) {
		for (Row r: to.inRow){
			if (r.isMuehle){
				return true;
			}
		}
		return false;
		
	}

	public Player switchPlayer(){
		if (this.game.data.activePlayerIndex == 0){
			this.game.data.activePlayerIndex = 1;
			this.game.data.activePlayer = this.game.data.players.get(this.game.data.activePlayerIndex);
		}else if(this.game.data.activePlayerIndex == 1){
			this.game.data.activePlayerIndex = 0;
			this.game.data.activePlayer = this.game.data.players.get(this.game.data.activePlayerIndex);
		}
		return this.game.data.activePlayer;
	}
	
	public boolean isPlayersTurn(){
		//return game.data.activePlayerIndex == game.data.humanPlayerIndex;
		return true;
	}
	
	private void removeOldMuehle(Field field) {
		for(int i=0; i<field.inRow.size();i++){
			field.inRow.get(i).isMuehle = false;
		}
		
	}

	private boolean checkForMuehle(Field field) {
		
		for(int i=0; i<field.inRow.size();i++){
			boolean muehle = true;
			for(int j=0;j<field.inRow.get(i).fields.size();j++){
				System.out.println(game.data.activePlayer.equals(field.inRow.get(i).fields.get(j).player));
				if (!game.data.activePlayer.equals(field.inRow.get(i).fields.get(j).player)){
					muehle = false;
				}
			}
			if (muehle){
				markAsMuehle(field.inRow.get(i));
				return muehle;
			}
		}
		return false;
		
	}
	
	private void markAsMuehle(Row r){
		r.isMuehle = true;
	}

	public boolean isPlayerInPutPhase() {
		return game.data.activePlayer.stonesInHand > 0;
	}


	

	
	
}
