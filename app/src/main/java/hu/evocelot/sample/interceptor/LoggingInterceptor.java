package hu.evocelot.sample.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Logging interceptor for handling request and response logging.
 * 
 * @author mark.danisovszky
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LogManager.getLogger(LoggingInterceptor.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String REQUEST_ID_HEADER_KEY = "REQUEST-ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Wrap the request to enable caching.
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }

        // Get request id from the request header.
        String requestId = (String) request.getAttribute(REQUEST_ID_HEADER_KEY);

        // If the request does not contains request id, generate a new one.
        if (Objects.isNull(requestId)) {
            requestId = UUID.randomUUID().toString();
            request.setAttribute(REQUEST_ID_HEADER_KEY, requestId);
        }

        // Read the request details.
        Map<String, Object> requestDetails = collectRequestDetails((ContentCachingRequestWrapper) request);
        requestDetails.put(REQUEST_ID_HEADER_KEY, requestId);

        try {
            String jsonLog = OBJECT_MAPPER.writeValueAsString(requestDetails);
            LOG.info("Request accepted: {}", jsonLog);
        } catch (JsonProcessingException e) {
            LOG.error("Error serializing request details to JSON:", e);
        }

        // Add request id to the response headers.
        response.addHeader(REQUEST_ID_HEADER_KEY, requestId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        // Read the response details.
        Map<String, Object> responseDetails = collectResponseDetails(response);

        if (ex != null) {
            responseDetails.put("exception", ex.getMessage());
        }

        try {
            String jsonLog = OBJECT_MAPPER.writeValueAsString(responseDetails);
            LOG.info("Response created: {}", jsonLog);
        } catch (JsonProcessingException e) {
            LOG.error("Error serializing response details to JSON:", e);
        }
    }

    private Map<String, Object> collectRequestDetails(ContentCachingRequestWrapper request) {
        Map<String, Object> details = new HashMap<>();
        details.put("method", request.getMethod());
        details.put("url", request.getRequestURL().toString());
        details.put("query", request.getQueryString());

        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        details.put("headers", headers);

        Map<String, String[]> parameters = request.getParameterMap();
        details.put("parameters", parameters);

        try {
            String body = new String(request.getContentAsByteArray(), request.getCharacterEncoding());
            details.put("body", body);
        } catch (Exception e) {
            details.put("bodyError", "Error reading body: " + e.getMessage());
        }

        return details;
    }

    private Map<String, Object> collectResponseDetails(HttpServletResponse response) {
        Map<String, Object> details = new HashMap<>();
        details.put("status", response.getStatus());

        Map<String, String> headers = new HashMap<>();
        for (String headerName : response.getHeaderNames()) {
            headers.put(headerName, response.getHeader(headerName));
        }
        details.put("headers", headers);

        return details;
    }
}
