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
public class SpecialSquareSkip extends SpecialSquare implements Serializable {

    public SpecialSquareSkip(String special_name, int number) {
        super(special_name, number);
    }
    
   
    
    
    @Override
     public String toString()
    {
        return  super.toString();
    }


    @Override
    public int action(int number, int start,int choice) {
        
        if(number==6)
       {
           System.out.println("Προχώρισε στο τετράγωνο 12");
           return 12;
       }
       
       if(number==42)
       {
            System.out.println("Προχώρισε στο τετράγωνο 30");
           return 30;
       }
       
       if(number==58)
       {
           System.out.println("ΘΑΝΑΤΟΣ!!! ΕΠΕΣΤΡΕΨΕ ΣΤΗΝ ΑΡΧΗ...");
           return 1;
       }
       
        return 0;       
    }
    
}
