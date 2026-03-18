package org.example.dnaconverter.writer;

import org.example.dnaconverter.exception.VcfProcessingException;
import org.example.dnaconverter.model.TxtRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Implementation of TxtWriter.
 * Writes TxtRecords to an output stream in the correct TXT format.
 */
@Component
public class TxtWriterImpl implements TxtWriter {

    @Override
    public void write(List<TxtRecord> records, OutputStream output) {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
        try {
            writer.write("# rsid\tchromosome\tposition\tgenotype");
            writer.newLine();

            for (TxtRecord record : records) {
                writer.write(String.format("%s\t%s\t%s\t%s",
                        record.getRsid(),
                        record.getChromosome(),
                        record.getPosition(),
                        record.getGenotype()));
                writer.newLine();
            }

            writer.flush();
        } catch (IOException e) {
            throw new VcfProcessingException("Error writing TXT file", e);
        }
    }
}
