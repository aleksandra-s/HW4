
package se.kth.id1212.nphomework4.currencyconv.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//A persistent representation of a conversion rate and currency object (stored in database)

@Entity
public class Conversion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String currency;
    private double conversionRate;

    public Conversion() {
    }

    public Conversion(String currency, double rate) {
        this.currency = currency;
        this.conversionRate = rate;
    }

    public String getCurrency() {
        return this.currency;
    }

    public double getConversionRate() {
        return this.conversionRate;
    }
}
