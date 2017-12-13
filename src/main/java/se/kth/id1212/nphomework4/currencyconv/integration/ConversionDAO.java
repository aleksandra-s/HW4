
package se.kth.id1212.nphomework4.currencyconv.integration;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import se.kth.id1212.nphomework4.currencyconv.model.Conversion;

//Handles all interaction with the entity manager

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class ConversionDAO {
    @PersistenceContext(unitName = "bankPU")
    private EntityManager em;

    //Find conversion rate stored in database using currency name
    public Conversion findConversion(String currencyKey) {
        Conversion conversion = em.find(Conversion.class, currencyKey);
        if (conversion == null) {
            throw new EntityNotFoundException("No rate stored for conversion to " + currencyKey);
        }
        return conversion;
    }
    
    //Store conversion rate and currency in databaseb
    public void storeConversionRate(Conversion conv) {
        em.persist(conv);
    }

}
