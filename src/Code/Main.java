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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class Main extends Applet implements Runnable, MouseListener, KeyListener{
    
    private int x=0;
    private int y=0;
    private int dx=1;
    Ball b;
    private Image GameNameImage;
    private Image i, bgImage,menuImage;
    private Graphics doubleG;
    private int score=0;
    String s = Integer.toString(score);
    private int gameTime=0;
    private boolean gameOver=false;
    private AudioClip bgMusic,hitMusic, missMusic; 
    private URL url;
    private Image mainMenu;
    
    //menu screen
    private boolean menu=true;
    
    //story mode
    private int storyNarrationX=0;
    private int storyNarrationY=0;
    private int storyNarrationDx=1;
    private boolean storyMode=true;
    private boolean storyNarration=false;
    private boolean level1=false;
    private boolean level2=false;
    private boolean level3=false;
    private boolean level3Instruction=false;
    private Image storyNarrationImage, chiefImage, soldierImage;
    private Image dialogue;
    private Image level1bgImage, level2bgImage, level3bgImage;
    private Image level3InstructionImage;
    private AudioClip level1Music, level2Music, level3Music;
    
    
    private boolean time=true;
    private boolean practice=true;
    private boolean instruction=false;
    private Image storyImage, timeImage, practiceImage;
    private Image storyInstructionImage;
    private Image timeInstructionImage;
    
    private Image practicebgImage;    
    private AudioClip practiceMusic;
    

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

    public boolean isTime() {
        return time;
    }

    public void setTime(boolean time) {
        this.time = time;
    }

    public boolean isInstruction() {
        return instruction;
    }

    public void setInstruction(boolean instruction) {
        this.instruction = instruction;
    }
    
    
    
    
    

    @Override
    public void init() {
        setSize(800, 600);
        addMouseListener(this);
        addKeyListener(this);
        url=getDocumentBase();
        hitMusic = getAudioClip(url, "hit.au");
        missMusic = getAudioClip(url, "miss.au");
        bgMusic = getAudioClip(url, "level3Music.au");
        bgImage = getImage(url, "bgimg1.png");
        timeInstructionImage = getImage(url, "timeInstructionImage.jpg");
        mainMenu = getImage(url, "mainMenu.png");
        
        //MAIN MENU
        GameNameImage = getImage(url, "gameNameImage.png");
        menuImage = getImage(url, "menuImage.jpg");
        storyImage = getImage(url, "storyImage.png");
        timeImage = getImage(url, "timeImage.png");
        practiceImage = getImage(url, "practiceImage.png");
        
        //STORY MODE
        storyNarrationImage = getImage(url, "storyNarrationImage.jpg");
        chiefImage = getImage(url, "chief.png");
        soldierImage = getImage(url, "soldier.png");
        dialogue = getImage(url, "dialogue.gif");
        storyInstructionImage = getImage(url, "storyInstructionImage.jpg");
        level1bgImage = getImage(url, "level1.jpg");
        level2bgImage = getImage(url, "level2.jpg");
        level3InstructionImage = getImage(url, "level3Instruction.jpg");
        level3bgImage = getImage(url, "level3.jpg");
        level1Music = getAudioClip(url, "level1Music.au");
        level2Music = getAudioClip(url, "level2Music.au");
        level3Music = getAudioClip(url, "level3Music.au");
        
        
        //PRACTICE        
        practicebgImage = getImage(url, "practicebg.jpg");
        practiceMusic = getAudioClip(url, "practiceMusic.au");
        
        //CURSOR CHANGE
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
            
            if(menu==false && instruction==false){
                b.update(this);
                
            }
            //STORY MODE
            if(level1==true || level2==true){
                gameTime++;
                if(gameTime==1800){                    
                    gameOver=true;
                    gameTime=0;
                }
            }
            else if(level3==true){
                gameTime++;
                if(gameTime==3600){                    
                    gameOver=true;
                    gameTime=0;
                }
            }
            //TIME ATTACK
            if(time==false && instruction==false){
                gameTime++;
                if(gameTime==3600){
                    gameOver=true;
                }
            }
            //PRACTICE
            if(practice==false){
                gameTime++;
                if(gameTime==36000){
                    gameOver=true;
                }
            }
            
            x-=dx;
            if(x==-1248){
                dx=-dx;
            }
            else if(x>0){
                dx=-dx;
            }
            //story mode
            storyNarrationX-=storyNarrationDx;
            if(storyNarrationX==-1120){
                storyNarrationDx=-storyNarrationDx;
            }
            else if(storyNarrationX>0){
                storyNarrationDx=-storyNarrationDx;
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
            g.drawImage(menuImage, x, y, this);//main menu  
            g.drawImage(GameNameImage, getWidth()/2-200, 100, this);
            g.drawImage(storyImage, getWidth()/2-270, getHeight()/2, this);//story mode            
            g.drawImage(timeImage, getWidth()/2-70, getHeight()/2, this);//time attack            
            g.drawImage(practiceImage, getWidth()/2+130, getHeight()/2, this);//practice    
        }
        //STORY MODE
        else if(storyMode == false && storyNarration == true && instruction==false){
            g.drawImage(storyNarrationImage, storyNarrationX, storyNarrationY, this);
            g.drawImage(chiefImage, 0, 0, this);
            g.drawImage(soldierImage, 600, 300, this);
            g.drawImage(dialogue, 0, 0, this);
        }
        //STORY INSTRUCTION
        if(instruction==true && storyMode==false){
            g.drawImage(storyInstructionImage, 0, 0, this);
        }
        //LEVEL 1
        else if(storyMode == false && storyNarration == false && instruction==false && level1==true){            
            bgForLevels(g, level1bgImage);
            String s = Integer.toString(score);
            if(gameOver==true){
                g.setColor(Color.green);
                if(score>10){
                    g.drawString("You have cleared Level 1!!", (this.getWidth()/2)-180, (this.getHeight()/2)-100);
                    g.drawString("Press Space to play Level 2", this.getWidth()/2-180,getHeight()/2+50);
                    PlayerScore(g);
                    
                }else{
                    g.setColor(Color.red);
                    g.drawString("You have Failed this City!", (this.getWidth()/2)-150, (this.getHeight()/2)-100);                    
                    PlayerScore(g);
                    returnToMainMenu(g);
                }
               
            }
            
        }
        //LEVEL 2
        else if(storyMode == false && storyNarration == false && instruction==false && level2==true){            
            bgForLevels(g, level2bgImage);
            String s = Integer.toString(score);
            if(gameOver==true){ 
                g.setColor(Color.red);
                if(score>5){
                    g.drawString("You have cleared Level 2!!", (this.getWidth()/2)-180, (this.getHeight()/2)-100);
                    g.drawString("Press Space to play Level 3", this.getWidth()/2-180,getHeight()/2+50);
                    PlayerScore(g);
                    
                }else{
                    g.setColor(Color.red);
                    g.drawString("You have Failed this City!", (this.getWidth()/2)-150, (this.getHeight()/2)-100);
                    PlayerScore(g);
                    returnToMainMenu(g);
                }
            
            }
        }
        //LEVEL 3
        else if(level3Instruction==true){
            g.drawImage(level3InstructionImage, 0, 0, this);
        }
        else if(storyMode == false && storyNarration == false && instruction==false && level3Instruction==false && level3==true){
            bgForLevels(g, level3bgImage);
            String s = Integer.toString(score);
            if(gameOver==true){
                if(score>5){
                    g.setColor(Color.white);
                    g.drawString("You have Saved your Friend!!", (this.getWidth()/2)-180, (this.getHeight()/2)-100);
                    PlayerScore(g);
                    returnToMainMenu(g);
                }else{
                    g.drawString("You are not a good friend!!", (this.getWidth()/2)-180, (this.getHeight()/2)-100);
                    PlayerScore(g);
                    returnToMainMenu(g);
                }
            
            }
        }
        //TIME ATTACK
        else if(instruction==true && time==false){
            g.drawImage(timeInstructionImage, 0, 0, this);
        }
        else if(time==false && instruction==false){            
            bgForLevels(g, bgImage);            
            if(gameOver==true){
                g.setColor(Color.white);
                if(score<10){
                    g.drawString("You are not a good soldier!!", (this.getWidth()/2)-180, (this.getHeight()/2)-100);
                    returnToMainMenu(g);
                }
                else if(score<20){
                    g.drawString("You need to work hard", (this.getWidth()/2)-150, (this.getHeight()/2)-100);                
                    returnToMainMenu(g);
                }
                else if(score<30){
                    g.drawString("We can use soldiers like you!", (this.getWidth()/2)-180, (this.getHeight()/2)-100);
                    returnToMainMenu(g);
                }
                else if(score<40){
                    g.drawString("Are you a professional?", (this.getWidth()/2)-150, (this.getHeight()/2)-100);
                    returnToMainMenu(g);
                }
                else if(score<50){
                    g.drawString("You are hired!!", (this.getWidth()/2)-100, (this.getHeight()/2)-100);
                    returnToMainMenu(g);
                }                
                PlayerScore(g);
            }
        }
        //PRACTICE
        else if(practice==false && instruction==false){
            bgForLevels(g, practicebgImage);
            g.setFont(new Font("ARIAL",Font.BOLD,18));
            g.setColor(Color.WHITE);
            g.drawString("Press ESC to quit !", 5, 25);
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
               
        if(menu==true){
            if(e.getX() > getWidth()/2-270 && e.getX() < getWidth()/2-150){
                if(e.getY() > getHeight()/2 && e.getY() < getHeight()/2+50){
                    menu=false;
                    storyMode=false;               
                    storyNarration=true;
                }                       
            }
            else if(e.getX() > getWidth()/2-70 && e.getX() < getWidth()/2+80){
                if(e.getY() > getHeight()/2 && e.getY() < getHeight()/2+50){
                    menu=false;
                    time=false;
                    instruction=true;
                }                       
            }
            else if(e.getX() > getWidth()/2+130 && e.getX() < getWidth()/2+280){
                if(e.getY() > getHeight()/2 && e.getY() < getHeight()/2+50){
                    menu=false;
                    practice=false;
                    bgMusic.stop();
                    practiceMusic.loop();
                    b.setDx(4);
                    b.setDy(4);
                }                       
            }
        }        
               
        //story mode
        if(gameOver==false && storyMode==false && storyNarration==false && instruction==false){
            hitMissLogic(e);                
        }
         
        //time attack
        if(gameOver==false && time==false && instruction==false){
            hitMissLogic(e);
        }
        
        //PRACTICE
        if(gameOver==false && practice==false){
            hitMissLogic(e);
        }
        
        //RETURN TO MAIN MENU
        if(gameOver==true){
            if(e.getX() > getWidth()/2-100 && e.getX() < getWidth()/2+100){
                if(e.getY() > getHeight()/2+50 && e.getY() < getHeight()/2+100){
                    restart();
                }
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

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(storyNarration==true){
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                storyNarration=false;
                instruction=true;
            }
        }
        else if(instruction==true && storyMode==false){
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                instruction=false;
                level1=true;
                bgMusic.stop();
                level1Music.loop();
                b.setDx(4);
                b.setDy(4);
            }            
        }
        else if(level1==true && gameOver==true && storyMode==false && score>10){
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                level1=false;
                level2=true;
                gameOver=false;
                score=0;
                level1Music.stop();
                level2Music.loop();
                b.setDiameter(40);
                b.setX(10);
                b.setY(10);
                b.setDx(7);
                b.setDy(7);
            }            
        }
        else if(level2==true && gameOver==true && storyMode==false && score>5){
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                level2=false;
                level3Instruction=true;  
                level2Music.stop();
                level3Music.play();
            }        
        }
        else if(level3Instruction==true){
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                level3Instruction=false;
                level3=true;
                gameOver=false;
                score=0;                
                b.setDiameter(40);
                b.setX(10);
                b.setY(10);
                b.setDx(9);
                b.setDy(9);
            }
        }
        else if(practice==false || instruction==false || storyMode==false){
            if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                restart();
            }
        }
        else if(instruction==true){
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                instruction=false;
            }
        }
        
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    
    }
    
    public void hitMissLogic(MouseEvent e){
        int ballX = b.getX();
        int ballY = b.getY();
        int ballD = b.getDiameter();
        
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
    
    public void bgForLevels(Graphics g, Image i){
        g.drawImage(i, 0, 0, this);
        String s = Integer.toString(score);
        b.paint(g);        
        g.setColor(Color.red);
        g.setFont(new Font("Arial",Font.BOLD,28));
        g.drawString("Score", getWidth()-100, 20);
        g.drawString(s, getWidth()-100, 50);
    }
    
   public void PlayerScore(Graphics g){
        String s = Integer.toString(score);
        g.drawString("Your Score", (this.getWidth()/2)-80, (this.getHeight()/2)-50);
        g.drawString(s, this.getWidth()/2-20, this.getHeight()/2);
        b.setDx(0);
        b.setDy(0);
        b.setDiameter(0);
   } 
   
   public void returnToMainMenu(Graphics g){
       g.drawRect(getWidth()/2-100, getHeight()/2+50, 200, 50);
       g.drawString("Main Menu", getWidth()/2-70, getHeight()/2+80);
       //g.drawImage(mainMenu, getWidth()/2-100, getHeight()/2+50, this);
   }
   
   public void restart(){
        score=0;
        gameTime=0;
        gameOver=false;
        menu=true;
        storyMode=true;
        storyNarration=false;
        level1=false;
        level2=false;
        level3=false;
        level3Instruction=false;
        time=true;
        practice=true;
        instruction=false;
        practiceMusic.stop();
        level1Music.stop();
        level2Music.stop();
        level3Music.stop();
        
        b=new Ball(10,10);
        bgMusic.loop();
   }
   
   
}
