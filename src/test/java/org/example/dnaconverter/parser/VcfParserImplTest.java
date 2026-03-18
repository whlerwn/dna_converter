package org.example.dnaconverter.parser;

import org.example.dnaconverter.model.VcfRecord;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VcfParserImplTest {

    private final VcfParser parser = new VcfParserImpl();

    @Test
    void testParseValidVcf() {
        String vcfContent = "#CHROM POS ID REF ALT QUAL FILTER INFO FORMAT cxrp57\n" +
                "chr1\t752721\trs3131972\tA\tG\t.\t.\t.\tGT\t0|1\n" +
                "chr1\t759036\trs114525117\tG\tA\t.\t.\t.\tGT\t1|0\n";

        List<VcfRecord> records = parser.parse(new ByteArrayInputStream(vcfContent.getBytes()));

        assertEquals(2, records.size());
        assertEquals("chr1", records.get(0).getChromosome());
        assertEquals("0|1", records.get(0).getGenotype());
    }

    @Test
    void testParseEmptyFile() {
        List<VcfRecord> records = parser.parse(new ByteArrayInputStream("".getBytes()));
        assertTrue(records.isEmpty());
    }
}