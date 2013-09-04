package net.worldoftomorrow.eventcontrol.listeners;

import net.worldoftomorrow.eventcontrol.EventControl;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class ECListener implements Listener {
	public final String permNode;
	
	public ECListener(String permNode) {
		this.permNode = EventControl.BASE_PERM + permNode + ".";
	}
	
	/**
	 * Given the ending of a permission, <b>not</b> including the
	 * name of the listener, returns if the player has the permission
	 * E.G. input of "5.3" would check "ec.permNode.5.3"
	 * @param permTail
	 * @return
	 */
	public boolean checkPermission(Player p, String permTail) {
		StringBuilder sb = new StringBuilder();
		sb.append(permNode).append(permTail);
		return EventControl.instance().permCache.hasPermission(p, sb.toString());
	}
}
