import java.awt.*;
import javax.swing.*;
import controller.*;
import view.*;
import model.*;


public class TidesOfFate {
	    
	    private GameEngine engine;
	    
	    
	    public TidesOfFate(){
	    	engine = new GameEngine();
	    }
	    
	    public static void main (String[] args){
	    	TidesOfFate game = new TidesOfFate();
	    	game.run();
	    }
	   
	    
	    public void run(){
	        try{
	        	engine.init();
	            gameLoop();
	        }
	        finally{
	            engine.cleanUp();
	        }
	    }
	    
	    
	    //main game loop
	    public void gameLoop(){
	        long startTime = System.currentTimeMillis();
	        long cumTime = startTime;
	        
	        while(engine.isRunning()){
	            long timePassed = System.currentTimeMillis() - cumTime;
	            cumTime += timePassed;
	            
	            engine.onTick(timePassed);

	            try{
	                Thread.sleep(20);
	            }catch(Exception ex){}
	            
	        }
	    }

	    
}
