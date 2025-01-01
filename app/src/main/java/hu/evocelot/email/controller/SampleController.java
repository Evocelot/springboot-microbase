package hu.evocelot.email.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

/**
 * Sample RestController.
 * 
 * @author mark.danisovszky
 */
@RestController
public class SampleController {

	private static final Logger LOG = LogManager.getLogger(SampleController.class);

	// Sample Counter (for creating custom application metric).
	private Counter counter;

	public SampleController(MeterRegistry meterRegistry) {
		// Create custom application metric.
        this.counter = Counter.builder("sample_endpoint_called")
			.description("The total number of the sample endpoind call")
			.register(meterRegistry);
	}
	
	// For create custom tracing spans.
	@Autowired
    private Tracer tracer;

	/**
	 * Sample root endpoint.
	 * 
	 * @return - the "Hello World" string
	 */
    @GetMapping("/")
	public String helloWorld() {
		Span span = tracer.spanBuilder("sampleMethodSpan").startSpan();

		LOG.log(Level.INFO, "sample endpoint triggered");

		counter.increment();

		span.end();
		return "Hello world!";
	}
}
