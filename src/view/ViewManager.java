package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import controller.ScreenManager;

import java.util.ArrayList;
import model.*;

public class ViewManager {
	private ArrayList<Renderer> renderers;
	
	private ModelManager models;
	
	private OverworldRenderer overworlds;
	private MapRenderer maps;
	private MenuRenderer menus;
	private SpriteRenderer sprites;
	private BattleRenderer battles;
	
	private Followable currentlyFollowing;
	
	private ScreenManager screen;
	
	private float offsetX;
	private float offsetY;
	
	public ViewManager(){
		this.overworlds = new OverworldRenderer();
		this.maps = new MapRenderer();
		this.menus = new MenuRenderer();
		this.sprites = new SpriteRenderer();
		this.battles = new BattleRenderer();
		
		renderers = new ArrayList<Renderer>();
		
		renderers.add(this.overworlds);
		renderers.add(this.maps);
		renderers.add(this.menus);
		renderers.add(this.sprites);
		renderers.add(this.battles);

	    this.offsetX = 0;
	    this.offsetY = 0;
		//new BufferedImage(null, null, false, null).;
	}
	
	public void render(Graphics2D g){
		setMap();
        //g.clipRect(0, 0, screen.getWidth()/2, screen.getHeight()/2);
		
		if(this.currentlyFollowing != null){
			
			MapCell currCell = this.maps.getMap().getCell(currentlyFollowing.getX(), currentlyFollowing.getY()); // The map starts at (0,0)
			if (currCell != null){
				//g.setColor(Color.BLACK);
				//g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
				g.clearRect(0, 0, screen.getWidth(), screen.getHeight());

				int xCoord = (int) (currCell.getX()/currCell.getCellLength());
				int yCoord = (int) (currCell.getY()/currCell.getCellLength());

				int xCellsOnScreen = screen.getWidth()/currCell.getCellLength();
				int yCellsOnScreen = screen.getHeight()/currCell.getCellLength();
				
				if(!(((xCoord - xCellsOnScreen/2 + 2) < 0 || this.maps.getMap().getCell(xCoord - xCellsOnScreen/2 + 2, yCoord) == null) ||
						(((xCoord + xCellsOnScreen/2 - 3) > this.maps.getMap().getMapWidth()) || (this.maps.getMap().getCell(xCoord + xCellsOnScreen/2 - 3, yCoord) == null)))){
					this.offsetX = - (this.currentlyFollowing.getX() - this.screen.getWidth()/2);
				}
						
			    if(!(((yCoord - yCellsOnScreen/2 + 2) < 0 || this.maps.getMap().getCell(xCoord, yCoord  - yCellsOnScreen/2 + 2) == null) ||
			    		(((yCoord + yCellsOnScreen/2 - 3) > this.maps.getMap().getMapHeight()) || (this.maps.getMap().getCell(xCoord, yCoord + yCellsOnScreen/2 - 3) == null)))){
			    	this.offsetY = -(this.currentlyFollowing.getY() - this.screen.getHeight()/2);
			    }
	            
				g.translate(this.offsetX, this.offsetY);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

		
		for(Renderer r : renderers){
			r.render((Graphics2D) g.create());
		}
	}
	
	public void setMap(){
		this.maps.setMap(this.models.getMap());
	}
	
	public void setModelManager(ModelManager m){
		this.models = m;
	}
	
	
	public void setFollow(Followable f){
		this.currentlyFollowing = f;
	}
	
	public Followable getFollow(){
		return this.currentlyFollowing;
	}
	
	public void setScreen(ScreenManager screen){
		this.screen = screen;
	}
}
