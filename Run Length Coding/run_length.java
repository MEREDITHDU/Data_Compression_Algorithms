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
public class run_length {
    
    public static void main(String args[])
    {
        int a;
        try {
            File f=new File("run_length_input.txt");
            RandomAccessFile rand=new RandomAccessFile(f, "r");
                File f1=new File("run_length_interm.txt");
                if(f1.exists())
                    f1.delete();
                f1.createNewFile();
                FileOutputStream fos=new FileOutputStream(f1);
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
                String s;
                while(rand.getFilePointer()<rand.length())
                {
                    a=rand.read();
                    s=Integer.toBinaryString(a);
                    int r=Integer.numberOfLeadingZeros(a)-24;
                    for(int i=0;i<r;i++)
                    {
                        s="0"+s;
                    }
                    bw.write(s);
                }
                bw.close();
                rand.close();
                
                File f2=new File("run_length_output.txt");
                FileOutputStream fos1=new FileOutputStream(f2);
                if(f2.exists())
                {
                    f2.delete();
                }
                f2.createNewFile();
                BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(fos1));
                RandomAccessFile rand1=new RandomAccessFile(f1,"r");
                char d;
                d=(char)rand1.read();
                bw1.write((int)d);
                rand1.seek(rand1.getFilePointer()-1);
                while(rand1.getFilePointer()<rand1.length())
                {
                    a=rand1.read();
                    int count=1;
                    while(rand1.read()==a)
                    {
                        count++;
                    }
                    count+=48;
                    bw1.write(count);
                    if(rand1.getFilePointer()==rand1.length())
                    {
                        rand1.seek(rand1.getFilePointer()-2);
                        if(rand1.read()!=rand1.read())
                        {
                            bw1.write(49);
                        }
                    }
                    else
                    {
                        rand1.seek(rand1.getFilePointer()-1);
                    }
                }
                rand1.close();
                bw1.close();
                f1.delete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(run_length.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(run_length.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
