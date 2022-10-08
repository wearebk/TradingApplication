package com.example.restservice;

//import org.junit.After;
//import org.junit.Before;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTests {
    private static final Application tApp = new Application();

    //private final PrintStream standardOut = System.out;
    //private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public static final String[] anExpected = {
            "Signal: 1setUpsetAlgoParam 1,60performCalcsubmitToMarketdoAlgo",
            "Signal: 2reversesetAlgoParam 1,80submitToMarketdoAlgo",
            "Signal: 3setAlgoParam 1,90setAlgoParam 2,15performCalcsubmitToMarketdoAlgo"
    };

    public static String clean(String s) {
        return s.replace("\n", "").replace("\r", "");
    }

    /*@BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }*/

    @Test
    public void outCorrect() {
        // Signals 1..3
        for (int s = 0; s < anExpected.length; s++) {
            tApp.handleSignal(s + 1);

            String actual = tApp.getOutput();
            tApp.resetOutput();
            assertEquals(anExpected[s], clean(actual));

            //assertEquals(this.anExpected[s], this.clean(outputStreamCaptor.toString()));
            //outputStreamCaptor.reset();
        }
    }

    @Test
    public void outWrong() {
        // Signal 4
        String expected = "Signal 4: not implemented.cancelTradesjava.lang.ClassNotFoundException: com.example.restservice.Sig4";
        tApp.handleSignal(4);

        String actual = tApp.getOutput();
        tApp.resetOutput();
        assertEquals(expected, clean(actual));

        //assertEquals(expected, this.clean(outputStreamCaptor.toString()));
        //outputStreamCaptor.reset();
    }

    /*@AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }*/
}
