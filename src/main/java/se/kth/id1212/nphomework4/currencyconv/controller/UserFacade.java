/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.nphomework4.currencyconv.controller;

//import javax.ejb.Stateless;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityNotFoundException;
import se.kth.id1212.nphomework4.currencyconv.integration.ConversionDAO;
import se.kth.id1212.nphomework4.currencyconv.model.Conversion;

/**
 *
 * @author aleks_uuia3ly
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class UserFacade {
    @EJB ConversionDAO conversionDB;
    boolean stored = false;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private void storeConversionRates() {
        //Account newAcct = new Account(balance, firstName, lastName);
        conversionDB.storeConversionRate(new Conversion("USD",0.12));
        conversionDB.storeConversionRate(new Conversion("EUR",0.1));
        conversionDB.storeConversionRate(new Conversion("PLN",0.42));
        conversionDB.storeConversionRate(new Conversion("GBP",0.088));
        //return newAcct;
        stored = true;
    }
    
    public double doConversion(String currencyChoice, double amountToConvert){
        if(!stored){
            storeConversionRates();
        }
        Conversion conversion = conversionDB.findConversion(currencyChoice);
        return amountToConvert * conversion.getConversionRate();
    }

}
