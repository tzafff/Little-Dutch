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
public class SpecialSquareWait extends SpecialSquare implements Serializable{

    public SpecialSquareWait(String special_name, int number) {
        super(special_name, number);
    }

    @Override
    public int action(int number,int start,int choice) {
        return number;
      
    }
    
}



