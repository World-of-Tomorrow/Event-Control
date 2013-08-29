package net.worldoftomorrow.eventcontrol.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.PluginManager;

import net.worldoftomorrow.eventcontrol.EventControl;

public class ListenerLoader {
	
	private List<ECListener> listeners = new ArrayList<ECListener>();
	
	public void registerListeners() {
		PluginManager pm = EventControl.instance().getServer().getPluginManager();
		EventControl instance = EventControl.instance();
		for(ECListener l : listeners) {
			pm.registerEvents(l, instance);
		}
	}
	
	public void loadListeners() {
		File lisFolder = new File(EventControl.instance().getDataFolder() + "/listeners");
		if(!lisFolder.exists()) lisFolder.mkdir();
		
	}
	
	/**
	 * We can not reload listeners, because Bukkit can not unregister
	 * events until a restart, only loading new event listeners is possible.
	 */
	public void loadNewListeners() {
		
	}
}
