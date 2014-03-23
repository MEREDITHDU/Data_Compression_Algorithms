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
public class run_length_decoder {
    
    public static void main(String args[])
    {
        try {
                File f=new File("run_length_output.txt");
                RandomAccessFile rand=new RandomAccessFile(f, "r");
                File f1=new File("run_length_interim1.txt");
                if(f1.exists())
                    f1.delete();
                f1.createNewFile();
                FileOutputStream fos=new FileOutputStream(f1);
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
                int a=rand.read();
                int b=5,c;
                int temp=a-48;
                if(temp==0)
                {
                    b=49;
                }
                else if(temp==1)
                {
                    b=48;
                }
                while(rand.getFilePointer()<rand.length())
                {
                    c=rand.read()-48;
                    System.out.println(c);
                    for(int i=0;i<c;i++)
                    {
                        bw.write(a);
                    }
                    c=rand.read()-48;
                    for(int i=0;i<c;i++)
                    {
                        bw.write(b);
                    }
                }
                rand.close();
                bw.close();
                File f2=new File("run_length_decoded.txt");
                if(f2.exists())
                    f2.delete();
                f2.createNewFile();
                FileOutputStream fos1=new FileOutputStream(f2);
                BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(fos1));
                RandomAccessFile rand1=new RandomAccessFile(f1, "r");
                String s="";
                while(rand1.getFilePointer()<rand1.length())
                {
                    for(int i=0;i<8;i++)
                    {
                        a=rand1.read()-48;
                        s+=a;
                    }
                    b=Integer.parseInt(s, 2);
                    bw1.write(b);
                    s="";
                }
                rand1.close();
                bw1.close();
                f1.delete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(run_length_decoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(run_length_decoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
