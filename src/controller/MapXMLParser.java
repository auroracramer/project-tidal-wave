package controller;
import model.Map;
import model.MapCell;
import model.MapSection;
import model.Swatch;
import model.SwatchSheet;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.ArrayList;


import java.awt.Rectangle;
import java.io.File;

public class MapXMLParser extends XMLParser{
	private Map map;    
    
	public MapXMLParser(File file, SwatchSheet sheet) {
		super(file, sheet);
	}
    
    
    private int getSectionLength(){
    	return Integer.parseInt(doc.getDocumentElement().getAttribute("sectionLength"));
    }
    
    public Map getMap(){
    	return this.map;
    }
    

    protected void build(){
    	this.map = new Map(getDimensions(), getSectionLength());
    	MapSection[][] sections = this.map.getMapSections();
    	ArrayList<Element> rows = getRows();
    	for(int j = 0; j < this.map.getNumHorizSections(); j++){
    		for(int i = 0; i < this.map.getNumVertSections(); i++){
    			for(int l = j * getSectionLength(); l < (j + 1) * getSectionLength(); l++){
    				if(l >= rows.size()){
    					break;
    				}
    	    		Node row = rows.get(l);
    	    		ArrayList<Element> cells = getRowChildren(row.getChildNodes());
	        		for(int k = i * getSectionLength(); k < (i + 1) * getSectionLength(); k++){
	        			if(k >= cells.size()){
	        			    break;
	        			} 
        			    Node cell = cells.get(k);
	        			NamedNodeMap attrs = cell.getAttributes();
	        			Swatch s = this.sheet.getSwatch(attrs.getNamedItem("swatch").getNodeValue());
	        			sections[i][j].getCells()[mod(k, getSectionLength())][mod(l, getSectionLength())] = new MapCell(s, k*s.getCellLength(), l*s.getCellLength());
	        			this.map.setCellLength(s.getCellLength());
	        			
	        		}
    			}
        	}
    	}
    	
    	this.map.initializeSectionNeighbors();
    }
}
