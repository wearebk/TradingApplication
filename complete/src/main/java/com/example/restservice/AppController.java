package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	//private static final String template = "Signal: %d";
	private final AtomicLong counter = new AtomicLong();
	private final Application tApp = new Application();

	@GetMapping("/signal/{sid}")
	public Signal signal(@PathVariable("sid") int sid) {
		tApp.handleSignal(sid);
		String output = tApp.getOutput();
		tApp.resetOutput();
		return new Signal(counter.incrementAndGet(), sid, output);
	}
}
