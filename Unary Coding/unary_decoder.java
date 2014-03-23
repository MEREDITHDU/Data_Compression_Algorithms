/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aniruddh
 */
public class unary_decoder {
    
    public static void main(String args[])
    {
        char a;
        int count=0;
        try {
            File f=new File("unary_output.txt");
            RandomAccessFile rand=new RandomAccessFile(f, "r");
                File f1=new File("unary_decoded.txt");
                if(f1.exists())
                    f1.delete();
                f1.createNewFile();
                FileOutputStream fos=new FileOutputStream(f1);
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
                
                while(rand.getFilePointer()<rand.length())
                {
                    while((char)rand.read()=='1')
                    {
                        count++;
                    }
                    bw.write(count);
                    count=0;
                    
                }
                bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(unary_decoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(unary_decoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
