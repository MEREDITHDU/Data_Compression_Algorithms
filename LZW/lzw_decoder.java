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
public class lzw_decoder {
    
    public static void main(String args[])
    {
        int a,size,size1,j,d,a1,k;
        String s,s1,s2,s3="",s4="",s5;
        char ch,ch1;
        try {
                        File f=new File("lzw_compressed.txt");
                        RandomAccessFile rand=new RandomAccessFile(f, "r");
                        File f1=new File("lzw_interim1.txt");
                        if(f1.exists())
                            f1.delete();
                        f1.createNewFile();
                        FileOutputStream fos=new FileOutputStream(f1);
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
                        
                        LinkedList<String> l=new LinkedList<>();
                        a=3;
                        ch=(char)a;
                        s=String.valueOf(ch);
                        l.add(s);
                        a=13;
                        ch=(char)a;
                        s=String.valueOf(ch);
                        l.add(s);
                        for(int i=32;i<127;i++)
                        {
                            ch=(char)i;
                            s=String.valueOf(ch);
                            l.add(s);
                        }
                        
                        File f2=new File("lzw_decoded.txt");
                        if(f2.exists())
                            f2.delete();
                        f2.createNewFile();
                        RandomAccessFile rand1=new RandomAccessFile(f1, "r");
                        FileOutputStream fos1=new FileOutputStream(f2);
                        BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(fos1));
                        RandomAccessFile rand2=new RandomAccessFile(f2, "r");
                        size1=l.size();
                        size=no_of_bits(size1);
                        s3="";
                        s1="";
                        s2="";
                        while(rand1.getFilePointer()<rand1.length())
                        {
                            s="";
                            for(int i=0;i<size;i++)
                            {
                                a=rand1.read();
                                ch=(char)a;
                                s1=String.valueOf(ch);
                                s+=s1;
                            }
                            j=Integer.parseInt(s, 2);
                            if(j>(l.size()-1))
                            {
                                rand2.seek(rand2.length()-1);
                                a=rand2.read();
                                ch=(char)a;
                                s2=String.valueOf(ch);
                                s2=s2+s2;
                                bw1.write(s2);
                                bw1.flush();
                                l.add(s2);
                                s2="";
                            }
                            else
                            {
                                s2=l.get(j);
                                System.out.println(s2);
                                if(j==0)
                                    break;
                                bw1.write(s2);
                                bw1.flush();
                                if(s3.length()>0)
                                {
                                    s4=s2.substring(0,1);
                                    s3+=s4;
                                    s5=s2;
                                }
                                else
                                {
                                    s3=s2;
                                    s5="";
                                }
                                
                                if(!(l.contains(s3)))
                                {
                                    l.add(s3);
                                    s3=s5;
                                }
                            }
                            size1=l.size();
                            size=no_of_bits(size1);
                        }
                        rand2.close();
                        rand1.close();
                        bw1.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(lzw_decoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(lzw_decoder.class.getName()).log(Level.SEVERE, null, ex);
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
