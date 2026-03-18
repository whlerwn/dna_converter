package org.example.dnaconverter.service;

import org.example.dnaconverter.converter.GenotypeConverter;
import org.example.dnaconverter.model.TxtRecord;
import org.example.dnaconverter.model.VcfRecord;
import org.example.dnaconverter.parser.VcfParser;
import org.example.dnaconverter.writer.TxtWriter;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Implementation of VcfProcessingService.
 * This class orchestrates the entire conversion pipeline:
 * parsing, converting, and writing output.
 */
@Service
public class VcfProcessingServiceImpl implements VcfProcessingService {

    private final VcfParser parser;
    private final GenotypeConverter converter;
    private final TxtWriter writer;

    public VcfProcessingServiceImpl(VcfParser parser,
                                    GenotypeConverter converter,
                                    TxtWriter writer) {
        this.parser = parser;
        this.converter = converter;
        this.writer = writer;
    }

    @Override
    public void process(InputStream input, OutputStream output) {

        List<VcfRecord> records = parser.parse(input);

        List<TxtRecord> converted = records.stream()
                .map(converter::convert)
                .toList();

        writer.write(converted, output);
    }
}
