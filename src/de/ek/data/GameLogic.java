package de.ek.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GameLogic {
	public HashMap<Integer, Field> fields = new HashMap<>(24, 1f);
	public ArrayList<Row> rows = new ArrayList<>(17);
	public ArrayList<Player> players = new ArrayList<>(2);
	
	public Player activePlayer;
	
	/**
	 * Places a stone on the field
	 * @return success
	 */
	public Move put(Move move){
		if (fields.get(move.to.id).player != null){
			move.allowed = false;
		}else if (activePlayer.stonesInHand > 0){
			fields.get(move.to.id).player = activePlayer;
			activePlayer.placeOneStoneOnField();
			move.allowed = true;
			move.muehle = checkForMuehle(fields.get(move.to.id));
		}
		return move;
	}
	
	/**
	 * Move a stone 
	 * @param move
	 * @return
	 */
	public Move move(Move move){
		if (fields.get(move.from.id).player == activePlayer && fields.get(move.to.id).player == null){
			//check if distance from "from" field to "to" field is 1
			if (fields.get(move.from.id).neighboors.contains(fields.get(move.to.id)) || activePlayer.isJumpPhase()){
				fields.get(move.from.id).player = null;
				fields.get(move.to.id).player = activePlayer;
				move.allowed = true;
				move.muehle = checkForMuehle(move.to);
				removeOldMuehle(move.from);
			}
		}
		return move;
	}

	private void removeOldMuehle(Field field) {
		for(int i=0; i<field.inRow.size();i++){
			field.inRow.get(i).isMuehle = false;
		}
		
	}

	private boolean checkForMuehle(Field field) {
		boolean muehle = true;
		for(int i=0; i<field.inRow.size();i++){
			for(int j=0;j<field.inRow.get(i).fields.size();j++){
				if (field.inRow.get(i).fields.get(j).player != activePlayer){
					muehle = false;
				}
			}
			if (muehle)
				markAsMuehle(field.inRow.get(i));
				return muehle;
		}
		return muehle;
		
	}
	
	private void markAsMuehle(Row r){
		r.isMuehle = true;
	}

	@Override
	public String toString() {
		return "Game [fields=" + fields + ", rows=" + rows + ", players=" + players + ", activePlayer=" + activePlayer
				+ "]";
	}
	

	
	
}
