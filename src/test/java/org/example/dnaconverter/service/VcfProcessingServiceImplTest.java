package org.example.dnaconverter.service;

import org.example.dnaconverter.converter.GenotypeConverter;
import org.example.dnaconverter.model.TxtRecord;
import org.example.dnaconverter.model.VcfRecord;
import org.example.dnaconverter.parser.VcfParser;
import org.example.dnaconverter.writer.TxtWriter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.mockito.Mockito.*;

class VcfProcessingServiceImplTest {

    @Test
    void testProcessPipeline() throws Exception {
        VcfParser parser = mock(VcfParser.class);
        GenotypeConverter converter = mock(GenotypeConverter.class);
        TxtWriter writer = mock(TxtWriter.class);

        VcfProcessingService service = new VcfProcessingServiceImpl(parser, converter, writer);

        ByteArrayInputStream input = new ByteArrayInputStream("dummy".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        VcfRecord record = new VcfRecord("chr1", "123", "rs1", "A", "G", "0|1");
        TxtRecord txt = new TxtRecord("rs1", "1", "123", "AG");

        when(parser.parse(input)).thenReturn(List.of(record));
        when(converter.convert(record)).thenReturn(txt);

        service.process(input, output);

        verify(parser, times(1)).parse(input);
        verify(converter, times(1)).convert(record);
        verify(writer, times(1)).write(List.of(txt), output);
    }
}