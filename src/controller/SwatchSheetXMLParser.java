package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.Map;
import model.MapCell;
import model.MapSection;
import model.Swatch;
import model.SwatchSheet;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class SwatchSheetXMLParser extends XMLParser{
    BufferedImage image;
    
    public SwatchSheetXMLParser(File file, SwatchSheet sheet){
    	super(file, sheet);
    }

    protected void build(){
    	try{
    	    this.image = ImageIO.read(new File(this.file.getParent() + '/' + this.getRootAttribute("src")));
    	} catch (Exception ex){}
    	
    	ArrayList<Element> rows = getRows();
		for(int i = 0; i < rows.size(); i++){
			Element row = rows.get(i);
    		ArrayList<Element> cells = getRowChildren(row.getChildNodes());
    		for(int j = 0; j < cells.size(); j++){
    			Element cell = cells.get(j);
    			NamedNodeMap attrs = cell.getAttributes();
    			this.sheet.addSwatch(new Swatch(attrs.getNamedItem("uid").getNodeValue(), this.image.getSubimage(j * this.getCellLength(), i * this.getCellLength(), this.getCellLength(), this.getCellLength()),
    					attrs.getNamedItem("swatchUnder").getNodeValue(), attrs.getNamedItem("solid").getNodeValue(), this.getCellLength()));
    		}
		}
    }
    
    public SwatchSheet getSwatchSheet(){
    	return this.sheet;
    }

}
