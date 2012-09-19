package model;
/*
 *  Contains all of the accessible map of an entire area.
 */


import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Map implements VisualElement {
    private MapSection[][] mapSections;
    private int mapWidth;
    private int mapHeight;
    private MapSection currSection;
    private int cellLength;
    
    // The section length of the square that each section of the map
    // will be divided into
    private int sectionLength;
    
    public Map(Rectangle r, int sectionLength){
    	this.mapWidth = r.width;
    	this.mapHeight = r.height;
    	this.sectionLength = sectionLength;
    	mapSections = new MapSection[getNumVertSections()][getNumHorizSections()];
    	initializeSections();
    }
    
    public void initializeSections(){
    	for(int j = 0; j < getNumHorizSections(); j++){
    		for(int i = 0; i < getNumVertSections(); i++){
        		this.mapSections[i][j] = new MapSection(i,j,this.sectionLength);
        	}
    	}
    }
    
    public void initializeSectionNeighbors(){
    	for(int j = 0; j < getNumHorizSections(); j++){
    		for(int i = 0; i < getNumVertSections(); i++){
        		MapSection[][] neighbors = this.mapSections[i][j].getNeighbors();
        		for(int l = 0; l < 3; l++){
        			for(int k = 0; k < 3; k++){
        				if(k == 1 && l == 1){
        					continue;
        				}
        				if((l == 0 && j == 0) || (l == 2 && j == getNumHorizSections() - 1)){
        					continue;
        				}
        				if((k == 0 && i == 0) || (k == 2 && i == getNumVertSections() - 1)){
        					continue;
        				}
        				neighbors[k][l] = this.mapSections[i + k - 1][j + l - 1];
        			}
        		}
        	}
    	}
    }
    
    public void setCellLength(int length){
    	this.cellLength = length;
    }
    
    public MapSection[][] getMapSections(){
    	return this.mapSections;
    }
    
    public int getMapWidth(){
    	return this.mapWidth;
    }
    
    public int getMapHeight(){
    	return this.mapHeight;
    }
    
    public void setMapSections(MapSection[][] mapSections){
    	this.mapSections = mapSections;
    }
    
    
    public int getNumVertSections(){
    	return (int) Math.ceil(mapWidth/((double)sectionLength));
    }
    
    public int getNumHorizSections(){
    	return (int) Math.ceil(mapHeight/((double)sectionLength));
    }
    
    
    public MapSection getCurrSection(){
    	return mapSections[0][0];
    	//return currSection;
    }
    
    public int getCellLength(){
    	return (int) mapSections[0][0].getCells()[0][0].getCellLength();
    }
    
    public MapCell getCell(int i, int j){
    	if(i < 0 || j < 0 || i >= getMapWidth() || j >= getMapHeight()){
    		return null;
    	}
    	return mapSections[i/sectionLength][j/sectionLength].getCells()[i % sectionLength][j % sectionLength];
    }
    
    public MapCell getCell(float x, float y){
    	return getCell(((int) x)/(cellLength), ((int) y)/(cellLength));
    	
    }

    
}
