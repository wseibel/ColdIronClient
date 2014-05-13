
package model.game;

import de.upb.tools.fca.*;
import java.util.*;

public class Archer extends Unit {
	/**
	 * <pre>
	 *           0..n     0..1
	 * Archer ------------------------- Tower
	 *           archer        &gt;       tower
	 * </pre>
	 */

	public static final String PROPERTY_TOWER = "tower";
	private Tower tower;

	public boolean setTower(Tower value) {
		boolean changed = false;

		if (this.tower != value) {
			Tower oldValue = this.tower;
			if (this.tower != null) {
				this.tower = null;
				oldValue.removeFromArcher(this);
			}
			this.tower = value;

			if (value != null) {
				value.addToArcher(this);
			}
			getPropertyChangeSupport().firePropertyChange(PROPERTY_TOWER,
					oldValue, value);
			changed = true;
		}
		return changed;
	}

	public Tower getTower() {
		return this.tower;
	}

	public void removeYou() {
		this.setTower(null);
		super.removeYou();
	}

}
