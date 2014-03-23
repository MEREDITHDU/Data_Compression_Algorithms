/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aniruddh
 */
public class check {
    
    public static void main(String args[])
    {
        try {
            File f=new File("op_green_hunt_input.txt");
            File f1=new File("decompressed.txt");
            RandomAccessFile rand=new RandomAccessFile(f,"r");
            RandomAccessFile rand1=new RandomAccessFile(f1,"r");
            char a,b;
            while(rand.getFilePointer()<rand.length())
            {
                a=(char) rand.read();
                b=(char) rand1.read();
                if(a!=b)
                {
                    System.out.println(a+"\t"+rand.getFilePointer()+"\t"+b+"\t"+rand1.getFilePointer());
                }
            }
            System.out.println("success !!!!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(check.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(check.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
