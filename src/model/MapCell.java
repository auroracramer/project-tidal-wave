package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


/*
 *  An individual cell representing a cell of the map. Can contain a character
 *  or interactable object.
 */


public class MapCell implements VisualElement{
    private CellElement occupant;
    
    // Swatch attributes
    private Swatch swatch;
    private float x;
    private float y;
    
    public MapCell(Swatch swatch, float x, float y){
    	this.swatch = swatch;
    	this.x = x;
    	this.y = y;
    }

    
    public Image getImage(){
    	return this.swatch.getImage();
    }
    
    public float getX(){
    	return x;
    }
    
    public float getY(){
    	return y;
    }
    
    public int getCellLength(){
    	return this.swatch.getCellLength();
    }
    
    
}
