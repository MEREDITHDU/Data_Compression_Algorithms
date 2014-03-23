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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aniruddh
 */
public class lzss_decoder {
    
    public static void main(String args[])
    {
        int size=31;
        try {
                File f=new File("final_output_lzss.txt");
                RandomAccessFile rand=new RandomAccessFile(f, "r");
                File f1=new File("lzss_decompressed.txt");
                if(f1.exists())
                    f1.delete();
                FileOutputStream fos=new FileOutputStream(f1);
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
                LinkedList<Character> l=new LinkedList<>();
                while(rand.getFilePointer()<rand.length())
                {
                    int c=rand.read();
                    int c1=-1,c2=-1;
                    String a=Integer.toBinaryString(c);
                    int temp=Integer.numberOfLeadingZeros(c);
                    temp=temp-24;
                    while(temp>0)
                    {
                        a="0"+a;
                        temp--;
                    }
                    
                    char d[]=a.toCharArray();
                    for(int i=0;i<8;i++)
                    {
                        if(rand.getFilePointer()<rand.length())
                        {
                            c1=rand.read();
                        }
                        else
                        {
                            break;
                        }
                        
                        if(d[i]=='1')
                        {
                            if(rand.getFilePointer()<rand.length())
                            {
                                c2=rand.read();
                            }
                            else
                            {
                                break;
                            }
                            
                            if(c2==1)
                            {
                                char p=l.get(l.size()-1);
                                for(int k=0;k<c2;k++)
                                {
                                    bw.write(p);
                                    bw.flush();
                                }
                            }
                            else
                            {
                                if(c2<=c1)
                                {
                                    for(int k=0;k<c2;k++)
                                    {
                                        bw.write(l.get(l.size()-c1+k));
                                        bw.flush();
                                    }
                                }
                                else
                                {
                                    int hi=0;
                                    while(c2>0)
                                    {
                                        while((hi<c1)&&(c2>0))
                                        {
                                            bw.write(l.get(l.size()-c1+hi));
                                            bw.flush();
                                            hi++;
                                            c2--;
                                        }
                                        hi=0;
                                    }
                                }
                            }
                        }
                        else
                        {
                            if(l.size()==size)
                            {
                                l.remove();
                                l.add((char)c1);
                            }
                            else
                            {
                                l.add((char)c1);
                                System.out.println((char)c1);
                            }
                            bw.write(c1);
                            bw.flush();
                        }
                    }
                }
                bw.close();
                rand.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(lzss_decoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(lzss_decoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
