package net.worldoftomorrow.eventcontrol;

import java.io.File;

public class Validate {
	public static boolean isValidFile(File f) {
		return notNull(f) && f.isFile() && f.exists() && f.canRead() && f.canWrite();
	}
	
	public static boolean isValidDir(File f) {
		return notNull(f) && f.isDirectory() && f.exists() && f.canRead() && f.canWrite();
	}
	
	public static boolean notNull(Object o) {
		return !(o == null);
	}
}
