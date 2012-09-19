package model;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Swatch {
    private String uid;
    private Image image;
    private boolean swatchUnder;
    private boolean solid;
    private int sideLength;
    
    public Swatch(String uid, Image image, String swatchUnder, String solid, int sideLength){
    	this.uid = uid;
    	this.sideLength = sideLength;
    	this.image = image;
    	
    	if(swatchUnder.equals("true")){
    		this.swatchUnder = true;
    	}else{
    		this.swatchUnder = false;
    	}
    	
    	if(solid.equals("true")){
    		this.solid = true;
    	}else{
    		this.solid = false;
    	}
    	
    }
    
    public String getUid(){
    	return this.uid;
    }
    
    
    public Image getImage(){
    	return this.image;
    }
    
    public boolean isUnder(){
    	return this.swatchUnder;
    }
    
    public boolean isSolid(){
    	return this.solid;
    }
    
    public int getCellLength(){
    	return this.sideLength;
    }
    
    
}
