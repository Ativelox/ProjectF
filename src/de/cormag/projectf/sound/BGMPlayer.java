package de.cormag.projectf.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import de.cormag.projectf.main.Handler;
import de.cormag.projectf.worlds.World;
import de.cormag.projectf.worlds.music.MusicWorld;

public class BGMPlayer {

	private Clip clip;
	private AudioInputStream audioInputStream;
	private FloatControl gainControl;
	
	private InputStream is;
	private Handler handler;
	private String lastClip;
	private World[] worlds;
	private Clip[] clips;
	public static final String FILE_EXTENSION = ".pfsf";
	
	

	public BGMPlayer(Handler handler) {
		
		this.handler = handler;
		
	}
	
	public void tick(){
		
//		if(handler.getKeyManager().arrowUp && gainControl.getValue() + 0.2 < gainControl.getMaximum()){
//			
//			gainControl.setValue((float) (gainControl.getValue() + 0.2));
//			
//		}else if(handler.getKeyManager().arrowDown && gainControl.getValue() - 0.2 > gainControl.getMinimum()){
//			
//			gainControl.setValue((float) (gainControl.getValue() - 0.2));
//			
//		}
		
	}
	
	public void setSound(String name){

		if(!name.equals(lastClip)){

			if(audioInputStream != null){
				
				try {
					audioInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				clip.stop();
			}
			
			is = getClass().getResourceAsStream("/music/" + name);
	
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-10f);
				
			} catch (Exception e) {
	
				e.printStackTrace();
			}
			
			lastClip = name;
		}
		
		
	}
	
	public void setClip(Clip clip){
		
		if(audioInputStream != null){
			
			try {
				audioInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.clip.stop();
		}
		
		this.clip = clip;
		
		playSound();
		
		
	}

	public void playSound() {
		if (!clip.isRunning()) {
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
		}
	}

	public Clip getClip() {

		return clip;

	}
	
	public void recoverLastSound(MusicWorld world){
		
		setSound(world.getDefaultSoundtrack());
		playSound();
		
	}

	public void stopCurrentSound() {

		if (audioInputStream != null && clip.isRunning()) {
			try {
				audioInputStream.close();
				clip.stop();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public void changeVolume(float volumeAdjust) {

		gainControl.setValue(volumeAdjust);

	}

	public Float getCurrentSoundVolume() {

		return gainControl.getValue();

	}
	
	public static String[] getAllBGMs(){
		
		ArrayList<String> allBGMNamesList = new ArrayList<>();
		
		String path = "music";
		
		final File jarFile = new File(BGMPlayer.class.getProtectionDomain().getCodeSource().getLocation().getPath());

		if(jarFile.isFile()){
			
			try {
				JarFile jar = new JarFile(jarFile);
				final Enumeration<JarEntry> entries = jar.entries();
				
				while(entries.hasMoreElements()){
					final String fullPath = entries.nextElement().getName();
					
					if(fullPath.startsWith(path + "/boss/")){
						
						String name = fullPath.substring((path + "/").length());
						
						if(!(name.length() - FILE_EXTENSION.length() <= 0)){
							
							allBGMNamesList.add(fullPath);
							
							
						}
						
						
					}
					
					else if(fullPath.startsWith(path + "/")){
						
						String name = fullPath.substring((path + "/").length());
						
						if(!(name.length() - FILE_EXTENSION.length() <= 0)){
							
							allBGMNamesList.add(fullPath);
							
							
						}
						
					}		
				}	
				
				jar.close();
				String[] allBGMNames = allBGMNamesList.toArray(new String[allBGMNamesList.size()]);
				
				
				return allBGMNames;
				
			} catch (IOException e) {
			
				e.printStackTrace();
			}
	
		}else{
			final URL url = BGMPlayer.class.getResource("/" + path);
			
			if(url != null){
				try{	
					final File apps = new File(url.toURI());
					
					for(File app : apps.listFiles()){
						
						if(app.getName().equals("boss")){
							
							path = "music/boss";
							
							final URL thatUrl = BGMPlayer.class.getResource("/" + path);
							
							if(thatUrl != null){
								
								try{	
									final File thoseApps = new File(thatUrl.toURI());
									
									for(File thatApp : thoseApps.listFiles()){
										
										allBGMNamesList.add(path + "/" + thatApp.getName());

									}
									
								}catch(URISyntaxException ed){
									
								}
							}
							
							
							
						}else{
						
							allBGMNamesList.add("music" + "/" + app.getName());
						
						}

					}
					
					String[] allBGMNames = allBGMNamesList.toArray(new String[allBGMNamesList.size()]);
					
					return allBGMNames;
			
				}catch(URISyntaxException ex){
				
					ex.printStackTrace();
					
				}

			}
		
		}
		return null;
	
	}
	
	public static String getMusicFile(String path){
			
		return path.substring("music/".length());
	
			
	}

	public void setLastMusicInWorld(Clip clip, World world) {
		
		if(worlds == null && clips == null){
			
			worlds = new World[handler.getWorldList().size()];
			clips = new Clip[handler.getWorldList().size()];
			
		}
		
		if(world instanceof MusicWorld){
			
			int count = 0;
			
			Iterator <World> worldList = handler.getWorldList().iterator();
			
			while(worldList.hasNext()){
				
				World w = worldList.next();
				
				if(w.equals(world)){
					worlds[count] = world;
					clips[count] = clip;
					
					
				}
				
			
				
				count += 1;
				
			}
			
		}

		
	}
	
	public World[] getVisitedMusicWorlds(){
		
		return worlds;
		
	}
	
	public Clip[] getVisitedMusicWorldsClip(){
		
		return clips;
		
	}
		
	
		
	
}
	
	


