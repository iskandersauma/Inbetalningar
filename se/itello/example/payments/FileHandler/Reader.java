package se.itello.example.payments.FileHandler;

import java.io.File;

import se.itello.example.payments.constants.Format;
import se.itello.example.payments.objects.BundledPayments;

public class Reader {

    private String dir = "se/itello/example/payments/TextFiles/";
    private ParseTXT parseTXT = new ParseTXT();

   public BundledPayments readFile(String fileName){
        File file = new File(dir + fileName);
        if(!file.exists()) {
        	throw new NullPointerException("File not found");
        }
        return this.startParsing(file);
    }

    private BundledPayments startParsing(File file){
        if(file.getName().endsWith(Format.PAYMENTTXT.fileName)){
            return parseTXT.parseInbetalningar(file);
        }
        else if(file.getName().endsWith(Format.SERVICETXT.fileName)){
            return parseTXT.parseService(file);
        }
        else{
            return null;
        }

    }
}
