/*
 * The MIT License
 *
 * Copyright 2017 Leif Lindbäck <leifl@kth.se>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package se.kth.id1212.nphomework4.currencyconv.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.kth.id1212.nphomework4.currencyconv.controller.UserFacade;
//import se.kth.id1212.appserv.bank.controller.CashierFacade;
//import se.kth.id1212.appserv.bank.model.AccountDTO;

/**
 * Handles all interaction with the account JSF page.
 */
@Named("conversionManager")
@ConversationScoped
public class ConversionManager implements Serializable {
    @EJB
    private UserFacade userFacade;
    //private ConversionDTO currentConversion;
    private String newCurrency;
    private Double newAmount;
    private String result;
    /*private String newAccountHolderFirstName;
    private String newAccountHolderLastName;
    private Integer newAccountBalance;
    private Integer transactionAmount;
    private Integer searchedAcct;
    private Exception transactionFailure;*/
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
        //transactionFailure = e;
    }
    
    public void doConversion() {
        try {
            startConversation();
            //transactionFailure = null;
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
}