package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.example.demo.utils.MappingUtils.EMPTY_JSON;

public final class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    public static final HttpHeaders EMPTY_HEADERS;

    static {
        EMPTY_HEADERS = new HttpHeaders();
        EMPTY_HEADERS.setContentType(MediaType.APPLICATION_JSON);
    }

    private HttpUtils() {
    }

    public static ResponseEntity<String> jsonGetRequest(String requestPath) {
        return executeHttpRequest(HttpMethod.GET, requestPath, EMPTY_HEADERS, EMPTY_JSON);
    }

    public static ResponseEntity<String> jsonPostRequest(String requestPath, Object requestBody) {
        return executeHttpRequest(HttpMethod.POST, requestPath, EMPTY_HEADERS, requestBody);
    }

    private static ResponseEntity<String> executeHttpRequest(HttpMethod httpMethod, String requestPath,
                                                             HttpHeaders headers, Object requestBody) {
        HttpEntity<?> request = new HttpEntity<>(MappingUtils.convertObjectToJson(requestBody), headers);
        try {
            return new RestTemplate().exchange(requestPath, httpMethod, request, String.class);
        } catch (HttpStatusCodeException ex) {
            logger.error("Request to {} failed with status: {} and message: {}.", requestPath, ex.getStatusCode(),
                    ex.getResponseBodyAsString(), ex);
            return new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        } catch (RestClientException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
