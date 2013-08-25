package net.worldoftomorrow.eventcontrol.permcache;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CacheListener implements Listener {
	
	private final Cache cache;
	
	public CacheListener(Cache cache) {
		this.cache = cache;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onJoin(PlayerJoinEvent event) {
		cache.updatePlayer(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onQuit(PlayerQuitEvent e) {
		cache.removePlayer(e.getPlayer());
	}
}
