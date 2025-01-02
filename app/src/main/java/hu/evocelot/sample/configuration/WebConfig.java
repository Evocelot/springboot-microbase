package hu.evocelot.sample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import hu.evocelot.sample.interceptor.LoggingInterceptor;

/**
 * Config class for configuring Web Mvc.
 * 
 * @author mark.danisovszky
 */
@Component
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Adding logging interceptor for handling request / response logs.
        registry.addInterceptor(loggingInterceptor);
    }
}
