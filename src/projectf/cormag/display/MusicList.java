package projectf.cormag.display;

 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projectf.cormag.main.Game;
import projectf.cormag.sound.BGMPlayer;
 
public class MusicList extends JPanel
                      implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private JList<String> list;
    private DefaultListModel<String> listModel;
 
    private static final String playString = "Play";
    private JButton playButton;
    private String clickedSoundtrack;
 
    public MusicList() { 
    	
        listModel = new DefaultListModel<String>();
        
        for(int i = 0; i < BGMPlayer.getAllBGMs().length; i++){
        	
        	listModel.addElement(BGMPlayer.getAllBGMs()[i]);
        	
        }

        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        list.setFixedCellWidth(200);
        list.setFocusable(false);
        
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setFocusable(false);
 
        playButton = new JButton(playString);
        playButton.setFocusable(false);
        playButton.setActionCommand(playString);
        playButton.addActionListener(new PlayListener());

        add(listScrollPane, BorderLayout.CENTER);
        add(playButton, BorderLayout.PAGE_END);
        
        this.setBackground(Color.WHITE);
        
        this.setBounds(50, Game.HEIGHT - 200 - 75 , 285, 200);
    }
    
    public String getClickedSoundtrack(){
    	
    	return clickedSoundtrack;
    	
    }
    
 
    class PlayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int index = list.getSelectedIndex();

            int size = listModel.getSize();
 
            if (size == 0) {
                playButton.setEnabled(false);
 
            } else { 
 
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
                clickedSoundtrack = list.getSelectedValue() + BGMPlayer.FILE_EXTENSION;


                
            }
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
 
            if (list.getSelectedIndex() == -1) {
                playButton.setEnabled(false);
 
            } else {
                playButton.setEnabled(true);
                
            }
        }
    }
}


