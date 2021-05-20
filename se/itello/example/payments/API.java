package se.itello.example.payments;

import se.itello.example.payments.FileHandler.Reader;
import se.itello.example.payments.objects.BundledPayments;
import se.itello.example.payments.output.PaymentReceiverImplementation;

import java.math.BigDecimal;

public class API {

    public static BundledPayments runAPI(String fileName){
        Reader read = new Reader();
        BundledPayments bp = read.readFile(fileName);
        printValues(bp);
        return bp;
    }

    private static void printValues(BundledPayments bp){
        PaymentReceiverImplementation pri = new PaymentReceiverImplementation();
        pri.startPaymentBundle(bp.getAccountNumber(), bp.getDateOfPayments(), bp.getCurrency());
        for(int i = 0; i < bp.getSinglePayments().size(); i++){
            pri.payment(bp.getSinglePayments().get(i).getAmount(), bp.getSinglePayments().get(i).getReference());
        }
        pri.endPaymentBundle();
    }

    public static boolean checkSum(BundledPayments bp){
        BigDecimal tot = new BigDecimal(0);
        for(int i = 0; i < bp.getSinglePayments().size(); i++){
            tot = tot.add(bp.getSinglePayments().get(i).getAmount());
        }
        return (tot.compareTo(bp.getTotalSum()) == 0);
    }
    

    public static void main(String[] args) {
        BundledPayments bp = runAPI("Exempelfil_betalningsservice.txt");
        System.out.println(checkSum(bp));
        bp = runAPI("Exempelfil_inbetalningstjansten.txt");
        System.out.println(checkSum(bp));
    }
}
