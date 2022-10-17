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
public abstract class SpecialSquare extends Square implements Serializable{

    private String special_name;
    
    public SpecialSquare(String special_name,int number)
    {
        super(number);
        this.special_name=special_name;
    }
    
    @Override
    public abstract int action(int number,int start,int choice);


    
    
    
    @Override
    public String toString()
    {
        return special_name + " " + super.toString();
    }

    
    
}
