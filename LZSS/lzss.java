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
public class lzss {
    
    public static void main(String args[])
    {
        LinkedList<Character> l=new LinkedList<>();
         int size=31;
         int w=-1,h=0;
         LinkedList<Integer> l1=new LinkedList<>();
         LinkedList<String> l2=new LinkedList<>();
         
        try {
            File f1=new File("lzss_input.txt");
            RandomAccessFile rand=new RandomAccessFile(f1, "r");
            File f=new File("output_lzss.txt");
            if(f.exists())
                f.delete();
            f.createNewFile();
            FileOutputStream fos=new FileOutputStream(f);
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
            
            File f2=new File("final_output_lzss.txt");
            if(f2.exists())
                f2.delete();
            f2.createNewFile();
            FileOutputStream fos1=new FileOutputStream(f2);
            BufferedWriter bw1=new BufferedWriter(new OutputStreamWriter(fos1));
            
            
            char c;
            while(rand.getFilePointer()<rand.length())
            {
                
               c=(char)rand.read();
               
               for(int i=0;i<l.size();i++)
               {
                   char g=l.get(i);
                   if(c==g)
                   {
                       System.out.println("c:"+c);
                       int k=1;
                       w=i;
                       if(w==(l.size()-1))
                       {
                           while((rand.getFilePointer()<rand.length())&&(g==(char)rand.read()))
                            {
                                
                                k++;
                                //System.out.println("k:"+k+" fpointer:"+rand.getFilePointer());
                            }
                           rand.seek(rand.getFilePointer()-1);
                           if(g==(char)rand.read())
                           {
                               
                           }
                           else
                           {
                               rand.seek(rand.getFilePointer()-1);
                           }
                       }
                       else
                       {
                           if(rand.getFilePointer()==rand.length())
                           {
                               ;
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

                                rand.seek(rand.getFilePointer()-1);

                                   if(l.get(i+k)==(char)rand.read())
                                   {
                                       //rand.seek(rand.getFilePointer()+1);
                                   }
                                   else
                                   {
                                       rand.seek(rand.getFilePointer()-1);
                                   }
                                k+=h;
                                h=0;
                           }
                            
                       }
                       if(!(rand.getFilePointer()>rand.length()))
                       {
                       rand.seek(rand.getFilePointer()-k+1);
                       }
                       if(k>=3)
                       {
                           rand.seek(rand.getFilePointer()+(k-1));
                           l1.add(1);
                           int y=l.size()-w;
                           String s1=Integer.toBinaryString(y);
                           int zeros1=Integer.numberOfLeadingZeros(y)-24;
                           String s2=Integer.toBinaryString(k);
                           int zeros2=Integer.numberOfLeadingZeros(k)-24;
                           for(int rr=0;rr<zeros1;rr++)
                           {
                               s1="0"+s1;
                           }
                           for(int rr=0;rr<zeros2;rr++)
                           {
                               s2="0"+s2;
                           }
                           l2.add(s1);
                           l2.add(s2);
                           bw.flush();
                           s1="";
                           s2="";
                           break;
                       }
                               
                   }
                   w=-1;
               }
               if(w==-1)
               {
                   l1.add(0);
                   String s1=Integer.toBinaryString((int)c);
                   int zeros=Integer.numberOfLeadingZeros((int)c)-24;
                   for(int i=0;i<zeros;i++)
                   {
                       s1="0"+s1;
                   }
                   l2.add(s1);
                   if(l.size()==size)
                   {
                       System.out.println("add_c:"+c);
                       l.remove();
                       l.add(c);
                   }
                   else
                   {
                       System.out.println("add_c:"+c);
                       l.add(c);
                   }
                   s1="";
               }
               
               if((l1.size()==8)||(rand.getFilePointer()>=rand.length()))
                {
                    if(l1.size()!=8)
                    {
                        while(l1.size()!=8)
                        {
                            l1.add(0);
                        }
                    }
                    for(int i=0;i<l1.size();i++)
                    {
                        int a=l1.get(i)+48;
                        bw.write(a);
                    }
                    for(int i=0;i<l2.size();i++)
                    {
                        bw.write(l2.get(i));
                    }
                    bw.flush();
                    l1.clear();
                    l2.clear();
                }
            }
            bw.close();
            rand.close();
            
            RandomAccessFile rand1=new RandomAccessFile(f,"r");
            byte[] ops=new byte[8];
            String strs="";
            while(rand1.getFilePointer()<rand1.length())
            {
                  int kat=rand1.read(ops);
                for(int i=0;i<ops.length;i++)
                {
                    int a=ops[i]-48;
                    strs+=a;
                }
                int qwerty=Integer.parseInt(strs, 2);
                System.out.println(kat);
                bw1.write(qwerty);
                strs="";
            }
            bw1.close();
            rand1.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lz77.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lz77.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
