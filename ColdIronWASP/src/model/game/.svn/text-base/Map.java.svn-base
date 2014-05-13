package model.game;

import de.upb.tools.pcs.PropertyChangeClient;
import de.upb.tools.sdm.*;
import java.util.*;
import de.upb.tools.fca.*;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class Map implements PropertyChangeClient {
	/**
	 * <pre>
	 *           1..1     1..1
	 * Map ------------------------- Game
	 *           map        &gt;       game
	 * </pre>
	 */

	public static final String PROPERTY_GAME = "game";
	private Game game;

	public boolean setGame(Game value) {
		boolean changed = false;

		if (this.game != value) {
			Game oldValue = this.game;
			if (this.game != null) {
				this.game = null;
				oldValue.setMap(null);
			}
			this.game = value;

			if (value != null) {
				value.setMap(this);
			}
			getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME,
					oldValue, value);
			changed = true;
		}
		return changed;
	}

	public Game getGame() {
		return this.game;
	}

	public static final String PROPERTY_ID = "id";

	private String id;

	public void setId(String value) {
		if (JavaSDM.stringCompare(this.id, value) != 0) {
			String oldValue = this.id;
			this.id = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_ID,
					oldValue, value);
		}
	}

	public String getId() {
		return this.id;
	}

	/**
	 * <pre>
	 *           1..1     0..n
	 * Map ------------------------- Sector
	 *           map        &lt;       sector
	 * </pre>
	 */

	public static final String PROPERTY_SECTOR = "sector";
	private FPropHashSet sector;

	public boolean addToSector(Sector value) {
		boolean changed = false;

		if (value != null) {
			if (this.sector == null) {
				this.sector = new FPropHashSet(this, PROPERTY_SECTOR);

			}
			changed = this.sector.add(value);
			if (changed) {
				value.setMap(this);
			}
		}
		return changed;
	}

	public boolean removeFromSector(Sector value) {
		boolean changed = false;

		if ((this.sector != null) && (value != null)) {
			changed = this.sector.remove(value);
			if (changed) {
				value.setMap(null);
			}
		}
		return changed;
	}

	public void removeAllFromSector() {
		Sector tmpValue;
		Iterator iter = this.iteratorOfSector();

		while (iter.hasNext()) {
			tmpValue = (Sector) iter.next();
			this.removeFromSector(tmpValue);
		}
	}

	public boolean hasInSector(Sector value) {
		return ((this.sector != null) && (value != null) && this.sector
				.contains(value));
	}

	public Iterator iteratorOfSector() {
		return ((this.sector == null) ? FEmptyIterator.get() : this.sector
				.iterator());

	}

	public int sizeOfSector() {
		return ((this.sector == null) ? 0 : this.sector.size());
	}

	public static final String PROPERTY_NAME = "name";

	private String name;

	public void setName(String value) {
		if (JavaSDM.stringCompare(this.name, value) != 0) {
			String oldValue = this.name;
			this.name = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME,
					oldValue, value);
		}
	}

	public String getName() {
		return this.name;
	}

	public void removeYou() {
		this.setGame(null);
		this.removeAllFromSector();
	}

	protected final PropertyChangeSupport listeners = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().removePropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String property,
			PropertyChangeListener listener) {
		getPropertyChangeSupport()
				.addPropertyChangeListener(property, listener);
	}

	public void removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		getPropertyChangeSupport().removePropertyChangeListener(property,
				listener);
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

}
