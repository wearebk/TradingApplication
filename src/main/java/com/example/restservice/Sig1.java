package com.example.restservice;

public class Sig1 extends Application {
    public void handleSignal() {
        this.algo.setUp();
        this.algo.setAlgoParam(1,60);
        this.algo.performCalc();
        this.algo.submitToMarket();
    }
}
