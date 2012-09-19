package controller;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class ScreenManager {
    private GraphicsDevice vc;
    private MouseLocker mouseLock;
    
    private static final DisplayMode modes[] = {
        new DisplayMode(1920, 1080, 32, 0),
        new DisplayMode(1366, 768, 32, 0),
        new DisplayMode(1360, 768, 32, 0),
        new DisplayMode(1024, 768, 32, 0),
        new DisplayMode(1280, 800, 32, 0),
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(640, 480, 16, 0)
    };
    
    public ScreenManager(){
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        vc = e.getDefaultScreenDevice();
        //System.setProperty("sun.awt.noerasebackground", "true");
    }
    
    public DisplayMode[] getCompatibleDisplayMode(){
        return vc.getDisplayModes();
    }
    
    public DisplayMode findFirstCompatibleMode(){
        DisplayMode goodModes[] = vc.getDisplayModes();
        
	    for (int x = 0; x < modes.length; x++){
	        for(int y = 0; y < goodModes.length; y++){
	            if(displayModesMatch(modes[x], goodModes[y])){
	                return modes[x];
	            }
	        }
	    }
        
        return goodModes[0];
    }
    
    public DisplayMode getCurrentDisplayMode(){
        return vc.getDisplayMode();
    }
    
    public boolean displayModesMatch(DisplayMode m1, DisplayMode m2){
        if(m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()){
            return false;
        }
        if(m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()){
            return false;
        }
        if(m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()){
            return false;
        }

        return true;
    }
    
    public void setFullScreen(DisplayMode dm){
        System.setProperty("apple.awt.fullscreenhidecursor","true");
        JFrame f = new JFrame();
        f.setUndecorated(true);
        f.setIgnoreRepaint(true);
        f.setResizable(false);
        
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
            cursorImg, new Point(0,0), null);

        // Set the blank cursor to the JFrame.
        f.setCursor(blankCursor);
        
        vc.setFullScreenWindow(f);
        
        // Trap mouse in middle
        
        //mouseLock = new MouseLocker(this.getFullScreenWindow());
        

        
        if(dm != null && vc.isDisplayChangeSupported()){
            try{
                vc.setDisplayMode(dm);
            }
            catch(Exception ex){}
        }
        f.createBufferStrategy(2);
    }
    
    public Graphics2D getGraphics(){
        Window w = vc.getFullScreenWindow();
        if (w != null){
            BufferStrategy s = w.getBufferStrategy();
            return (Graphics2D) s.getDrawGraphics();
        }
        else{
            return null;
        }
    }
    
    
    public void update(){
        Window w = vc.getFullScreenWindow();
        if(w != null){
            BufferStrategy s = w.getBufferStrategy();
            if(!s.contentsLost()){
                s.show();
            }
        }
    }
    
    public Window getFullScreenWindow(){
        return vc.getFullScreenWindow();
    }
    
    public int getWidth(){
        Window w = vc.getFullScreenWindow();
        if (w != null){
            return w.getWidth();
        }
        else{
            return 0;
        }
    }
    public int getHeight(){
        Window w = vc.getFullScreenWindow();
        if (w != null){
            return w.getHeight();
        }
        else{
            return 0;
        }
    }
    
    public void restoreScreen(){
        Window w = vc.getFullScreenWindow();
        if(w != null){
            w.dispose();
        }
        vc.setFullScreenWindow(null);
    }
    
    public BufferedImage createCompatibleImage(int w, int h, int t){
        Window win = vc.getFullScreenWindow();
        if (win != null){
            GraphicsConfiguration gc = win.getGraphicsConfiguration();
            return gc.createCompatibleImage(w, h, t);
        }
        return null;
    }
}
