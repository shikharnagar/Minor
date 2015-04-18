/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Code;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class Main extends Applet implements Runnable,MouseListener {
    
    Ball b;
    private Image i;
    private Graphics doubleG;
    private int score=0;
    private int gameTime=0;
    private boolean gameOver=false;
    private AudioClip bgMusic,hitMusic, missMusic; 
    private URL url;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    
    

    @Override
    public void init() {
        setSize(800, 600);
        addMouseListener(this);
        url=getDocumentBase();
        hitMusic = getAudioClip(url, "hit.au");
        missMusic = getAudioClip(url, "miss.au");
        bgMusic = getAudioClip(url, "bgmusic.au");
    }

    @Override
    public void start() {
        
        b=new Ball(10,10);
                        
        Thread t=new Thread(this);
        t.start();
        
        bgMusic.loop();
        
    }
    
    @Override
    public void run() {
        
        while(true){
            
            
            b.update(this);
            repaint();
            
            try {
                Thread.sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            gameTime++;
            if(gameTime==1500){
                gameOver=true;
            }
            if(gameTime>200 && gameTime<400){
                b.setBonus(true);
            }else{
                b.setBonus(false);
            }
             
        }
    }



    
    
    @Override
    public void paint(Graphics g) {
        b.paint(g);
        String s = Integer.toString(score);
        g.setColor(Color.red);
        g.setFont(new Font("Arial",Font.BOLD,28));
        g.drawString("Score", 700, 20);
        g.drawString(s, 700, 50);
        if(gameOver==true){
            g.drawString("Game Over!!! Bitch!!", (this.getWidth()/2)-130, (this.getHeight()/2)-100);
            g.drawString("Your Score", (this.getWidth()/2)-80, (this.getHeight()/2)-50);
            g.drawString(s, this.getWidth()/2, this.getHeight()/2);
            b.setDx(0);
            b.setDy(0);
            b.setDiameter(0);
           
        
        }
        
    }
    
    @Override
    public void update(Graphics g) {
       // for double buffering
        if(i==null)
        {
            i= createImage(this.getWidth(), this.getHeight());
            doubleG = i.getGraphics();
        }
        doubleG.setColor(getBackground());
        doubleG.fillRect(0, 0, this.getWidth(),this.getHeight());
        
        doubleG.setColor(getForeground());
        paint(doubleG);
        
        g.drawImage(i, 0, 0, this);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        int ballX = b.getX();
        int ballY = b.getY();
        int ballD = b.getDiameter();
        
        if(gameOver==false){
            if(e.getX() > ballX && e.getX() < ballX+ballD){
                if(e.getY() > ballY && e.getY() < ballY+ballD){
                    Random r = new Random();
                    b.setX(r.nextInt(400));
                    b.setY(r.nextInt(400));
                    score++;
                    if(b.isBonus()==true){
                        score+=2;
                    }
                    hitMusic.play();
                }
        
            }
            else{
                score--;
                missMusic.play();
            }
        }
        
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    
    }

    @Override
    public void mouseExited(MouseEvent me) {
    
    }

    
    
    
    
}
