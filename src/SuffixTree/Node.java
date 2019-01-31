/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuffixTree;

import java.util.ArrayList;

/**
 *
 * @author koksa
 */
public class Node {
    
    String str;
    ArrayList<Node> children;
    int indeks;
    int x,y;
    
    public Node(String str){
        this.str=str;
        
    }
    
}
