package hu.evocelot.sample.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.evocelot.sample.action.SampleAction;
import hu.evocelot.sample.dto.SampleDto;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Sample RestController.
 * 
 * @author mark.danisovszky
 */
@RestController
public class SampleController {

	private static final Logger LOG = LogManager.getLogger(SampleController.class);

	public SampleController(MeterRegistry meterRegistry) {
		// Create custom application metric.
		this.counter = Counter.builder("sample_endpoint_called")
				.description("The total number of the sample endpoind call")
				.register(meterRegistry);
	}

	@Autowired
	private SampleAction sampleAction;

	@Autowired
	private Tracer tracer;

	private Counter counter;

	/**
	 * Sample root endpoint.
	 * 
	 * @return - the "Hello World" string
	 */
	@GetMapping("/")
	@Operation(summary = "Test endpoint", description = "Endpoint for testing tracing, logging and metics.")
	public String helloWorld() {
		Span span = tracer.spanBuilder("sampleMethodSpan").startSpan();

		LOG.log(Level.INFO, "sample endpoint triggered");

		counter.increment();

		span.end();
		return "Hello world!";
	}

	/**
	 * Calls the sample action.
	 * 
	 * @return - with the "created" string.
	 */
	@GetMapping("/create-sample")
	@Operation(summary = "Create sample entity", description = "Endpoint for creating a sample entity.")
	public ResponseEntity<SampleDto> createSampleEntity() {
		return sampleAction.sampleAction();
	}
}
