package com.example.restservice;

public class Sig1 extends Application {
    public void handleSignal() {
        algo.setUp();
        algo.setAlgoParam(1,60);
        algo.performCalc();
        algo.submitToMarket();
    }
}
