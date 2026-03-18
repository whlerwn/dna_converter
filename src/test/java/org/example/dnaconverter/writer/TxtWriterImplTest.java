package org.example.dnaconverter.writer;

import org.example.dnaconverter.model.TxtRecord;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TxtWriterImplTest {

    @Test
    void testWriteRecords() {
        List<TxtRecord> records = List.of(
                new TxtRecord("pos_123", "1", "123", "AA"),
                new TxtRecord("rs2", "2", "456", "CT"),
                new TxtRecord("rsX", "X", "789", "GA")
        );

        TxtWriterImpl writer = new TxtWriterImpl();
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        assertDoesNotThrow(() -> writer.write(records, output));

        String result = output.toString(StandardCharsets.UTF_8);

        String[] lines = result.split("\\r?\\n");

        assertEquals("# rsid\tchromosome\tposition\tgenotype", lines[0]);

        assertEquals("pos_123\t1\t123\tAA", lines[1]);
        assertEquals("rs2\t2\t456\tCT", lines[2]);
        assertEquals("rsX\tX\t789\tGA", lines[3]);

        assertEquals(4, lines.length);
    }
}