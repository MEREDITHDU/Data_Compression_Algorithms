/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aniruddh
 */
public class JavaApplication1 {

    static char c;
    static LinkedList<node> l4=new LinkedList<>();         //stores final tree
    static HashMap<Character,String> map;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int k,z;
        char w;
        int sum=0;
        LinkedList<Character> l=new LinkedList<>();     //store characters
        LinkedList<Integer> l1=new LinkedList<>();      //stores frequency
        LinkedList<Float> l2=new LinkedList<>();        //stores probability
        LinkedList<node> l3=new LinkedList<>();         //stores remaining nodes
        
        try 
        {
            // TODO code application logic here
            map=new HashMap<>();
            
            File f=new File("64_bit.txt");
            f.createNewFile();
            File f1=new File("huffman_temp.txt");
            if(f1.exists())
                f1.delete();
            f1.createNewFile();
            File f2=new File("huffman_output.txt");
            if(f2.exists())
                f2.delete();
            f2.createNewFile();
            FileInputStream in=new FileInputStream("huffman_input.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            while((k=br.read())!=-1)
            {
                c=(char)k;
                if(!l.contains(c))
                {  
                    l.add(c);
                    l1.add(1);
                }
                else
                {
                    int b=l1.get(l.indexOf(c));
                    l1.set(l.indexOf(c), ++b);
                }
            }
            
            for(int r=0;r<l1.size();r++)
            {
                sum+=l1.get(r);
            }
            
           for(int i=0;i<l.size();i++)
            {
                int h=l1.get(i);
                float y=(float)h/(float)sum;
                l2.add(y);
            }
           
           for(int p=0;p<l2.size();p++)                       // Bubble sort for sorting the list
           {
               for(int i=1;i<l2.size()-p;i++)
               {
                   if(l2.get(i)<l2.get(i-1))
                   {
                       float temp=l2.get(i);
                       l2.set(i, l2.get(i-1));
                       l2.set(i-1, temp);
                       char ctemp=l.get(i);
                       l.set(i, l.get(i-1));
                       l.set(i-1, ctemp);
                   }
               }
           }
           
           
            for(int i=0;i<l.size();i++)
            {
                node n=new node(l2.get(i),-1, -1,l.get(i));
                l3.add(n);
            }
            
        while(l3.size()>0)
        {
                if(l3.size()>1)
                {
                    l4.add(l3.get(0));
                    l4.add(l3.get(1));
                    l3.removeFirst();
                    l3.removeFirst();
                    node n=new node(l4.get(l4.size()-1).prob+l4.get(l4.size()-2).prob,l4.size()-2,l4.size()-1,'^');
                    
                    //binary search to determine position
                    
                    int position=0;
                    int end=l3.size();
                    int start=0;
                    int mid;
                    while(start<end)
                    {
                        mid=(start+end)/2;
                        if(n.prob>l3.get(mid).prob)
                        {
                            start=mid+1;
                        }
                        else if(n.prob<l3.get(mid).prob)
                        {
                            end=mid-1;
                        }
                        else
                        {
                            position=mid;
                            break;
                        }
                    }
                    if(start==end)
                        position=start;
                    l3.add(position, n);
                }
                else if(l3.size()==1)
                {
                    l4.add(l3.get(0));
                    l3.removeFirst();
                }
            }
                
            
            traverse(l4.get(l4.size()-1),"");
           
            br.close();
            in.close();
            
            FileInputStream in1=new FileInputStream("abc.txt");
            BufferedReader br1=new BufferedReader(new InputStreamReader(in1));
            
            FileWriter o1=new FileWriter("64_bit.txt");
            BufferedWriter bwr=new BufferedWriter(o1);
            
            FileOutputStream o2=new FileOutputStream("huffman_temp.txt");
            BufferedWriter bwr1=new BufferedWriter(new OutputStreamWriter(o2));
            
            FileOutputStream fos2=new FileOutputStream("huffman_output.txt");
            BufferedWriter bw2=new BufferedWriter(new OutputStreamWriter(fos2));
            
            while((z=br1.read())!=-1)
            {
                String s1=Integer.toBinaryString(z);
                if(s1.length()<16)
                {
                    while(s1.length()!=16)
                    {
                        s1="0"+s1;
                    }
                }
                bwr.write(s1);
                w=(char)z;
                bwr1.write(map.get(w));
            }
           
            bwr.close();
            bwr1.close();
            
            FileInputStream in2=new FileInputStream("huffman_temp.txt");
            BufferedReader br2=new BufferedReader(new InputStreamReader(in2));
            
            
           }
           
         catch (FileNotFoundException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public static void traverse(node root, String s)
        {
            if((root.left==-1)&&(root.right==-1))
            {
                System.out.println(root.c+"-"+s);
                map.put(root.c, s);
            }
            else
            {
                traverse(l4.get(root.left),s+"0");
                traverse(l4.get(root.right),s+"1");
            }
        }
}
