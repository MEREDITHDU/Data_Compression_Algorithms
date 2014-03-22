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
public class golomb_decoder {
    
    public static void main(String args[])
    {
        int a,a1,k,d,m=64,count,j,d1;
        String s,s1,s2,s3,s4,s5;
        char ch;
        
        try {
                    File f1=new File("golomb_compressed.txt");
                    RandomAccessFile rand=new RandomAccessFile(f1, "r");
                    File f=new File("golomb_interim1.txt");
                    if(f.exists())
                        f.delete();
                    f.createNewFile();
                    FileOutputStream fos=new FileOutputStream(f);
                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
                    while(rand.getFilePointer()<rand.length())
                    {
                        a=rand.read();
                        s=Integer.toBinaryString(a);
                            if(a>127)
                            {
                               a1=rand.read();
                               s1=Integer.toBinaryString(a1);
                               s2=s1.substring(2);
                               s3=s.substring(3);
                               s=s3.concat(s2);
                               k=Integer.parseInt(s, 2);
                               s=Integer.toBinaryString(k);
                            }
                            else
                            {
                                d=Integer.numberOfLeadingZeros(a)-24;
                                if(a==0)
                                    d--;
                                for(int i=0;i<d;i++)
                                {
                                    s="0"+s;
                                }
                            }
                            bw.write(s);
                    }
                    bw.close();
                    rand.close();
                    
                    File f2=new File("golomb_decoded.txt");
                    if(f2.exists())
                        f2.delete();
                    f2.createNewFile();
                    FileOutputStream fos1=new FileOutputStream(f2);
                    BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(fos1));
                    RandomAccessFile rand1=new RandomAccessFile(f, "r");
                    
                    while(rand1.getFilePointer()<rand1.length())
                    {
                        a=rand1.read();
                        if(a==49)
                        {
                            count=0;
                            while(a==49)
                            {
                                count++;
                                a=rand1.read();
                            }
                            s="";
                            while(s.length()<7)
                            {
                                a=rand1.read();
                                ch=(char)a;
                                s+=String.valueOf(ch);
                            }
                            j=Integer.parseInt(s,2);
                            d1=count*m+j;
                            System.out.println(d1);
                            bw1.write(d1);
                        }
                        else
                        {
                            count=0;
                            s="";
                            if((rand1.length()-rand1.getFilePointer())<7)
                                break;
                            while(s.length()<7)
                            {
                                a=rand1.read();
                                ch=(char)a;
                                s+=String.valueOf(ch);
                            }
                            j=Integer.parseInt(s,2);
                            bw1.write(j);
                        }
                    }
                    bw1.close();
                    rand1.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(golomb_decoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(golomb_decoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
