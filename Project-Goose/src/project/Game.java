/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Game implements Serializable{
    
    ArrayList<Player> players = new ArrayList<>();
    Board br = new Board();
    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private Player p5;
    private Player p6;
    
    SpecialSquareGoose sq_goose;
    SpecialSquareBan sq_ban;
    SpecialSquareWait sq_wait;
    SpecialSquareSkip sq_skip;
    SimpleSquare simpl;
  
    public void start(){
           
       int sumpl=0;
       createBoard();    
     
       System.out.println("board " + br);
       
       Scanner input = new Scanner(System.in);
       System.out.println("Δώσε το πλήθος των παικτών:");
       
       while(true)
       {
            try
            {     
                sumpl = input.nextInt();

                while(sumpl<2 || sumpl>6)
                {
                   System.out.println("Δώσε ξανά το πλήθος των παικτών:");
                   sumpl = input.nextInt();

                } 

                createPlayer(sumpl); 

                break;
            }
           catch(InputMismatchException e)
            {
                System.out.println("Λάθος είσοδος");
                System.out.println("Δώσε ξανά το πλήθος των παικτών:");
                input.nextLine();
            }
       }
    
    }
  
  private int i=1;
  public String play()
  {
      
       int d1,d2; 
          
       do
        { 
          Scanner input = new Scanner(System.in);    
          System.out.println(i + "ος ΓΥΡΟΣ");
        
          for(Player pl:players)
          {
              
             System.out.println("Θες να αποθηκεύσεις το παιχνίδι S/s(Save) ή N/n(no)");
             String s = input.nextLine();
             if(s.equalsIgnoreCase("s"))
             {   
                return s; 
             }
             
             System.out.println("Θες να φορτώσεις ένα παιχνίδι L/l(load) ή Ν/n(no)");
             String l = input.nextLine();
             if(l.equalsIgnoreCase("l"))
             {
                return l;
                 
             }
             
             if(pl.getTurn()==true)
             {
                 System.out.println("Χάνεις την σειρά σου");
                 d1=0;
                 d2=0;
             }
             else
             {
                d1=pl.throwDice1();
                d2=pl.throwDice2();
                System.out.println("Zαριά του παίκτη:" + pl.getName() + " " + d1 + " + " + d2);
             }  
              
          
             pl.SumDices(d1,d2);
             pl.setNumber(pl.SumDices(d1, d2));
             //System.out.println("Current place " + pl.getNumber());
               
               
             try
             {
                
                    pl.setSquare(br.getSquare(pl.getNumber()));


                    System.out.println(pl);

                    if(br.getSquare(pl.getNumber()).getClass()== SimpleSquare.class)
                    {
                         simpleSquare(pl);
                    }
   
                    else if(br.getSquare(pl.getNumber()).getClass()== SpecialSquareGoose.class)
                    {
                        squareGoose(pl,d1,d2);
                    }


                    else if(br.getSquare(pl.getNumber()).getClass()== SpecialSquareSkip.class)
                    {
                        squareSkip(pl);
                    }


                    else if(br.getSquare(pl.getNumber()).getClass()== SpecialSquareBan.class)
                    {    
                        squareBan(pl);
                    }
                    
//                    else if(br.getSquare(pl.getNumber()).getClass()== SpecialSquareWait.class)
//                    {
//                        int temp = sq_wait.action(pl.getNumber(),0,0);     
//                        pl.setStart(temp);   
//                    }
                
                    System.out.println("\n");
                }
                
             catch(IndexOutOfBoundsException e)
              {
                    
                    if(pl.getNumber()>63)
                     {
                         
                          int back = pl.getNumber()-63;
                          pl.moveTo(63-back);
                          pl.setSquare(br.getSquare(pl.getNumber()));
                          
                          if(br.getSquare(pl.getNumber()).getClass()== SimpleSquare.class)
                          {
                              simpleSquare(pl);
                          }
                          
                          else if(br.getSquare(pl.getNumber()).getClass()== SpecialSquareGoose.class)
                          {
                              do{
                              int tmp= sq_goose.action(pl.getNumber(), pl.SumDices(d1, d2),3);
                              pl.moveTo(tmp);
                              pl.setSquare(br.getSquare(pl.getNumber()));
                              }while(br.getSquare(pl.getNumber()).getClass()== SpecialSquareGoose.class);
                              pl.setStart(pl.getNumber());
                              System.out.println("ο παικτης πηγε απο την θεση " + pl.getStart() + " στην " + pl.getNumber());
                              System.out.println(pl);
                          }


                          else if(br.getSquare(pl.getNumber()).getClass()== SpecialSquareSkip.class)
                          {
                              squareSkip(pl);
                          }


                          else if(br.getSquare(pl.getNumber()).getClass()== SpecialSquareBan.class)
                          {    
                               squareBan(pl);
                          }

                                  System.out.println(pl);
                    }
                  }       
        
        }
             System.out.println("\n");
             i++;
     }while(!endGame());
        return null;
  }
  
   private boolean endGame()
   {
       for(Player pl: players)
       {
           System.out.println("The place of player :" + pl.getName()  + " is " + pl.getSquare());
           if(pl.getSquare().equals(simpl))
           {
               System.out.println("Συγχαρητήρια " + pl.getName() + " είσαι ο νικητής της χήνας!!!" );
               return true;
           }
       } 
       
       System.out.println("\n");
       return false;
        
   }
    
   private void simpleSquare(Player pl)
   {
         int temp = simpl.action(pl.getNumber(),0,0);      
         pl.setSquare(br.getSquare(pl.getNumber()));   
         System.out.println("ο παικτης πηγε απο την θεση " + pl.getStart()+ " στην " + pl.getNumber());
         pl.setStart(pl.getNumber());      
   }
    
   
   private void squareGoose(Player pl,int d1,int d2)
   {
        do
        {
                       
              if(pl.SumDices(d1, d2)==9 && pl.getStart()==0)
               {
                     if((d1==6 && d2==3 )|| (d1==3 && d2==6))
                     {
                         int temp=sq_goose.action(pl.getNumber(),0,1);
  
                         pl.setStart(pl.getNumber());
                         pl.moveTo(temp);
                         pl.setSquare(br.getSquare(temp));
                      
                        System.out.println("ο παικτης πηγε απο την θεση " + pl.getStart() + " στην " + pl.getNumber());
                        System.out.println(pl);
                       
                     }
                     else if((d1==5 && d2==4) || (d1==4 && d2==5))
                     {
                         int temp=sq_goose.action(pl.getNumber(),0,2);
                         
                         pl.setStart(pl.getNumber());
                         pl.moveTo(temp);
                         pl.setSquare(br.getSquare(temp));
                         System.out.println("ο παικτης πηγε απο την θεση " + pl.getStart() + " στην " + pl.getNumber());
                         System.out.println(pl);
                     }
                }
             
             
                else
                {   
                    int temp =sq_goose.action(pl.getNumber(),pl.getStart(),4);
                      
                   
                    pl.setStart(pl.getNumber());
                    pl.moveTo(temp);
                    pl.setSquare(br.getSquare(temp));
                      
                      if(pl.getNumber()==19)
                      {
                          squareBan(pl);
                      }
                      
                      else if(pl.getNumber()==58 || pl.getNumber()==42 || pl.getNumber()==6)
                      {
                          squareSkip(pl);
                      }
                      System.out.println("ο παικτης πηγε απο την θεση " + pl.getStart() + " στην " + pl.getNumber());
                     
                      System.out.println(pl);
                }
            
        }while(br.getSquare(pl.getNumber()).getClass()== SpecialSquareGoose.class);
         pl.setStart(pl.getNumber());
   }
   
   
   private void squareBan(Player pl)
   {
     //System.out.println("count: " + pl.getCount());
     pl.setCount();
                  
     int temp = sq_ban.action(pl.getNumber(),0,0);     
     pl.moveTo(temp);
     pl.setTurn(true);
     if(pl.getCount()==3)
     {
          pl.setTurn(false);
          pl.setStart(temp);
     }
   }
   
   private void squareSkip(Player pl)
   {
       int temp = sq_skip.action(pl.getNumber(),pl.getStart(),0);
                   
       pl.setStart(pl.getNumber());
       pl.moveTo(temp);
       pl.setSquare(br.getSquare(temp));
                   
       System.out.println("ο παικτης πηγε απο την θεση " + pl.getStart() + " στην " + pl.getNumber());
       //System.out.println("The getNumber is: " + pl.getNumber());
       pl.setStart(pl.getNumber());
       System.out.println(pl);                   
   }
   
   
    private void createPlayer(int sumpl)
    {
         Scanner input = new Scanner(System.in);
         for(int i=0; i<sumpl; i++)
         {
             System.out.println("Δώσε το όνομα του " + (i+1) + "oυ παίκτη");
             String name = input.nextLine();
                
             if(i==0)
             {
                p1 = new Player(name);
                players.add(p1);
             }
             if(i==1)
             {
                p2=new Player(name);
                players.add(p2);
             }   
             if(i==2)
             {
                p3=new Player(name);
                players.add(p3);
             }
             if(i==3)
             {
                p4=new Player(name);
                players.add(p4);
             }
             if(i==4)
             {
                p5=new Player(name);
                players.add(p5);
             }
             if(i==5)
             {
                p6=new Player(name);
                players.add(p6);
             }
        }
            
     }
           
   private void createBoard()
   {
        for(int i=0; i<=63; i++)
        {
            if(i==5)
            {
                  sq_goose = new SpecialSquareGoose("Goose",5);
                 br.addSquare(sq_goose);
                 
            }
            else if(i==6)
            {
                sq_skip = new SpecialSquareSkip("Bridge",6);
                br.addSquare(sq_skip);
            }
            else if(i==9)
            {
                  sq_goose = new SpecialSquareGoose("Goose",9);
                 br.addSquare(sq_goose);
            }
            
            else if(i==14)
            {
                 sq_goose = new SpecialSquareGoose("Goose",14);
                br.addSquare(sq_goose);
            }
            
            else if(i==18)
            {
                 sq_goose = new SpecialSquareGoose("Golden Goose",18);
               br.addSquare(sq_goose);
            }
            
            else if(i==19)
            {
                sq_ban = new SpecialSquareBan ("Iin",19);
                br.addSquare(sq_ban);
            }
            
            else if(i==23)
            {
                sq_goose = new SpecialSquareGoose("Goose",23);
                br.addSquare(sq_goose);
            }
            
            else if(i==27)
            {
                sq_goose = new SpecialSquareGoose("Golden Goose",27);
                br.addSquare(sq_goose);
            }
            
            else if(i==31)
            {
                sq_wait = new SpecialSquareWait ("Well",31);
                br.addSquare(sq_wait);
            }
            
             else if(i==32)
            {
                 sq_goose = new SpecialSquareGoose("Goose",32);
               br.addSquare(sq_goose);
            }
            
              else if(i==36)
            {
                sq_goose = new SpecialSquareGoose("Golden Goose",36);
                br.addSquare(sq_goose);
            }
             
              
               else if(i==41)
            {
                 sq_goose = new SpecialSquareGoose("Goose",41);
                br.addSquare(sq_goose);
            }
               
               
                else if(i==42)
            {
                 sq_skip = new SpecialSquareSkip("Labyrinth",42);
               br.addSquare(sq_skip);
            }
              
                
             else if(i==45)
            {
                 sq_goose = new SpecialSquareGoose("Golden Goose",45);
                  br.addSquare(sq_goose);
            }
             
              else if(i==50)
            {
                 sq_goose = new SpecialSquareGoose("Goose",50);
                br.addSquare(sq_goose);  
            }
              
               else if(i==52)
            {
                 sq_wait = new SpecialSquareWait("Prison",52);
                br.addSquare(sq_wait);
            }
              
               
                else if(i==54)
            {
                 sq_goose = new SpecialSquareGoose("Golden Goose",54);
                 br.addSquare(sq_goose);
            }
                
                 else if(i==58)
            {
               sq_skip = new SpecialSquareSkip("Death",58);
                  br.addSquare(sq_skip);
            }
                 
                 
                  else if(i==59)
            {
                 sq_goose = new SpecialSquareGoose("Goose",59);
                  br.addSquare(sq_goose);
            }
                
            else
            {
                simpl = new SimpleSquare(i); 
                br.addSquare(simpl);
            }
            
      }
 }
   
}