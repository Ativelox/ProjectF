package de.cormag.projectf.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import de.cormag.projectf.main.Game;

public class Display {

	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width, height;

	private Game game;
	private JLayeredPane layers;
	private MusicList musicList;	

	public Display(String title, int width, int height, Game game) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.game = game;
		
		createDisplay();
	}

	private void createDisplay() {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.setBounds(0, 0, width, height);
		
		musicList = new MusicList();
		musicList.setOpaque(false);
		musicList.setFocusable(false);
		musicList.setVisible(false);
		
		layers = new JLayeredPane();
		layers.setLayout(null);
		layers.setBounds(0, 0, width, height);
		
		layers.setLayer(canvas, new Integer(0));
		layers.setLayer(musicList, new Integer(2));
		
		layers.add(canvas);
		layers.add(musicList);

		frame.setContentPane(layers);

		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				game.stopGame();

			}

		});
	}
	
	public String getClickedSoundtrack(){
		
		return musicList.getClickedSoundtrack();
		
	}
	
	public JPanel getMusicList(){
		
		return musicList;
		
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public JLayeredPane getLayers(){
		return layers;
		
	}
	
	public int getWidth(){
		return frame.getWidth();
		
	}
	
	public int getHeight(){
		return frame.getHeight() - 30;
		
	}

}