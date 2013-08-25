package net.worldoftomorrow.eventcontrol;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class PlayerUtil {
	
	/**
	 * Gets all of a players set permissions and their respective values then
	 * returns them in a map
	 * @param p
	 * @return
	 */
	public static Map<String, Boolean> getPlayersPermissions (Player p){
		final Map<String, Boolean> perms = new HashMap<String, Boolean>();
		final Iterator<PermissionAttachmentInfo> permset = p.getEffectivePermissions().iterator();
		while(permset.hasNext()) {
			PermissionAttachmentInfo pai = permset.next();
			perms.put(pai.getPermission(), pai.getValue());
		}
		return perms;
	}
}
