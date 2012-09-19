package model;

import java.util.Hashtable;

public class SwatchSheet {
	Hashtable<String, Swatch> swatches;
	
    public SwatchSheet(){
    	swatches = new Hashtable<String, Swatch>();
    }
    
    public void addSwatch(Swatch s){
    	swatches.put(s.getUid(), s);
    }
    
    public Swatch getSwatch(String uid){
    	return swatches.get(uid);
    }
    
}
