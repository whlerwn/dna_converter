package org.example.dnaconverter.exception;

/**
 * Custom exception for errors during VCF file processing.
 * Extends RuntimeException to avoid mandatory try/catch in controllers.
 */
public class VcfProcessingException extends RuntimeException {

    public VcfProcessingException(String message) {
        super(message);
    }

    public VcfProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
