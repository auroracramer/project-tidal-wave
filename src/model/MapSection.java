package model;
import java.awt.Image;
import java.awt.image.BufferedImage;


/*
 *  A section of a larger map area. Always a square. 
 */

public class MapSection implements VisualElement{
    private MapSection[][] adjacentSections;
    private Character[] chars;
    private MapCell[][] cells;
    
    private int sectionLength;
    private int sectionWidth;
    private int sectionHeight;
    
    private int sectCoordX;
    private int sectCoordY;
    
    public MapSection(int sectCoordX, int sectCoordY, int sectionLength){
    	this.sectCoordX = sectCoordX;
    	this.sectCoordY = sectCoordY;
    	this.sectionLength = sectionLength;
    	cells = new MapCell[sectionLength][sectionLength];
    	adjacentSections = new MapSection[3][3];
    }
    
    public MapCell[][] getCells(){
    	return this.cells;
    }
    
    public void setCells(MapCell[][] cells){
    	this.cells = cells;
    }
    
    public MapSection[][] getNeighbors(){
    	return adjacentSections;
    }
    
    public int getSectionLength(){
    	return this.sectionLength;
    }
    
    public float getSectionSpan(){
    	return this.sectionLength * this.cells[0][0].getCellLength();
    }
    
    public float getX(){
    	return cells[0][0].getX();
    }
    
    public float getY(){
    	return cells[0][0].getY();
    }
    
    public int getSectCoordX(){
    	return this.sectCoordX;
    }
    
    public int getSectCoordY(){
    	return this.sectCoordY;
    }
}
