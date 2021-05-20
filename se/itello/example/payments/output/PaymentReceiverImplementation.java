package se.itello.example.payments.output;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentReceiverImplementation implements PaymentReceiver{
    /**
     * Called to mark the start of a new payment bundle.
     * @param accountNumber The account number where funds are deposited.
     * @param paymentDate The date at which the funds were/will be deposited on the specified account.
     * @param currency The currency of the payments in the bundle.
     */
    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency){
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Currency: " + currency);
    }

    /**
     * Called to notify the receiver of a single payment within a bundle.
     * @param amount The payment amount.
     * @param reference The payment reference.
     */
    @Override
    public void payment(BigDecimal amount, String reference){
        System.out.println("Amount: " + amount);   
        System.out.println("Reference: " + reference);
    }

    /**
     * Called to mark the end of a payment bundle. This means that there will be no more calls to payment() 
     * for this bundle, and that the receiver can process the bundle.
     */
    @Override
    public void endPaymentBundle(){
        System.out.println("Parsing completed");
    }
}
