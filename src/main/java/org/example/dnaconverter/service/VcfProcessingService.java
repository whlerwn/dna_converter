package org.example.dnaconverter.service;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Service interface for processing VCF files
 * and converting them into correct TXT format.
 */
public interface VcfProcessingService {
    /**
     * Processes the input VCF stream and writes the converted
     * data to the output stream.
     *
     * @param input  InputStream containing the VCF file
     * @param output OutputStream where the TXT file will be written
     */
    void process(InputStream input, OutputStream output);
}
