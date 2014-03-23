/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author aniruddh
 */
public class Jpeg {
    
    public static void main(String args[])
    {
        for(int i=0;i<100;i++)
        {
        try {
            
            File f=new File("m.jpg");
            
            BufferedImage img=ImageIO.read(f);
            if(img==null)
            {
                System.out.println("fff");
            }
            ImageIO.write(img, "jpg", f);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Jpeg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Jpeg.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}
