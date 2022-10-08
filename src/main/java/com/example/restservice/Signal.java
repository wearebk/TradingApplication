package com.example.restservice;

public class Signal {

	private final long id;
	private final int signalID;

	public Signal(long id, int sid) {
		this.id = id;
		this.signalID = sid;
	}

	public long getId() {
		return id;
	}

	public int getContent() {
		return signalID;
	}
}
