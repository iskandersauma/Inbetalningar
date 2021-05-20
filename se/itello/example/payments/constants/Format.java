package se.itello.example.payments.constants;

public enum Format {
    PAYMENTTXT("_inbetalningstjansten.txt"),
    SERVICETXT("_betalningsservice.txt");

    public String fileName;

    Format(String fileName){
        this.fileName = fileName;
    }

}
