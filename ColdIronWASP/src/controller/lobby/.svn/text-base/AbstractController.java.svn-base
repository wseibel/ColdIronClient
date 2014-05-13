package controller.lobby;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;

import de.upb.tools.pcs.PropertyChangeClient;

public abstract class AbstractController implements PropertyChangeListener
{
	Collection<PropertyChangeClient> observedObjects = new ArrayList<PropertyChangeClient>();

	public void start()
	{
		registerListeners();
		refreshUI();
	}

	public void stop()
	{
		for (PropertyChangeClient observed : observedObjects)
		{
			observed.removePropertyChangeListener(this);
		}
	}

	public abstract void refreshUI();

	public abstract void registerListeners();

	protected final void listenTo(String property, PropertyChangeClient client)
	{
		if (property != null)
		{
			client.addPropertyChangeListener(property, this);
		} else
		{
			client.addPropertyChangeListener(this);
		}

		observedObjects.add(client);
	}

	protected final void listenTo(PropertyChangeClient client)
	{
		listenTo(null, client);
	}
}
