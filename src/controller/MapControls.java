package controller;

import java.awt.event.KeyEvent;

import model.Followable;
import model.Character;

public class MapControls extends ControlScheme {

	private GameEngine engine;
	private Followable currentlyFollowing;
	
	
	public MapControls(GameEngine e, Followable f){
		this.engine = e;
		this.currentlyFollowing = f;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
        case KeyEvent.VK_UP:
            ((Character) this.currentlyFollowing).walkUp();
            break;
        case KeyEvent.VK_DOWN:
            ((Character) this.currentlyFollowing).walkDown();
            break;
        case KeyEvent.VK_LEFT:
            ((Character) this.currentlyFollowing).walkLeft();
            break;
        case KeyEvent.VK_RIGHT:
            ((Character) this.currentlyFollowing).walkRight();
            break;
        case KeyEvent.VK_ESCAPE:
            engine.stop();
        }
        e.consume();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		((Character) this.currentlyFollowing).stopWalk();
        e.consume();
	}
	
	public Followable getFollow(){
		return this.currentlyFollowing;
	}
	
	public void setFollow(Followable f){
		this.currentlyFollowing = f;
	}

}
