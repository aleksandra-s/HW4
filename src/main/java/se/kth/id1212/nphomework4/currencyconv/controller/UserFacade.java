
package se.kth.id1212.nphomework4.currencyconv.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
//import javax.persistence.EntityNotFoundException;
import se.kth.id1212.nphomework4.currencyconv.integration.ConversionDAO;
import se.kth.id1212.nphomework4.currencyconv.model.Conversion;


@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class UserFacade {
    @EJB ConversionDAO conversionDB;
    boolean stored = false;

    //Store conversion rates in database
    private void storeConversionRates() {
        conversionDB.storeConversionRate(new Conversion("USD",0.12));
        conversionDB.storeConversionRate(new Conversion("EUR",0.1));
        conversionDB.storeConversionRate(new Conversion("PLN",0.42));
        conversionDB.storeConversionRate(new Conversion("GBP",0.088));
        stored = true;
    }
    
    //Convert inputted amount 
    public double doConversion(String currencyChoice, double amountToConvert){
        if(!stored){
            storeConversionRates();
        }
        String currency;
        if(currencyChoice.length() == 7){
            currency = currencyChoice.substring(4);
            Conversion conversion = conversionDB.findConversion(currency);
            return amountToConvert / conversion.getConversionRate();
        }
        else{
            currency = currencyChoice.substring(2);
            Conversion conversion = conversionDB.findConversion(currency);
            return amountToConvert * conversion.getConversionRate();
        } 
    }
}
