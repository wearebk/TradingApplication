package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	//private static final String template = "Signal: %d";
	private final AtomicLong counter = new AtomicLong();
	private Application app = new Application();

	@GetMapping("/signal/{sid}")
	public Signal signal(@PathVariable("sid") int sid) {
		this.app.handleSignal(sid);

		return new Signal(counter.incrementAndGet(), sid);
	}

	/*@GetMapping("/greeting")
	public Signal signal(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Signal(counter.incrementAndGet(), String.format(template, name));
	}*/
}
