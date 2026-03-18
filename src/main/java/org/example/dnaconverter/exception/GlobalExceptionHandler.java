package org.example.dnaconverter.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Global exception handler for the application.
 * Catches and handles exceptions thrown by controllers and services.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles custom exceptions related to VCF processing.
     *
     * @param ex VcfProcessingException thrown by the service or controller
     * @return ResponseEntity with 400 Bad Request and the error message
     */
    @ExceptionHandler(VcfProcessingException.class)
    public ResponseEntity<String> handleVcfException(VcfProcessingException ex) {
        log.warn("VCF processing error: {}", ex.getMessage(), ex);

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    /**
     * Handles general IOExceptions, e.g., reading or writing files.
     *
     * @param ex IOException thrown during file processing
     * @return ResponseEntity with 500 Internal Server Error and a generic message
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        log.warn("VCF processing error: {}", ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing file");
    }

    /**
     * Fallback handler for any uncaught exceptions.
     *
     * @param ex Throwable exception
     * @return ResponseEntity with 500 Internal Server Error and a generic message
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleGenericException(Throwable ex) {
        log.error("Unexpected error occurred", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred");
    }
}
