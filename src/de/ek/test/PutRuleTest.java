package de.ek.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.ek.data.Field;
import de.ek.data.GameLogic;
import de.ek.data.GameFactory;
import de.ek.data.Move;

public class PutRuleTest {

	@Test
	public void succeedOnEmptyField() {
		GameLogic g = GameFactory.initializeGameArea();
		g.activePlayer = g.players.get(0);
		
		Move m = new Move();
		m.to = new Field(1);
		
		g.put(m);
		
		assertEquals(8, g.activePlayer.stonesInHand);
		assertEquals(1, g.activePlayer.stonesOnField);
		assertEquals(g.activePlayer, g.fields.get(1).player);
		
	}

	@Test
	public void failOnEmptyHand() {
		GameLogic g = GameFactory.initializeGameArea();
		g.activePlayer = g.players.get(0);
		g.activePlayer.stonesInHand = 0;
		
		Move m = new Move();
		m.to = new Field(1);
		
		Move response = g.put(m);
		
		assertEquals(false, response.allowed);
		
	}

	@Test
	public void failOnOccupiedField() {
		GameLogic g = GameFactory.initializeGameArea();
		g.activePlayer = g.players.get(0);
		
		g.fields.get(1).player = g.activePlayer;
		
		Move m = new Move();
		m.to = new Field(1);
		
		Move response = g.put(m);
		
		assertEquals(false, response.allowed);
		assertEquals(9, g.activePlayer.stonesInHand);
		assertEquals(0, g.activePlayer.stonesOnField);
		assertEquals(false, response.muehle);
		
	}
	
	@Test
	public void putWithMill() {
		GameLogic g = GameFactory.initializeGameArea();
		g.activePlayer = g.players.get(0);
		
		g.fields.get(0).player = g.activePlayer;
		g.fields.get(2).player = g.activePlayer;
		
		Move m = new Move();
		m.to = new Field(1);
		
		Move response = g.put(m);
		
		assertEquals(true, response.allowed);
		assertEquals(8, g.activePlayer.stonesInHand);
		assertEquals(1, g.activePlayer.stonesOnField);
		assertEquals(true, response.muehle);
		
	}

	
}
