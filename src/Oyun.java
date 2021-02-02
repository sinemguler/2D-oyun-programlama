
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sinem
 */

class Ates{
    
    private int x;
    private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
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
    
}


public class Oyun extends JPanel implements KeyListener, ActionListener {
    
    Timer timer = new Timer(1,this);
    
    private int gecen_sure = 0;
    private int harcanan_ates = 0;
    
    private BufferedImage image; 
    
    private ArrayList<Ates> atesler = new ArrayList<Ates>(); 
    
    private int atesdirY =1;
    
    private int topX =0;
    
    private int topdirX = 2;
    
    private int uzayGemisiX =0;
    
    private int dirUzayX= 20;

    public boolean kontrolEt() {
    
        for (Ates ates : atesler){
            
            if (new Rectangle(ates.getX(), ates.getY(),10,20).intersects(new Rectangle(topX,0,20,20))){
                return true; 
            }
           
        }
        return false;
 }
    
    
    public Oyun() {
        
        try {
            image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.BLACK);
        
        timer.start();
     
    }

    @Override
    public void paint(Graphics g) { 
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        gecen_sure += 1;
        
        g.setColor(Color.red);
        
        g.fillOval(topX,0,20,20);
        
        g.drawImage(image,uzayGemisiX,490,image.getWidth() / 82, image.getHeight() / 82,this);
        
        for (Ates ates : atesler){
            if (ates.getY() < 0){
                
                atesler.remove(ates);
            }
            
        }
        
        g.setColor(Color.BLUE);
        
           for (Ates ates : atesler){
               
               g.fillRect(ates.getX(), ates.getY(),10,20);
           }
           
           if (kontrolEt()){
               timer.stop();
               String message = "Kazandınız...\n" +
                                "Harcanan Ateş : " + harcanan_ates +
                                "\nGeçen Süre : " + gecen_sure / 1000.0 + "saniye";
              JOptionPane.showMessageDialog(this, message);
              System.exit(0);
           }  
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
  
    
    
    //oyuna klavye ve hareket etme özeliği vermek için
    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) { //uzay gemisinin sağa ve sola hareket etmesi için
        
        int c = e.getKeyCode();
        
        if (c == KeyEvent.VK_LEFT){
            if (uzayGemisiX <=0 ){
                
                uzayGemisiX =0;
                
            }
            else {
                uzayGemisiX -= dirUzayX;
                
            }
            
        }
        else if (c == KeyEvent.VK_RIGHT){
            
            if (uzayGemisiX >= 750){
                
                uzayGemisiX = 750;
                
            }
            else {
                uzayGemisiX += dirUzayX;
            }
        }
        else if (c == KeyEvent.VK_CONTROL){
            atesler.add(new Ates(uzayGemisiX+15,470)); // uzay gemisinden ateş çıkacak
           
            harcanan_ates++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for (Ates ates : atesler){
            
            ates.setY(ates.getY() - atesdirY);
            
        }
        
        
        topX += topdirX;
        
        if (topX >= 750){
            
            topdirX = -topdirX;
         
        }
        
        if (topX <= 0){
            
            topdirX = -topdirX;
            
        }
       repaint();
    }
    
}
