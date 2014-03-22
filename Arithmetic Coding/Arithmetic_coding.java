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
public class Arithmetic_coding {
    
    public static void main(String args[])
    {
        LinkedList<Info> l=new LinkedList<>();
        int flag=0;
        int j=0,cntr=0;
        try {
            File f1=new File("arithmetic_output.txt");
            f1.createNewFile();
            FileOutputStream fos=new FileOutputStream(f1);
            BufferedWriter bwr=new BufferedWriter(new OutputStreamWriter(fos));
            
            File f=new File("arithmetic_input.txt");
            RandomAccessFile r=new RandomAccessFile(f,"r");
            while(r.getFilePointer()<r.length())
            {
                char c=(char)r.read();
                for(int i=0;i<l.size();i++)
                {
                    if(l.get(i).c==c)
                    {
                        l.get(i).freq++;
                        flag=1;
                        break;
                    }
                }
                if(flag==0)
                {
                    Info i=new Info(c,0f,1,0f,0f);
                    l.add(i);
                }
                flag=0;
                j++;
            }
            Info opr=l.remove(l.size()-2);
            l.add(opr);
            for(int i=l.size()-1;i>=0;i--)
            {
                l.get(i).prob=(float)((float)l.get(i).freq/(float)j);
                
                if(i==(l.size()-1))
                {
                    l.get(i).lower=0f;
                    l.get(i).upper=l.get(i).prob;
                }
                else
                {
                    l.get(i).lower=l.get(i+1).upper;
                    l.get(i).upper=l.get(i).lower+l.get(i).prob;
                }
                System.out.println(l.get(i).c+"-"+l.get(i).prob+"-["+l.get(i).lower+","+l.get(i).upper+")");
            }
            
            float low=0f,high=1f;
            float ltemp=0f,htemp=0f;
            r.seek(0);
            int pos=0;
            while(r.getFilePointer()<r.length())
            {
                char ch=(char)r.read();
                for(int i=0;i<l.size();i++)
                {
                    if(l.get(i).c==ch)
                    {
                      pos=i;
                      break;
                    }
                }
                System.out.println("low: "+low);
                System.out.println("high: "+high);
                float temp_low=low;
                low=temp_low+(high-temp_low)*l.get(pos).lower;
                high=temp_low+(high-temp_low)*l.get(pos).upper;
                
                ltemp=low*1000000;
                htemp=high*1000000;
                
                int li=(int)ltemp;
                int hi=(int)htemp;
                hi=hi-1;
                if((li/100000)==(hi/100000))
                {
                    int y=li/100000+48;
                    System.out.println(li);
                    bwr.write(y);
                    if(cntr!=0)
                    {
                        for(int i=0;i<cntr;i++)
                            bwr.write("0");
                    }
                    cntr=0;
                    bwr.flush();
                    li=li%100000;
                    hi=hi%100000;
                    li=li*10;
                    hi=(hi*10)+9;
                    hi+=1;
                    low=(float)((float)li/(float)1000000);
                    high=(float)((float)hi/(float)1000000);
                    
                }
                else if(hi-li<=5)
                {
                    cntr++;
                    int ytemp=hi/100000;
                    int ztemp=li/100000;
                    int yytemp=hi%10000;
                    int zztemp=li%10000;
                    yytemp=yytemp*10+9;
                    zztemp=zztemp*10;
                    yytemp=yytemp+ytemp*100000;
                    zztemp=zztemp+ztemp*100000;
                    hi=yytemp;
                    low=zztemp;
                    low=(float)((float)li/(float)1000000);
                    high=(float)((float)hi/(float)1000000);
                }
                
            }
            int yup=(int)(low*1000000);
            String str=String.valueOf(yup);
            int yupy=(int)(high*1000000);
            System.out.println(yupy);
            bwr.write(str);
            bwr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Arithmetic_coding.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Arithmetic_coding.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
