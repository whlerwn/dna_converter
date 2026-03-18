package org.example.dnaconverter.integration;

import org.example.dnaconverter.converter.GenotypeConverterImpl;
import org.example.dnaconverter.parser.VcfParserImpl;
import org.example.dnaconverter.service.VcfProcessingService;
import org.example.dnaconverter.service.VcfProcessingServiceImpl;
import org.example.dnaconverter.writer.TxtWriterImpl;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VcfProcessingIntegrationTest {

    @Test
    void testProcessVcfFile() throws Exception {

        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("testdata/dna.vcf");
        InputStream expectedStream = getClass().getClassLoader()
                .getResourceAsStream("testdata/dna_result.txt");

        if (inputStream == null || expectedStream == null) {
            throw new IllegalStateException("Test files not found in classpath!");
        }

        File outputFile = File.createTempFile("dna_result_out", ".txt");
        outputFile.deleteOnExit();

        VcfProcessingService service = new VcfProcessingServiceImpl(
                new VcfParserImpl(),
                new GenotypeConverterImpl(),
                new TxtWriterImpl()
        );

        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            service.process(inputStream, outputStream);
        }

        String expected = new String(expectedStream.readAllBytes());

        String actual = new String(java.nio.file.Files.readAllBytes(outputFile.toPath()));

        assertEquals(expected.trim(), actual.trim());
    }
}
