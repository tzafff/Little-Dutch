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
public abstract class Square extends Board implements Serializable{
    
    private int number;
    private int start;
    private Player player;
    
    public Square(int number)
    {
        this.number=number;
    }
    
    public void setPlayer(Player player)
    {
        this.player=player;
        
    }
   
    public Player getPlayer()
    {
        return player;
        
    }
    
    public void setStart(int start)
    {
        this.start=start;
    }
    
    public void setNumber(int number)
    {
        this.number=number;
    }
    
    public int getNumber()
    {
        return number;
    }
    
   
    
    public abstract int action(int number,int start,int choice);
    
    
    
    public String toString()
    {
       return "" + number;
    }
}
