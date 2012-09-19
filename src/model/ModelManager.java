package model;

import java.io.File;

import view.*;
import controller.MapXMLParser;

public class ModelManager {
    protected Map currMap;
	protected ViewManager views;
	protected SwatchSheet sheet;
    
	public ModelManager(){
	}

	
	public void makeMap(File file){
		this.currMap = new MapXMLParser(file, this.sheet).getMap();
	}
    
	public Map getMap(){
		return this.currMap;
	}
	
	public void setViewManager(ViewManager v){
		this.views = v;
	}
	
	public void setSwatchSheet(SwatchSheet sheet){
		this.sheet = sheet;
	}
	
	public void update(long timePassed){
		//this.currMap.update(timePassed);
	}
}
