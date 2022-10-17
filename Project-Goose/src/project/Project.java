/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        Game game = new Game();
        game.start();
        
        try
        {
              String g = game.play();
              if(g.equalsIgnoreCase("s"))
              {
                save(game,g);
              }
              else if(g.equalsIgnoreCase("l"))
              {
                load();
              }
              
        }
        catch(NullPointerException e)
        {
            System.out.println("Tέλος Παιχνιδιού!!!");
        }
       
       
    }
    
    public static void save(Game game,String g)
    {
            Scanner input = new Scanner(System.in);
       
            System.out.println("Δώσε το όνομα του αρχείου στο οποιο θες να γίνει η αποθήκευση του παιχνιδιού");
            
            while(true)
            {
                String name = input.nextLine();
                try
                {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name));
                    oos.writeObject(game);
                    String ch = game.play();
                     if(ch.equalsIgnoreCase("s"))
                     {
                         save(game,g);
                     }
                     else if(ch.equalsIgnoreCase("l"))
                     {
                        load();
                     }
                    
                    oos.close();
                    break;
                }
                catch(FileNotFoundException e)
                {
                    System.out.println("Δώσε το όνομα του αρχείου στο οποιο θες να γίνει η αποθήκευση του παιχνιδιού");  
                }
                catch (IOException e) 
                {
                    System.err.println(e);
                }
            }
        
    }
    
    public static void load()
    {
            Scanner input = new Scanner(System.in);
            System.out.println("Δώσε ένα όνομα αρχείου το οποίο θες να φορτώσεις");
            
            while(true)
            {
                String name = input.nextLine();
                try
                {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name));
                    Game gm = (Game)(ois.readObject());
                    System.out.println(gm);
                    String ch = gm.play();
                    if(ch.equalsIgnoreCase("s"))
                    {
                         save(gm,ch);
                    }
                    else if(ch.equalsIgnoreCase("l"))
                    {
                        load();
                    }
                    ois.close();
                    break;
                }
                catch(FileNotFoundException e)
                {
                    System.out.println("Δώσε ένα όνομα αρχείου το οποίο θες να φορτώσεις");
                }
                catch (IOException e) 
                {
                    System.err.println(e);
                }
                catch (ClassNotFoundException e) 
                {
                    System.err.println(e);
                }
          }     
    }
   
    
    
}
