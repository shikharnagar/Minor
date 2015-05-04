/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

/**
 *
 * @author Student
 */
public class Ball {
    
    private int x;
    private int y;
    private int diameter=40;
    private int dx=1;
    private int dy=1;
    
    private Image ballImage;
    private URL url;
    
    
    public Ball(int i,int j){        
        x=i;
        y=j;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    

     

    public void update(Main m){
        x+=dx;
        y+=dy;
        
        //width bounce
        if(x+diameter>m.getWidth()){
            dx=-dx;            
        }
        if(x<0){
            dx=-dx;            
        }
        //height bounce
        if(y+diameter>m.getHeight()){
            dy=-dy;          
        }
        if(y<0){
            dy=-dy;            
        }
        
        //speed increase
        if(m.isTime()==false && m.isInstruction()==false){
            if(m.getGameTime()%1200==0){
                if(dx<0){
                    dx-=3;
                }
                if(dy<0){
                    dy-=3;
                }
                if(dx>0){
                    dx+=3;
                }
                if(dy>0){
                    dy+=3;
                }     
            }
        }
        
        url=m.getDocumentBase();
        if(m.isGameOver()==true){
            if(m.getScore()<5 || m.getScore()<10){
                ballImage = m.getImage(url, "explosion.gif");
            }
            else{
                ballImage=m.getImage(url, "bomb.png");
            }
        }else{
            ballImage=m.getImage(url, "bomb.gif");
        }         
    }
    
    
    public void paint(Graphics g){
        g.drawImage(ballImage, x, y, null);       
    }
    
}
