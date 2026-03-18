package org.example.dnaconverter.parser;

import org.example.dnaconverter.exception.VcfProcessingException;
import org.example.dnaconverter.model.VcfRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of VcfParser.
 * Reads a VCF file and parses it into domain objects.
 */
@Component
public class VcfParserImpl implements VcfParser {

    @Override
    public List<VcfRecord> parse(InputStream input) {
        List<VcfRecord> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.startsWith("#")) continue;

                String[] parts = line.trim().split("\\s+");
                if (parts.length < 5) continue;

                String ref = parts[3];
                String alt = parts[4];

                if (ref.length() != 1 || alt.length() != 1) continue;

                VcfRecord record = new VcfRecord(
                        parts[0], // chromosome
                        parts[1], // position
                        parts[2], // ID (rsid)
                        ref,      // REF
                        alt,      // ALT
                        parts[parts.length - 1] // genotype
                );

                records.add(record);
            }
        } catch (IOException e) {
            throw new VcfProcessingException("Error reading VCF file", e);
        }

        return records;
    }
}
