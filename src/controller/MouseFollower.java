package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.Followable;
import model.MapCell;

public class MouseFollower implements MouseMotionListener, Followable {
    
	float x;
	float y;
	
	public MouseFollower(){
		x = 0;
		y = 0;
	}
	
	
	public float getX() {
		return x;
	}

	
	public float getY() {
		return y;
	}
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

}
