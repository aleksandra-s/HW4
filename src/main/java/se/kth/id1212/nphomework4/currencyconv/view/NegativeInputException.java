/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.nphomework4.currencyconv.view;

/**
 *
 * @author aleks_uuia3ly
*/
public class NegativeInputException extends Exception {
    /**
     * Creates an instance representing the specified exception situation.
     * 
     * @param msg A description of the exception.
     */
    public NegativeInputException(String msg) {
        super(msg);
    }

}

