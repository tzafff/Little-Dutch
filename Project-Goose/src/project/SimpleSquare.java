/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class SimpleSquare extends Square implements Serializable{
    
     public SimpleSquare(int number)
    {
        super(number);
       
    } 
     
    
     @Override
    public int action(int number,int start,int choice) {
       return number;
    }
    
    
     @Override
    public String toString()
    {
        return super.toString();
    }
    
}
