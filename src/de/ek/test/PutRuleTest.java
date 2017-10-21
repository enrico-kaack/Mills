package de.ek.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.ek.data.Field;
import de.ek.data.Game;
import de.ek.data.GameLogic;
import de.ek.data.GameFactory;
import de.ek.data.Move;

public class PutRuleTest {

	@Test
	public void succeedOnEmptyField() {
		Game g = new Game();
		g.data.activePlayer = g.data.players.get(0);
		
		Move m = new Move();
		m.to = new Field(1);
		
		g.logic.put(m);
		
		assertEquals(8, g.data.activePlayer.stonesInHand);
		assertEquals(1, g.data.activePlayer.stonesOnField);
		assertEquals(g.data.activePlayer, g.data.fields.get(1).player);
		
	}

	@Test
	public void failOnEmptyHand() {
		Game g = new Game();
		g.data.activePlayer = g.data.players.get(0);
		g.data.activePlayer.stonesInHand = 0;
		
		Move m = new Move();
		m.to = new Field(1);
		
		Move response = g.logic.put(m);
		
		assertEquals(false, response.allowed);
		
	}

	@Test
	public void failOnOccupiedField() {
		Game g = new Game();
		g.data.activePlayer = g.data.players.get(0);
		
		g.data.fields.get(1).player = g.data.activePlayer;
		
		Move m = new Move();
		m.to = new Field(1);
		
		Move response = g.logic.put(m);
		
		assertEquals(false, response.allowed);
		assertEquals(9, g.data.activePlayer.stonesInHand);
		assertEquals(0, g.data.activePlayer.stonesOnField);
		assertEquals(false, response.muehle);
		
	}
	
	@Test
	public void putWithMill() {
		Game g = new Game();
		g.data.activePlayer = g.data.players.get(0);
		
		g.data.fields.get(0).player = g.data.activePlayer;
		g.data.fields.get(2).player = g.data.activePlayer;
		
		Move m = new Move();
		m.to = new Field(1);
		
		Move response = g.logic.put(m);
		
		assertEquals(true, response.allowed);
		assertEquals(8, g.data.activePlayer.stonesInHand);
		assertEquals(1, g.data.activePlayer.stonesOnField);
		assertEquals(true, response.muehle);
		
	}

	
}
