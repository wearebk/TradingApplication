package com.example.restservice;

public class Sig2 extends Application {
    public void handleSignal() {
        algo.reverse();
        algo.setAlgoParam(1,80);
        algo.submitToMarket();
    }
}
