package net.worldoftomorrow.eventcontrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;


public class I18n {
	
	// Values are loaded as "key, value"
	// Each entry should be on a new line in the file
	private final Map<String, String> values;
	
	public I18n(String lang) {
		File langfile = this.geti18nFile(lang);
		values = loadValues(langfile);
	}
	
	public String $(String key) {
		return values.get(key);
	}
	
	/**
	 * Loads localization values to an immutable map
	 * and returns it. Loads English if given file
	 * is not valid
	 * @param f
	 * @return immutable map of values
	 */
	private Map<String, String> loadValues(File f) {
		if(Validate.isValidFile(f)) {
			return Collections.unmodifiableMap(this.mapValues(f));
		} else {
			EventControl.instance().getLogger().warning("Could not load language file, defaulting to en_US");
			return Collections.unmodifiableMap(this.mapValues(this.copyDefaultFile()));
		}
	}
	
	private final Map<String, String> mapValues(File f) {
		Map<String, String> map = new HashMap<String, String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(f));
			String line = null;
			while((line = br.readLine()) != null) {
				// Format should be some.key.whatever=My message! woo!
				String[] split = line.split("=", 2);
				map.put(split[0], split[1]);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public final File copyDefaultFile() {
		File dest = new File(EventControl.instance().geti18nFolder() + File.separator + "en_US");
		FileOutputStream fos;
		try {
			if(!dest.exists()) {
				dest.getParentFile().mkdirs();
				dest.createNewFile();
				fos = new FileOutputStream(dest);
				//IOUtils.copy(EventControl.instance().getResource("local/en_US"), fos);
				IOUtils.copy(this.getClass().getResourceAsStream("/lang/en_US"), fos);
				fos.close();
			}
			return dest;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private File geti18nFile(String language) {
		String path = EventControl.instance().getDataFolder().getPath() +
				File.separator + "lang" + File.separator + language;
		File langfile = new File(path);
		if(Validate.isValidFile(langfile)) {
			return langfile;
		} else {
			return null;
		}
	}
}
