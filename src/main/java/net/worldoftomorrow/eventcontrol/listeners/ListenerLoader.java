package net.worldoftomorrow.eventcontrol.listeners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;

import net.worldoftomorrow.eventcontrol.EventControl;
import net.worldoftomorrow.eventcontrol.Validate;

public class ListenerLoader {
	
	private final EventControl instance = EventControl.instance();
	private List<ECListener> listeners = new ArrayList<ECListener>();
	private final File listenerFolder = new File(instance.getDataFolder() + "/listeners");
	
	public ListenerLoader() {
		if(!listenerFolder.exists()) listenerFolder.mkdir();
	}
	
	public void registerListeners() {
		PluginManager pm = EventControl.instance().getServer().getPluginManager();
		EventControl instance = EventControl.instance();
		for(ECListener l : listeners) {
			pm.registerEvents(l, instance);
		}
	}
	
	public void loadListeners() {
		File lisFolder = new File(EventControl.instance().getDataFolder() + "/listeners");
		if(!Validate.isValidDir(lisFolder)) {
			EventControl.instance().getLogger().severe("Listener folder is not valid!");
			return;
		}
		List<URL> urls = new ArrayList<URL>();
		// Get the URL for each jar containing feature(s)
		for(File f : lisFolder.listFiles()) {
			if(Validate.isValidFile(f) && f.getName().endsWith(".jar")) {
				try {
					urls.add(f.toURI().toURL());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
		URLClassLoader urlcl = new URLClassLoader((URL[]) urls.toArray());
		Enumeration<URL> lisfiles;
		try {
			lisfiles = urlcl.getResources("listeners.txt");
		} catch (IOException e) {
			EventControl.instance().getLogger().severe("Could not load listeners files!");
			e.printStackTrace();
			return;
		}
		
		while(lisfiles.hasMoreElements()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(lisfiles.nextElement().getFile());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				continue;
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			String line;
			try {
				while((line = br.readLine()) != null) {
					urlcl.loadClass(line).getConstructor(Event.class, String.class);
				}
			} catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	public URL[] getListenerURLs() {
		List<URL> urls =  new ArrayList<URL>();
		for (File jar : listenerFolder.listFiles()) {
			try {
				if(Validate.isValidJar(jar)) {
					urls.add(jar.toURI().toURL());
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return urls.toArray(new URL[urls.size()]);
	}
	
	
}
