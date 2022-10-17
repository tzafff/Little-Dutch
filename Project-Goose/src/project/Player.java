/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author USER
 */
public class Player extends Board implements Serializable{
    
    private String name;
    private Board board;
    private Square square;
    private int number;
    private int start;
    private boolean miss_turn;
    private int count_turns;
    
   
    Random rnd1 = new Random();
    Random rnd2 = new Random();
    
    
    public Player(String name)
    {
        this.name=name;   
    }
     
    public void setCount()
    {
        this.count_turns++;
    }
    
    public int getCount()
    {
        return count_turns;
    }
    
    public void setTurn(boolean turn)
    {
        this.miss_turn=turn;
    }
    
    public boolean getTurn()
    {
        return miss_turn;
    }
    
    public int moveTo(int move)
    {
        return this.number=move;
    }
   
    
    public void setNumber(int number)
    {
        
        this.number+=number;
    }
    
    public int getNumber()
    {
        return number;
    }
    
    
    public void setStart(int start)
    {
        this.start=start;
    }
    
    
    public int getStart()
    {
        return start;
    }
    
    
   public String getName()
   {
       return name;
   }
    
   
   public void setBoard(Board board)
   {
       this.board=board;
   }
   
   
   public Board getBoard()
   {
       return board;
   }
   
   
   public void setSquare(Square square)
   {
       this.square=square;
   }
   
   public Square getSquare()
   {
       return square;
   }
   

   public int SumDices(int d1,int d2)
   {
       return d1+d2;
   }
   
   
     public int throwDice1()
    {        
         int d1 = rnd1.nextInt(6) + 1;
         return d1;
    }
     
     public int throwDice2()
    {        
         int d2 = rnd2.nextInt(6) + 1;
         return d2;
    }
   

    @Override
     public String toString()
     {
         return "ο παιίκτης " + name + " βρίσκεται στο τετράγωνο " + getSquare();
     }
     
     
}
