package de.ek.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.ek.data.Field;
import de.ek.data.Game;
import de.ek.data.GameLogic;
import de.ek.data.GameFactory;
import de.ek.data.Move;

public class MoveRuleTest {

	@Test
	public void failOnOccupiedTargetField() {
		Game g = new Game();
		g.data.activePlayer = g.data.players.get(0);
		
		Move m = new Move();
		m.from = new Field(4);
		m.to = new Field(1);
		
		g.data.fields.get(1).player = g.data.players.get(1);
		
		
		Move response = g.logic.move(m);

		assertEquals(false, response.allowed);
		
	}
	
	@Test
	public void failOnMoveOfEnemyStone() {
		Game g = new Game();
		g.data.activePlayer = g.data.players.get(0);
		
		Move m = new Move();
		m.from = new Field(4);
		m.to = new Field(1);
		
		g.data.fields.get(4).player = g.data.players.get(1);
		
		
		Move response = g.logic.move(m);

		assertEquals(false, response.allowed);
		
	}
	
	@Test
	public void failOnMoveToIllegalField() {
		Game g = new Game();
		g.data.activePlayer = g.data.players.get(0);
		g.data.activePlayer.stonesInHand = 0;
		Move m = new Move();
		m.from = new Field(4);
		m.to = new Field(15);
		
		
		Move response = g.logic.move(m);

		assertEquals(false, response.allowed);
		
	}

}
