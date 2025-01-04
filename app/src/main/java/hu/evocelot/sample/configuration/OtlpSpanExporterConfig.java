package hu.evocelot.sample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hu.evocelot.sample.constant.ProjectEnvironmentVariables;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;

/**
 * Configuration class for setting the OTLP GRPC Span Exporter.
 * 
 * @author mark.danisovszky
 */
@Configuration
public class OtlpSpanExporterConfig {

    /**
     * Creates the {@link OtlpGrpcSpanExporter} bean.
     * 
     * @param url - TRACING_URL environment variable.
     * @return - the created bean.
     */
    @Bean
    OtlpGrpcSpanExporter otlpGrpcSpanExporter(@Value(ProjectEnvironmentVariables.TRACING_URL) String url) {
        return OtlpGrpcSpanExporter.builder()
                .setEndpoint(url)
                .build();
    }
}
