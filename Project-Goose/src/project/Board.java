/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Board implements Serializable{
     
    ArrayList<Square> squares;
    
    public Board(){
        squares=new ArrayList<>(); 
    }
    
    
    public void addSquare(Square spec)
    {   
         squares.add(spec);
    }
           
    public Square getSquare(int num)
    {
            return squares.get(num);
    }
      
    public String toString()
    {
        return "" +squares;
    }
   
    
}
