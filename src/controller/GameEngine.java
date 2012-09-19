package controller;
import java.awt.*;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import view.*;
import model.*;
import java.io.*;

public class GameEngine {
    private ScreenManager s;
    private ModelManager models;
    private ViewManager views;
    private boolean running;
    private ControlScheme controls;
    
    public GameEngine(){
    	running = true;
    	
    	models = new ModelManager();
    	models.setSwatchSheet(loadSwatchSheet());
    	models.makeMap(new File("resources/maps/testmap.xml"));
    	views = new ViewManager();
    	models.setViewManager(views);
    	views.setModelManager(models);
    	
    }
    
    
    // set to full screen
    public void init(){
        s = new ScreenManager();
        DisplayMode dm = s.findFirstCompatibleMode();
        s.setFullScreen(dm);
        
        Window w = s.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.BLACK);
        w.setForeground(Color.WHITE);
        w.setFocusTraversalKeysEnabled(false);
        
        this.controls = new MapControls(this, null);
        w.addKeyListener(this.controls);
        Followable mouse = new MouseFollower();
        w.addMouseMotionListener((MouseMotionListener) mouse);
        this.views.setScreen(s);
        this.views.setFollow(mouse);
        
    }
    
    public void stop(){
    	this.running = false;
    }
    
    public void onTick(long timePassed){
        this.models.update(timePassed);
        this.views.render(s.getGraphics());
        this.s.update();
    }
    
    
    public SwatchSheet loadSwatchSheet(){
    	SwatchSheet sheet = new SwatchSheet();
    	File directory = new File ("resources/swatchsheets");
    	for(File sheetfolder : directory.listFiles()){
    		if(sheetfolder.isDirectory()){
    			for(File file : sheetfolder.listFiles()){
    				if(file.getAbsolutePath().endsWith(".xml")){
    					new SwatchSheetXMLParser(file, sheet);
    				}
    			}
    		}
    	}
    	
    	return sheet;
    }
    
    public void cleanUp(){
    	s.restoreScreen();
    }
    
    public boolean isRunning(){
    	return this.running;
    }

    
}
