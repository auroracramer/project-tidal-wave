package controller;

import java.awt.Point;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;


public class MouseLocker implements MouseMotionListener {

    private Robot robot;
    private Point center;
    private Window w;
    
    public MouseLocker(Window w) {
        this.w = w;
        center = new Point();
        try{
            robot = new Robot();
        } catch (Exception ex){}
        
        recenterMouse();

        w.addMouseMotionListener(this);
  
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        recenterMouse();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        recenterMouse();
    }
    
    private synchronized void recenterMouse(){
        if(robot != null && w.isShowing()){
            center.x = w.getWidth()/2;
            center.y = w.getHeight()/2;
            SwingUtilities.convertPointToScreen(center, w);
            robot.mouseMove(center.x, center.y);
        }
    }


}
