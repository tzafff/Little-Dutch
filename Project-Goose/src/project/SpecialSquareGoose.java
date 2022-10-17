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
public class SpecialSquareGoose extends SpecialSquare implements Serializable{
    
    public SpecialSquareGoose(String special_name, int number) {
        super(special_name, number);
    }



    @Override
    public int action(int number, int start, int choice) {
        
        
        //System.out.println("the number is:" + number + " the start is:" + start);
        
        if(number==9 && choice==1)
       {
           System.out.println("Προχώρισε στο τετράγωνο 26");
           return 26;
       }
       
        else if(number==9 && choice==2)
       {
           System.out.println("Προχώρισε στο τετράγωνο 53");
           return 53;
       }
     
        else if(choice ==3)
        {
            System.out.println("Πήγαινε πίσω " + start + " βήματα");
            return number-start;
        }
        
        else if(choice==4)
       { 
           //System.out.println("the number is:" + number + " the start is:" + start);
           int zaria=number-start;
           System.out.println("Προχώρησε " + zaria + " βήματα" );
           return number+zaria;
       }  
        return 0;
    }

   
    
    
    
}