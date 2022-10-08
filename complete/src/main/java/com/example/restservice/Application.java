package com.example.restservice;

import com.example.trading.*;

public class Application implements SignalHandler {
    private final String signalBase = ".Sig";
    public Algo algo = new Algo();

    @Override
    public void handleSignal(int signal) {
        String className = "com.example.restservice" + this.signalBase + signal;
        //String className = this.getClass().getPackage().getName() + this.signalBase + signal;

        try {
            Class<?> cls = Class.forName(className);
            Object obj = cls.getDeclaredConstructor().newInstance();

            java.lang.reflect.Method method = cls.getMethod("handleSignal");
            String methodName = "handleSignal";
            try {
                method = cls.getMethod(methodName);
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
