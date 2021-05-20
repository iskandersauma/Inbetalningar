package se.itello.example.payments.FileHandler;

import se.itello.example.payments.objects.BundledPayments;
import java.io.File;

public interface ParserInterface{

    //used to parse Service files
    public BundledPayments parseService(File file);

    //used to parse Inbetalning files
    public BundledPayments parseInbetalningar(File file);
}
