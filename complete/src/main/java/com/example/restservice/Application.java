package com.example.restservice;

import com.example.trading.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Application implements SignalHandler {
    private static final String signalBase = ".Sig";
    protected static final Algo algo = new Algo();

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public String getOutput() {
        return this.outputStreamCaptor.toString();
    }

    public void resetOutput() {
        this.outputStreamCaptor.reset();
    }

    @Override
    public void handleSignal(int signal) {
        String className = "com.example.restservice" + signalBase + signal;
        //String className = this.getClass().getPackage().getName() + this.signalBase + signal;

        System.setOut(new PrintStream(outputStreamCaptor));

        try {
            Class<?> cls = Class.forName(className);
            Object obj = cls.getDeclaredConstructor().newInstance();

            //java.lang.reflect.Method method = cls.getMethod("handleSignal");
            //String methodName = "handleSignal";
            try {
                //method = cls.getMethod(methodName);
                java.lang.reflect.Method method = cls.getMethod("handleSignal");
                System.out.println("\nSignal: " + signal);
                method.invoke(obj);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            algo.doAlgo();
        }
        catch (ClassNotFoundException ex) {
            System.out.println("\nSignal " + signal + ": not implemented.");
            algo.cancelTrades();
            System.out.println(ex);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        System.setOut(standardOut);

        /*Algo algo = new Algo();

        switch (signal) {
            case 1:
                algo.setUp();
                algo.setAlgoParam(1,60);
                algo.performCalc();
                algo.submitToMarket();
                break;

            case 2:
                algo.reverse();
                algo.setAlgoParam(1,80);
                algo.submitToMarket();
                break;

            case 3:
                algo.setAlgoParam(1,90);
                algo.setAlgoParam(2,15);
                algo.performCalc();
                algo.submitToMarket();
                break;

            default:
                algo.cancelTrades();
                break;
        }*/
    }
}
