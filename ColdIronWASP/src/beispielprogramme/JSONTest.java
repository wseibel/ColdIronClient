package beispielprogramme;

import java.util.Iterator;

import model.game.Archer;
import model.game.Building;
import model.game.Game;
import model.game.SectorElement;
import model.game.Unit;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();
		SectorElement newBuilding = new SectorElement();
		//game.addToSectorElement((Building)newBuilding);


			// check if the sectorElement which should be deleted is in the game
			// and set it null
			for (Iterator<SectorElement> sectorElementIterator = game
					.iteratorOfSectorElement(); sectorElementIterator.hasNext();) {
				SectorElement sectorElement = sectorElementIterator.next();
					sectorElement.removeYou();
			}
			return;
		}

}
