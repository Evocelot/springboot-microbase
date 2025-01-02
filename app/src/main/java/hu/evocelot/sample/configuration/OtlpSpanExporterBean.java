package hu.evocelot.sample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;

/**
 * Bean for setting the OTLP GRPC Span Exporter.
 * 
 * @author mark.danisovszky
 */
@Configuration
public class OtlpSpanExporterBean {

    /**
     * Creates the {@link OtlpGrpcSpanExporter} bean.
     * 
     * @param url - the url of the tracing tool (Jaeger). Can be set with the
     *            TRACING_URL environment variable.
     * @return - the created bean.
     */
    @Bean
    OtlpGrpcSpanExporter otlpGrpcSpanExporter(@Value("${TRACING_URL}") String url) {
        return OtlpGrpcSpanExporter.builder()
                .setEndpoint(url)
                .build();
    }
}
