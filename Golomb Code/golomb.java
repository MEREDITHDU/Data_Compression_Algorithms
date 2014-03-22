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
public class golomb {
    
    public static void main(String args[])
    {
        int m=64,a,q,r,d;
        String s;
        char ch;
        try {
            File f1=new File("golomb_input.txt");
                RandomAccessFile rand=new RandomAccessFile(f1, "r");
                File f=new File("golomb_interim.txt");
                if(f.exists())
                    f.delete();
                f.createNewFile();
                FileOutputStream fos=new FileOutputStream(f);
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
                while(rand.getFilePointer()<rand.length())
                {
                    a=rand.read();
                    q=a/m;
                    r=a%m;
                    for(int i=0;i<q;i++)
                    {
                        bw.write(49);
                    }
                    bw.write(48);
                    bw.flush();
                    s=Integer.toBinaryString(r);
                    d=Integer.numberOfLeadingZeros(r)-25;
                    for(int i=0;i<d;i++)
                    {
                        s="0"+s;
                    }
                    bw.write(s);
                    bw.flush();
                }
                rand.close();
                
                File f2=new File("golomb_compressed.txt");
                if(f2.exists())
                    f2.delete();
                f2.createNewFile();
                FileOutputStream fos1=new FileOutputStream(f2);
                BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(fos1));
                RandomAccessFile rand1=new RandomAccessFile(f, "r");
                
                if((rand1.length()%8)!=0)
                {
                    while((rand1.length()%8)!=0)
                    {
                        bw.write(48);
                        bw.flush();
                    }
                }
                bw.close();
                
                while(rand1.getFilePointer()<rand1.length())
                {
                    s="";
                    while(s.length()<8)
                    {
                        a=rand1.read();
                        ch=(char)a;
                        s+=String.valueOf(ch);
                    }
                    d=Integer.parseInt(s, 2);
                    bw1.write(d);
                }
                bw1.close();
                rand1.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(golomb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(golomb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
