package view;

import java.awt.*;
import model.*;

public class MapRenderer extends Renderer{
	private Map currMap;
	
    public MapRenderer(){
    	
    }

    
	@Override
	public void render(Graphics2D g) {
		Map map = getMap();
		MapSection section = map.getCurrSection();
		MapSection[][] neighbors = section.getNeighbors();
		
		// Render central section
		renderMapSection(g, section);
		
	    // Render neighbors
		for(int j = 0; j < 3; j++){
			for(int i = 0; i < 3; i++){
				MapSection currNeigh = neighbors[i][j];
				if(currNeigh != null){
					
					renderMapSection(g, currNeigh);
				}
			}
		}
			
		
	}
	
	public void renderMapSection(Graphics2D g, MapSection sect){
		MapCell[][] cells = sect.getCells();
		for(int j = 0; j < sect.getSectionLength(); j++){
			for(int i = 0; i < sect.getSectionLength(); i++){
				MapCell currCell = cells[i][j];
				if(currCell != null && currCell.getImage() != null){
				    g.drawImage(currCell.getImage(), Math.round(currCell.getX()), Math.round(currCell.getY()), null);
				}
			}
		}
	}
	
	public void setMap(Map map){
		this.currMap = map;
	}
	
	public Map getMap(){
		return this.currMap;
	}
}
