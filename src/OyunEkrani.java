
import java.awt.HeadlessException;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sinem
 */
public class OyunEkrani extends JFrame {

    public OyunEkrani(String title) throws HeadlessException { //constructordan string tittle şeklinde JFrame oluşturulur. oyun ekranı şeklinde JFrame oluşturuldu
        super(title);
    }
    
    public static void main (String[] args){
        
        OyunEkrani ekran = new OyunEkrani("Uzay Oyunu");
        ekran.setResizable(false); //false olma sebebi bütün işlemlerin,tuşa bastığımızda j paneli üzerinde olmasını istiyoruz. JFrame değil direkt j paneline odaklanacak
        ekran.setFocusable(false);
        
        ekran.setSize(800,600);
        
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyun oyun = new Oyun(); //JPanelden bir obje üretiyoruz
        
        oyun.requestFocus(true); // JPanelin klavye işlemlerimizi anlaması için requestFocus : klavyeden işlemlerimizi anlaması için direkt bana odağı ver diyor
        
        oyun.addKeyListener(oyun); //Keylistener şeklinde interface implemente etmemiz gerekiyor. İnterfacemiz klavye işlemlerini anlamayı sağlayacak. KeyListener klavyeden işlemleri almamızı sağlıyor
        
        oyun.setFocusable(true); //artık odağımız JPanelde olduğu için true olur.
        oyun.setFocusTraversalPolicyProvider(false); //klavye işlemleri direkt gerçekleşebilsin diye false olur.
        
        ekran.add(oyun); //JPameli ekrana ekledik
        
        ekran.setVisible(true); //JFrame JPanel eklendiğinde direkt oluşssun
        
        
        
    }
}
