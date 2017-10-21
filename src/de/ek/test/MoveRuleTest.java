package de.ek.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.ek.data.Field;
import de.ek.data.GameLogic;
import de.ek.data.GameFactory;
import de.ek.data.Move;

public class MoveRuleTest {

	@Test
	public void failOnOccupiedTargetField() {
		GameLogic g = GameFactory.initializeGameArea();
		g.activePlayer = g.players.get(0);
		
		Move m = new Move();
		m.from = new Field(4);
		m.to = new Field(1);
		
		g.fields.get(1).player = g.players.get(1);
		
		
		Move response = g.move(m);

		assertEquals(false, response.allowed);
		
	}
	
	@Test
	public void failOnMoveOfEnemyStone() {
		GameLogic g = GameFactory.initializeGameArea();
		g.activePlayer = g.players.get(0);
		
		Move m = new Move();
		m.from = new Field(4);
		m.to = new Field(1);
		
		g.fields.get(4).player = g.players.get(1);
		
		
		Move response = g.move(m);

		assertEquals(false, response.allowed);
		
	}
	
	@Test
	public void failOnMoveToIllegalField() {
		GameLogic g = GameFactory.initializeGameArea();
		g.activePlayer = g.players.get(0);
		g.activePlayer.stonesInHand = 0;
		Move m = new Move();
		m.from = new Field(4);
		m.to = new Field(15);
		
		
		Move response = g.move(m);

		assertEquals(false, response.allowed);
		
	}

}
