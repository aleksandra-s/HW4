
package se.kth.id1212.nphomework4.currencyconv.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.kth.id1212.nphomework4.currencyconv.controller.UserFacade;

/**
 * Handles all interaction with the account JSF page.
 */
@Named("conversionManager")
@ConversationScoped
public class ConversionManager implements Serializable {
    @EJB
    private UserFacade userFacade;
    private String newCurrency;
    private Double newAmount;
    private String result;
    private Exception conversionFailure;
    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        conversionFailure = e;
    }
    
    public void doConversion() {
        try {
            startConversation();
            if(newAmount < 0){
                throw new NegativeInputException("Cannot convert negative amount of SEK");
            }
            conversionFailure = null;
            double conversionAmount = userFacade.doConversion(newCurrency, newAmount);
            result = conversionAmount + " " + newCurrency;
        } catch (Exception e) {
            handleException(e);
        }
    }
    
    public void setNewCurrency(String currency){
        this.newCurrency = currency;
    }
    
    public String getNewCurrency(){
        return null;
    }
    
    public void setNewAmount(Double amount){
        this.newAmount = amount;
    }
    
     public Double getNewAmount(){
        return null;
    }
    
    public void setResult(String result){
        this.result = result;
    }
    
    public String getResult(){
        return result;
    }
    
    public boolean getSuccess() {
        return conversionFailure == null;
    }

    public Exception getException() {
        return conversionFailure;
    }
}