package net.worldoftomorrow.eventcontrol;

import java.io.File;

import net.worldoftomorrow.eventcontrol.permcache.Cache;
import net.worldoftomorrow.eventcontrol.permcache.CacheListener;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventControl extends JavaPlugin {
	
	private static EventControl instance;
	private final Cache cache = new Cache();
	
	@Override
	public void onEnable() {
		instance = this;
		PluginManager pm = this.getServer().getPluginManager();
		// Register Cache listener.
		pm.registerEvents(new CacheListener(cache), this);
	}
	
	public static EventControl instance() {
		return instance;
	}
	
	public File geti18nFolder() {
		return new File(this.getDataFolder() + File.separator + "local");
	}
}
