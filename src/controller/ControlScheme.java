package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class ControlScheme implements KeyListener{

	public abstract void keyPressed(KeyEvent event);
	
	public abstract void keyReleased(KeyEvent event);

	public void keyTyped(KeyEvent event) {
		event.consume();
	}

}
