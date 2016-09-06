package de.cormag.projectf.utils;

public class PathBool {
	
	private String path;
	private boolean bool;

	public PathBool(String path, boolean bool) {
		this.path = path;
		this.bool = bool;
	}
	
	public String getPath(){
		
		return path;
	}
	
	public boolean getStatus(){
		
		return bool;
		
	}
	
}
