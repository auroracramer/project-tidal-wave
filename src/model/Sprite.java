package model;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {
    private Animation a;
    private float x;
    private float y;
    private float vx;
    private float vy;
    
    public Sprite(Animation a, float x, float y){
        this.a = a;
        this.x = x;
        this.y = y;
    }
    
    public Sprite(Animation a){
        this.a = a;
    }
    

    
    public void update(long timePassed){
        x += vx * timePassed;
        y += vy * timePassed;
        a.update(timePassed);
    }
    
    public float getX(){
        return this.x;
    }
    
    public float getY(){
        return this.y;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public float getVelocityX(){
        return this.vx;
    }
    
    public float getVelocityY(){
        return this.vy;
    }
    
    public void setVelocityX(float vx){
        this.vx = vx;
    }
    
    public void setVelocityY(float vy){
        this.vy = vy;
    }
    
    public int getWidth(){
        return a.getImage().getWidth(null);
    }
    
    public int getHeight(){
        return a.getImage().getHeight(null);
    }
    
    public Image getImage(){
        return a.getImage();
    }
}
