package org.example.dnaconverter.converter;

import org.example.dnaconverter.model.TxtRecord;
import org.example.dnaconverter.model.VcfRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenotypeConverterImplTest {

    private final GenotypeConverter converter = new GenotypeConverterImpl();


    @Test
    void testConvertRefAltGenotypes() {
        VcfRecord record = new VcfRecord("chr1", "12345", "rs1", "A", "G", "0|1");
        TxtRecord txt = converter.convert(record);

        assertEquals("1", txt.getChromosome().replace("chr", ""));
        assertEquals("rs1", txt.getRsid());
        assertEquals("AG", txt.getGenotype());
    }

    @Test
    void testConvertUnknownId() {
        VcfRecord record = new VcfRecord("chr2", "54321", ".", "C", "T", "1|1");
        TxtRecord txt = converter.convert(record);

        assertEquals("TT", txt.getGenotype());
        assertEquals("2_54321", txt.getRsid());
    }


    @Test
    void testUnknownGenotype() {
        VcfRecord record = new VcfRecord("chrX", "999", "rsX", "G", "A", "2|2");
        TxtRecord txt = converter.convert(record);

        assertEquals("NN", txt.getGenotype());
    }

}