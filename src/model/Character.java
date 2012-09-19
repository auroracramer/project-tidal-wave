package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;




public class Character implements CellElement{
	
	// Sprite animation attributes
    private String name;
    private SpriteSheetReader sprites;
    private Sprite currentSprite;
    private float x;
    private float y;
    
    private SpriteSheetReader.SpriteDir lastDir;
    private boolean moving;
    
    // Overworld attributes
    
    MapCell currCell;
    

    
    public Character(String name){
        this.name = name;
        this.sprites = new SpriteSheetReader(name);
        this.lastDir = SpriteSheetReader.SpriteDir.DOWN;
        this.moving = false;
        this.x = 0;
        this.y = 0;
    }
    
    // ACCESSORS
    
    public String getName(){
        return this.name;
    }    
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
 
    public MapCell getCell(){
    	return this.currCell;
    }
    
    public void setCell(MapCell c){
    	this.currCell = c;
    }
    
    // ANIMATIONS
    
    public Sprite stopWalk(){
        if(currentSprite != null){
            this.x = currentSprite.getX();
            this.y = currentSprite.getY();
        }

        currentSprite = sprites.getStillSprite(this.x, this.y, lastDir);

        currentSprite.setVelocityX(0);
        currentSprite.setVelocityY(0);
        this.moving = false;
        return currentSprite;
    }
    
    // FIX ANIMATION LOCKING UP ISSUE when changing directions without letting go of a button
    
    public Sprite walkUp(){
    	return walk(SpriteSheetReader.SpriteDir.UP, 0.0f, -0.075f);
    }
    
    public Sprite walkDown(){
    	return walk(SpriteSheetReader.SpriteDir.DOWN, 0.0f, 0.075f);
    }
    
    public Sprite walkLeft(){
    	return walk(SpriteSheetReader.SpriteDir.LEFT, -0.075f, 0.0f);
    }
    
    public Sprite walkRight(){
        return walk(SpriteSheetReader.SpriteDir.RIGHT, 0.075f, 0.0f);
    }
    
    public Sprite walk(SpriteSheetReader.SpriteDir d, float vx, float vy){
        if(currentSprite != null){
            this.x = currentSprite.getX();
            this.y = currentSprite.getY();
        }
        if(lastDir != d || !moving){
            lastDir = d;
            currentSprite = sprites.getWalkingSprite(this.x, this.y, d);
            currentSprite.setVelocityX(vx);
            currentSprite.setVelocityY(vy);
        }
        this.moving = true;
        return currentSprite;
    }
    


    

}
