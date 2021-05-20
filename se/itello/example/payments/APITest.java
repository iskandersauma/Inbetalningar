package se.itello.example.payments;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import se.itello.example.payments.FileHandler.*;
import se.itello.example.payments.objects.*;

class APITest {

	@Test
    public void wrongFileName() throws Exception{
        Reader fileReader = new Reader();
        assertThrows(NullPointerException.class, ()->{ fileReader.readFile("Ex_betalningsservice.txt");});
    }
	
	@Test
    public void incorrectServicePostTypes() throws Exception{
        Reader fileReader = new Reader();
        assertThrows(IllegalArgumentException.class, ()->{ fileReader.readFile("Felaktig_betalningsservice.txt");});
    }

	@Test
    public void inbetalningTotalSumCheck() throws Exception{
        Reader fileReader = new Reader();
        BundledPayments bp = fileReader.readFile("Exempelfil_inbetalningstjansten.txt");
        assertEquals(API.checkSum(bp), true);
    }
	
	@Test
    public void serviceReturnWrongSum() throws Exception{
        Reader fileReader = new Reader();
        BundledPayments bp = fileReader.readFile("Exempelfil_betalningsservice.txt");
        assertEquals(API.checkSum(bp), false);
        assertEquals(API.checkSum(bp), false);
    }
	
	@Test
    public void betalningServiceCheck() throws Exception{
        Reader fileReader = new Reader();
        BundledPayments bp = fileReader.readFile("Exempelfil_betalningsservice.txt");
        assertEquals(bp.getAccountNumber(), "55555555555555");
        assertEquals(bp.getCurrency(), "SEK");
    }
	
	@Test
    public void inbetalningTjansterCheck() throws Exception{
        Reader fileReader = new Reader();
        BundledPayments bp = fileReader.readFile("Exempelfil_inbetalningstjansten.txt");
        assertEquals(bp.getAccountNumber(), "12341234567897");
        assertEquals(bp.getCurrency(), "SEK");
    }

}
