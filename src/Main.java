import de.ek.data.Game;
import de.ek.data.GameFactory;
import de.ek.ui.FieldPositions;
import de.ek.ui.View;

public class Main {

	public static void main(String[] args) {
			new FieldPositions();
			Game g  = GameFactory.initializeGameArea();
			View v = new View(g);
			
		
	}

}
