
package se.kth.id1212.nphomework4.currencyconv.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityNotFoundException;
import se.kth.id1212.nphomework4.currencyconv.integration.ConversionDAO;
import se.kth.id1212.nphomework4.currencyconv.model.Conversion;


@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class UserFacade {
    @EJB ConversionDAO conversionDB;
    boolean stored = false;

    private void storeConversionRates() {
        conversionDB.storeConversionRate(new Conversion("USD",0.12));
        conversionDB.storeConversionRate(new Conversion("EUR",0.1));
        conversionDB.storeConversionRate(new Conversion("PLN",0.42));
        conversionDB.storeConversionRate(new Conversion("GBP",0.088));
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
