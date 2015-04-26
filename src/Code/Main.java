/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Code;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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
    private Image i, bgImage;
    private Graphics doubleG;
    private int score=0;
    private int gameTime=0;
    private boolean gameOver=false;
    private AudioClip bgMusic,hitMusic, missMusic; 
    private URL url;
    
    //menu screen
    private boolean menu=true;
    private boolean storyMode=true;
    private boolean time=true;
    private boolean practice=true;
    

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

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
    
    
    

    @Override
    public void init() {
        setSize(800, 600);
        addMouseListener(this);
        url=getDocumentBase();
        hitMusic = getAudioClip(url, "hit.au");
        missMusic = getAudioClip(url, "miss.au");
        bgMusic = getAudioClip(url, "bgmusic.au");
        bgImage = getImage(url, "bgimg1.png");
        
        //cursor change
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = getImage(url, "cursor.png");
        Cursor newCursor = toolkit.createCustomCursor(cursorImage , new Point(this.getX(),
        this.getY()), "cursorImage");
        this.setCursor(newCursor);
          
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
            
            if(menu==false){
                b.update(this);
                gameTime++;
                if(gameTime==3600){
                    gameOver=true;
                }
            }
            
            repaint();
            
            try {
                Thread.sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
             
        }
    }



    
    
    @Override
    public void paint(Graphics g) {
        if(storyMode==true && time==true && practice==true){
            g.drawRect(getWidth()/2-300, getHeight()/2, 100, 50);//story
            g.drawRect(getWidth()/2-100, getHeight()/2, 100, 50);//time
            g.drawRect(getWidth()/2+100, getHeight()/2, 100, 50);//practice
        }
        else if(time==false){
            g.drawImage(bgImage, 0, 0, this);
            b.paint(g);
            String s = Integer.toString(score);
            g.setColor(Color.red);
            g.setFont(new Font("Arial",Font.BOLD,28));
            g.drawString("Score", getWidth()-100, 20);
            g.drawString(s, getWidth()-100, 50);
            if(gameOver==true){
                g.drawString("Game Over!!! Bitch!!", (this.getWidth()/2)-130, (this.getHeight()/2)-100);
                g.drawString("Your Score", (this.getWidth()/2)-80, (this.getHeight()/2)-50);
                g.drawString(s, this.getWidth()/2, this.getHeight()/2);
                b.setDx(0);
                b.setDy(0);
                b.setDiameter(0);


            }
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
        
        if(e.getX() > getWidth()/2-300 && e.getX() < getWidth()/2-200){
            if(e.getY() > getHeight()/2 && e.getY() < getHeight()/2+50){
                menu=false;
                storyMode=false;
            
            }                       
        }
        else if(e.getX() > getWidth()/2-100 && e.getX() < getWidth()/2){
            if(e.getY() > getHeight()/2 && e.getY() < getHeight()/2+50){
                menu=false;
                time=false;
            
            }                       
        }
        else if(e.getX() > getWidth()/2+100 && e.getX() < getWidth()/2+200){
            if(e.getY() > getHeight()/2 && e.getY() < getHeight()/2+50){
                menu=false;
                practice=false;
            
            }                       
        }
        else if(gameOver==false && time==false){
                if(e.getX() > ballX && e.getX() < ballX+ballD){
                    if(e.getY() > ballY && e.getY() < ballY+ballD){
                        Random r = new Random();
                        b.setX(r.nextInt(400));
                        b.setY(r.nextInt(400));
                        score++;
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
