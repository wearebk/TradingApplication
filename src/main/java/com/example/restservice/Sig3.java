package com.example.restservice;

public class Sig3 extends Application {
    public void handleSignal() {
        algo.setAlgoParam(1,90);
        algo.setAlgoParam(2,15);
        algo.performCalc();
        algo.submitToMarket();
    }
}
