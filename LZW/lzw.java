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
public class lzw {
    
    public static void main(String args[])
    {
        char ch,ch1;
        String s="",s1="",s2="",s3="";
        int j;
        int size1,size;
        int a;
        try {
            File f=new File("lzw_input.txt");
                    RandomAccessFile rand=new RandomAccessFile(f, "r");
                    File f1=new File("lzw_interim.txt");
                    if(f1.exists())
                        f1.delete();
                    f1.createNewFile();
                    FileOutputStream fos=new FileOutputStream(f1);
                    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
                    LinkedList<String> l1=new LinkedList<>();
                    a=3;
                    ch=(char)a;
                    s=String.valueOf(ch);
                    l1.add(s);
                    a=13;
                    ch=(char)a;
                    s=String.valueOf(ch);
                    l1.add(s);
                    for(int i=32;i<127;i++)
                    {
                        ch=(char)i;
                        s=String.valueOf(ch);
                        l1.add(s);
                    }
                    size1=l1.size();
                    size=no_of_bits(size1);
                    
                    while(rand.getFilePointer()<rand.length())
                    {
                        a=rand.read();
                        ch1=(char)a;
                        s=String.valueOf(ch1);
                        while((rand.getFilePointer()<rand.length())&&(l1.contains(s)))
                        {
                            a=rand.read();
                            ch1=(char)a;
                            s+=String.valueOf(ch1);
                        }
                        
                        if(rand.getFilePointer()==rand.length())
                        {
                            System.out.println("hello1");
                            System.out.println(s);
                            if(l1.contains(s))
                            {
                                j=l1.indexOf(s);
                                s2=Integer.toBinaryString(j);
                               System.out.println("hello");
                                if(s2.length()<size)
                                {
                                    while(s2.length()<size)
                                    {
                                        s2="0"+s2;
                                    }
                                }
                                bw.write(s2);
                            }
                            else
                            {
                                s1=s.substring(0, s.length()-1);
                                j=l1.indexOf(s1);
                                s2=Integer.toBinaryString(j);
                                if(s2.length()<size)
                                {
                                    while(s2.length()<size)
                                    {
                                        s2="0"+s2;
                                    }
                                }
                                bw.write(s2);
                                s3=s.substring(s.length()-1, s.length());
                                j=l1.indexOf(s3);
                                s2=Integer.toBinaryString(j);
                                if(s2.length()<size)
                                {
                                    while(s2.length()<size)
                                    {
                                        s2="0"+s2;
                                    }
                                }
                                bw.write(s2);
                            }
                        }
                        else
                        {
                            l1.add(s);
                            rand.seek(rand.getFilePointer()-1);
                            s1=s.substring(0, s.length()-1);
                            j=l1.indexOf(s1);
                            s2=Integer.toBinaryString(j);
                           // System.out.println(s2.length());
                            if(s2.length()<size)
                             {
                                while(s2.length()<size)
                                 {
                                    s2="0"+s2;
                                 }
                              }
                              bw.write(s2);
                        }
                        
                        size1=l1.size();
                        size=no_of_bits(size1);
                        
                    }
                    rand.close();
                    s2=Integer.toBinaryString(0);
                    if(s2.length()<size)
                    {
                        while(s2.length()<size)
                        {
                             s2="0"+s2;
                        }
                    }
                    bw.write(s2);
                    bw.flush();
                    
                    File f2=new File("lzw_compressed.txt");
                    if(f2.exists())
                        f2.delete();
                    f2.createNewFile();
                    FileOutputStream fos1=new FileOutputStream(f2);
                    BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(fos1));
                    RandomAccessFile rand1=new RandomAccessFile(f1, "r");
                    
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
                       // System.out.println(s.length());
                        j=Integer.parseInt(s, 2);
                        bw1.write(j);
                    }
                    bw1.close();
                    rand1.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(lzw.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(lzw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static int no_of_bits(int a)
    {
        for(int i=1;i<64;i++)
        {
            double b=Math.pow(2,i);
            if(b>a)
            {
                return i;
            }
        }
        return 0;
    }
    
}
