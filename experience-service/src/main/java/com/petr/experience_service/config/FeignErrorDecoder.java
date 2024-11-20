package com.petr.experience_service.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignErrorDecoder implements ErrorDecoder {

    private final static Logger LOGGER = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400 -> LOGGER.error("Request contains errors");
            case 404 -> LOGGER.error("Incorrect url");
            case 500, 503 -> LOGGER.error("Industry service is not available");
            default -> {
                return new Exception(response.reason());
            }
        }
        return null;
    }
}
