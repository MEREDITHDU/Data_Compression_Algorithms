/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author aniruddh
 */
public class node {
    int left;
    int right;
    float prob;
    char c;
    
    public node(float prob,int left,int right, char c)
    {
        this.left=left;
        this.right=right;
        this.c=c;
        this.prob=prob;
    }
}
