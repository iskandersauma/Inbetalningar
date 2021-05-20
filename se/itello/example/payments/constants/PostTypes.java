package se.itello.example.payments.constants;

public enum PostTypes {
    
    STARTPOFPAYMENTS("00"),
    PAYMENTPOST("30"),
    ENDOFPAYMENTS("99"),
    STARTOFSERVICE("O"),
    SERVICEPOST("B");

    public String postType;

    PostTypes(String postType){
        this.postType = postType;
    }
}
