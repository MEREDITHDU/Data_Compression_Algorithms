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
public class Lz77 {
  
    
    public static void main(String args[])
    {
        LinkedList<Character> l=new LinkedList<>();
         int size=8;
         int w=-1,h=0;
        try {
            File f1=new File("lz77.txt");
            RandomAccessFile rand=new RandomAccessFile(f1, "r");
            File f=new File("output_lz77.txt");
            f.createNewFile();
            FileOutputStream fos=new FileOutputStream(f);
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
            
            System.out.println("length:"+rand.length());
            System.out.println("buf_length:"+l.size());
            char c;
            while(rand.getFilePointer()<rand.length())
            {
               c=(char)rand.read();
               System.out.println("c:"+c);
               for(int i=0;i<l.size();i++)
               {
                   char g=l.get(i);
                   if(c==g)
                   {
                       int k=1;
                       w=i;
                       if(w==(l.size()-1))
                       {
                           while((rand.getFilePointer()<rand.length())&&(g==(char)rand.read()))
                            {
                                k++;
                                System.out.println("k:"+k+" fpointer:"+rand.getFilePointer());
                            }
                       }
                       else
                       {
                            while((rand.getFilePointer()<rand.length())&&(i+k<l.size())&&(l.get(i+k)==(char)rand.read()))
                            {
                                if(i+k==l.size()-1)
                                {
                                    k++;
                                    h+=k;
                                    k=0;
                                }
                                else
                                    k++;
                            }
                            k+=h;
                            h=0;
                       }
                       if(!(rand.getFilePointer()>=rand.length()))
                       {
                       rand.seek(rand.getFilePointer()-(k+1));
                       }
                       if(k>=1)
                       {
                           rand.seek(rand.getFilePointer()+k);
                           char g1=(char)rand.read();
                           if(l.size()<size)
                           {
                               l.add(g1);
                           }
                           else
                           {
                               l.remove();
                               l.add(g1);
                           }
                           int y=l.size()-w;
                           if(y/10==0)
                           {
                               if(k/10==0)
                               {
                                   bw.write("0"+y+"0"+k+g1);
                               }
                               else
                               {
                                   bw.write("0"+y+k+g1);
                               }
                           }
                           else
                           {
                               if(k/10==0)
                               {
                                   bw.write(y+"0"+k+g1);
                               }
                               else
                               {
                                   bw.write(y+k+g1);
                               }
                           }
                           bw.flush();
                           break;
                       }
                               
                   }
                   w=-1;
               }
               if(w==-1)
               {
                   bw.write("0000"+c);
                   if(l.size()<size)
                   {
                       System.out.println("add_c:"+c);
                       l.add(c);
                   }
                   else
                   {
                       l.remove();
                       l.add(c);
                   }
               }
               
               
            }
            bw.close();
            rand.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lz77.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lz77.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
}
