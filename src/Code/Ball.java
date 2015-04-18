/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Code;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Student
 */
public class Ball {
    
    private int x;
    private int y;
    private int diameter=40;
    private int dx=10;
    private int dy=10;
    private boolean bonus=false;
    private boolean penalty=false;
    
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

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    public boolean isPenalty() {
        return penalty;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }
    
    
    
    public void update(Main m){
        x+=dx;
        y+=dy;
        if(x+diameter>m.getWidth()){
            dx=-dx;
        }
        if(x<0){
            dx=-dx;
        }
        if(y+diameter>m.getHeight()){
            dy=-dy;
        }
            
        if(y<0){
            dy=-dy;
        }
        
        
    }
    
    
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(x, y, diameter, diameter);
        if(bonus==true){
            g.setColor(Color.green);
            g.fillOval(x, y, diameter, diameter);
        }
    }
    
}
