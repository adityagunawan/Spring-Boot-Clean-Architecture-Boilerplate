package com.salt.boilerplate.api.config;

import com.salt.boilerplate.api.config.dto.GeneralResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<GeneralResponse> generalException(ApiException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new GeneralResponse(false, ex.getMessage(), null), ex.getHttpStatus());
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.error(ex.getMessage(), ex);
        List<KeyValue> data = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            data.add(
                    KeyValue.builder()
                            .key(error.getField())
                            .value(error.getDefaultMessage())
                            .build()
            );
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            data.add(
                    KeyValue.builder()
                            .key(error.getObjectName())
                            .value(error.getDefaultMessage())
                            .build()
            );
        }

        return new ResponseEntity(new GeneralResponse(false,  "error", data), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        String error = ex.getParameterName() + " parameter is missing";

        return new ResponseEntity(new GeneralResponse(false, error, null), HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.error(ex.getMessage(), ex);
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        return new ResponseEntity(new GeneralResponse(false, builder.toString(), null), HttpStatus.METHOD_NOT_ALLOWED);
    }


    @Builder
    @Data
    public static class KeyValue{
        private String key;

        private String value;
    }
}
