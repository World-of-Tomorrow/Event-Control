package net.worldoftomorrow.eventcontrol;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands {
	public void reloadCache(CommandSender sender) {
		if(sender instanceof Player) {
			if(EventControl.instance().permCache.hasPermission((Player) sender, "ec.reloadcache")) {
				EventControl.instance().permCache.reloadCache();
			}
		} else {
			EventControl.instance().permCache.reloadCache();
		}
	}
	
	public void reload(CommandSender sender) {
		
	}
	
	public void version(CommandSender sender) {
		sender.sendMessage(ChatColor.BLUE + EventControl.instance().getDescription().getVersion());
	}
}
