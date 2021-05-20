package se.itello.example.payments.FileHandler;

import se.itello.example.payments.objects.BundledPayments;
import se.itello.example.payments.constants.PostTypes;

import java.nio.charset.StandardCharsets;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParseTXT implements ParserInterface{

    //used to parse Service files
    @Override
    public BundledPayments parseService(File file){
        int n = 0;
        try(BufferedReader br =  new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1))){
            String line;
            BundledPayments bp = new BundledPayments();
            while ((line = br.readLine()) != null) {
                if(n == 0){
                    n++;
                    if(line.substring(0,1).equals(PostTypes.STARTOFSERVICE.postType)){
                        bp.setAccountNumber(line.substring(1,16).trim().replace(" ", ""));
                        bp.setSum(new BigDecimal(line.substring(16, 30).trim().replace(",", ".")));
                        bp.setAmountOfPayments(Integer.parseInt(line.substring(30,40).trim()));
                        String date = line.substring(40,48).trim();
                        bp.setDate(new SimpleDateFormat("yyyyMMdd").parse(date));
                        bp.setCurrency(line.substring(48,51).trim());
                    }
                    else{
                        throw new IllegalArgumentException("Wrong starting post type");
                    }
                }
                else if(line.substring(0,1).equals(PostTypes.SERVICEPOST.postType)){
                    BigDecimal amount = new BigDecimal(line.substring(1, 15).trim().replace(",", "."));
	                String reference = line.substring(15, 50).trim();
                    bp.addSinglePayment(amount, reference);
                }
                else{
                    throw new IllegalArgumentException("Wrong payment post type");                    
                }
            }
            return bp;
        }
        catch(ParseException | IOException e) {
            System.out.println(e.getMessage());
        }

    

        return null;
    }

    //used to parse Inbetalning files
    @Override
    public BundledPayments parseInbetalningar(File file){
        int n = 0;
        try(BufferedReader br =  new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1))){
            String line;
            BundledPayments bp = new BundledPayments();
            while ((line = br.readLine()) != null) {

                if(n == 0){
                    n++;
                    if(line.substring(0,2).equals(PostTypes.STARTPOFPAYMENTS.postType)){
                        bp.setAccountNumber(line.substring(10,14) + line.substring(14, 24));
                    }
                    else{
                        throw new IllegalArgumentException("Wrong starting post type");
                    }
                }
                else if(line.substring(0,2).equals(PostTypes.ENDOFPAYMENTS.postType)){
                    if(br.readLine() == null){
                        bp.setSum(new BigDecimal(line.substring(2,20) + "." + line.substring(20,22)));
                        bp.setAmountOfPayments(Integer.parseInt(line.substring(30,38)));
                    }
                    else{
                        throw new IllegalArgumentException("Other post types exist after ending post type");
                    }
                    
                }
                else if(line.substring(0,2).equals(PostTypes.PAYMENTPOST.postType)){
                    BigDecimal amount = new BigDecimal(line.substring(2,20) + "." + line.substring(20,22));
                    String reference = line.substring(40,65).trim();
                    bp.addSinglePayment(amount, reference);
                }
                else{
                    throw new IllegalArgumentException("Wrong payment post type");                    
                }
            }
            return bp;
        }
        catch( IOException e) {
            System.out.println(e.getMessage());
        }


        return null;
    }
}
