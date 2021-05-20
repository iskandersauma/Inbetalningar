package se.itello.example.payments.objects;

import java.math.BigDecimal;
import java.util.*;

public class BundledPayments {

    public class SinglePayment{
        private BigDecimal amount;
        private String reference;

        public SinglePayment(BigDecimal amount, String reference){
            this.amount = amount;
            this.reference = reference;
        }

        public BigDecimal getAmount(){
            return this.amount;
        }

        public String getReference(){
            return this.reference;
        }

        public void setAmount(BigDecimal amount){
            this.amount = amount;
        }

        public void setReference(String reference){
            this.reference = reference;
        }
    }

    private String accountNumber;
    private BigDecimal sum;
    private int amountOfPayments;
    private Date date;
    private String currency = "SEK";
    private ArrayList<SinglePayment> singlePayments = new ArrayList<>();


    public String getAccountNumber(){
        return this.accountNumber;
    }    

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public BigDecimal getSum(){
        return this.sum;
    }

    public void setSum(BigDecimal sum){
        this.sum = sum;
    }

    public int getAmountOfPayments(){
        return this.amountOfPayments;
    }    

    public void setAmountOfPayments(int amountOfPayments){
        this.amountOfPayments = amountOfPayments;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public String getCurrency(){
        return this.currency;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public ArrayList<SinglePayment> getSinglePayments(){
        return this.singlePayments;
    }

    public void setSinglePayments(ArrayList<SinglePayment> singlePayments){
        this.singlePayments = singlePayments;
    }

    public void addSinglePayment(BigDecimal amount, String reference){
        this.singlePayments.add(new SinglePayment(amount, reference));
    }
}
