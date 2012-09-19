package model;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;




public class SpriteSheetReader {
    private Image spriteSheet;
    public enum SpriteDir {LEFT, RIGHT, UP, DOWN};

    // Overworld //
    
    private static final Point OVERWORLD_START = new Point(365,452);
    private static final Rectangle OVERWORLD_DIM = new Rectangle(14,16);
    private static final Rectangle OVERWORLD_SPACE = new Rectangle(6,4);
    
    private static final Point WALKING_DOWN_POS = new Point(0,0);
    private static final Point WALKING_UP_POS = new Point(2,0);
    private static final Point WALKING_LEFT_POS = new Point(4,0);
    private static final Point WALKING_RIGHT_POS = new Point(4,0);
    private static final Point STILL_UP_POS = new Point(3,1);
    private static final Point STILL_DOWN_POS = new Point(0,1);
    private static final Point STILL_LEFT_POS = new Point(4,0);
    private static final Point STILL_RIGHT_POS = new Point(4,0);
    
    public SpriteSheetReader(String name){
    	this.spriteSheet = getSpriteSheet(name);
    }
    
    public Image getSpriteSheet(String name){
        if(this.spriteSheet == null){
            try{
                //return ImageIO.read(new File(name.toLowerCase() + "_spritesheet.png");
                return ImageIO.read(new File("bartz.png"));
            } catch (IOException ex){
                return null;
            }
        }
        else{
            return this.spriteSheet;
        }
    }
    
    public Sprite getWalkingSprite(float x, float y, SpriteDir d){
    	Point startPoint;
    	switch(d){
    	case UP:
    		startPoint = WALKING_UP_POS;
            break;
    	case DOWN:
    		startPoint = WALKING_DOWN_POS;
    		break;
    	case LEFT:
    	    startPoint = WALKING_LEFT_POS;
    	    break;
    	case RIGHT:
    		startPoint = WALKING_RIGHT_POS;
    		break;
        default:
        	return null;
    	}
    	return new Sprite(getAnimation(OVERWORLD_START, OVERWORLD_DIM, OVERWORLD_SPACE, startPoint, 2), x, y);
    }
    
    public Sprite getStillSprite(float x, float y, SpriteDir d){
    	Point startPoint;
    	switch(d){
    	case UP:
    		startPoint = STILL_UP_POS;
            break;
    	case DOWN:
    		startPoint = STILL_DOWN_POS;
    		break;
    	case LEFT:
    	    startPoint = STILL_LEFT_POS;
    	    break;
    	case RIGHT:
    		startPoint = STILL_RIGHT_POS;
    		break;
        default:
        	return null;
    	}
    	return new Sprite(getAnimation(OVERWORLD_START, OVERWORLD_DIM, OVERWORLD_SPACE, startPoint, 1), x, y); 
    }
    
    
    private Animation getAnimation(Point startingPoint, Rectangle size, Rectangle spacing, Point cellLocation, int cols, int rows, boolean reversed){
        Animation a = new Animation();
        Image[] sprites = new Image[cols * rows];

        
        for (int i = cellLocation.y; i < cellLocation.y + rows; i++){
            for (int j = cellLocation.x; j < cellLocation.x + cols; j++){
                sprites[((i - cellLocation.y) * cols) + j - cellLocation.x] = ((BufferedImage) spriteSheet).getSubimage(
                    startingPoint.x + j * (size.width + spacing.width),
                    startingPoint.y + i * (size.height + spacing.height),
                    size.width,
                    size.height
                );
            }
        }
        
        if(reversed){
            for (int i = cellLocation.y + rows; i >= cellLocation.y; i++){
                for (int j = cellLocation.x + cols; j >= cellLocation.x; j--){
                    sprites[((i - cellLocation.y) * cols) + j - cellLocation.x] = ((BufferedImage) spriteSheet).getSubimage(
                        startingPoint.x + j * (size.width + spacing.width),
                        startingPoint.y + i * (size.height + spacing.height),
                        size.width,
                        size.height
                    );
                }
            }
        }
        for (int i = 0; i < sprites.length; i++){
            a.addScene(sprites[i], 250);
        }
        
        return a;

    }
    private Animation getAnimation(Point startingPoint, Rectangle size, Rectangle spacing, Point cellLocation, int cols){
        return getAnimation(startingPoint, size, spacing, cellLocation, cols, 1, false);
    }
}
