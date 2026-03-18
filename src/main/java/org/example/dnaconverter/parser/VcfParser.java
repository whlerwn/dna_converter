package org.example.dnaconverter.parser;


import org.example.dnaconverter.model.VcfRecord;

import java.io.InputStream;
import java.util.List;

/**
 * Parser interface for converting InputStream of VCF into VcfRecord objects.
 */
public interface VcfParser {

    /**
     * Parses the input VCF stream and returns a list of VcfRecords.
     *
     * @param input InputStream containing the VCF file
     * @return List of parsed VcfRecord objects
     */
    List<VcfRecord> parse(InputStream input);
}
