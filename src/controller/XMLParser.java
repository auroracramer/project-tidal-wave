package controller;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import model.SwatchSheet;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.ArrayList;


import java.awt.Rectangle;
import java.io.File;

public abstract class XMLParser {
	protected File file;
	protected Document doc;
	protected SwatchSheet sheet;
	
    public XMLParser(File file, SwatchSheet sheet){
    	this.file = file;
    	this.sheet = sheet;
    	try{
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	doc = dBuilder.parse(file.getAbsoluteFile());
			doc.getDocumentElement().normalize();
			build();
    	}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    protected String getRootAttribute(String attr){
    	return doc.getDocumentElement().getAttribute(attr);
    }
    
    
    
    protected Rectangle getDimensions(){
    	int width = Integer.parseInt(doc.getDocumentElement().getAttribute("width"));
    	int height = Integer.parseInt(doc.getDocumentElement().getAttribute("height"));
    	return new Rectangle(width, height);
    }
    
    protected int getCellLength(){
    	return Integer.parseInt(doc.getDocumentElement().getAttribute("cellLength"));
    }
    
    public ArrayList<Element> getRows(){
    	ArrayList<Element> rows = new ArrayList<Element>();
    	Node row = doc.getDocumentElement().getFirstChild();
    	while(row != null){
    		if(row.getNodeType() == Node.ELEMENT_NODE){
    		    rows.add((Element) row);
    		}
    		row = row.getNextSibling();
    	}
    	return rows;
    }
    
    public ArrayList<Element> getRowChildren(NodeList nodes){
    	ArrayList<Element> children = new ArrayList<Element>();
        
    	for(int i = 0; i < nodes.getLength(); i++){
    		if(nodes.item(i).getNodeType() == Node.ELEMENT_NODE){
    		    children.add((Element) nodes.item(i));
    		}
    	}
    	return children;
    }
    
    protected int mod(int n, int m){
    	return (n < 0) ? (m - (Math.abs(n) % m)) : (n % m);
    }
    
    protected abstract void build();
}
