package com.example.restservice;

public class Signal {

	private final long id;
	private final int signalID;
	private final String output;

	public Signal(long id, int sid, String out) {
		this.id = id;
		this.signalID = sid;
		this.output = out;
	}

	public long getId() {
		return id;
	}

	public int getSignal() {
		return signalID;
	}

	public String getOutput() { return output; }
}
