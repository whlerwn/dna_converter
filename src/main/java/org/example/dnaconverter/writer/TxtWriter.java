package org.example.dnaconverter.writer;

import org.example.dnaconverter.model.TxtRecord;

import java.io.OutputStream;
import java.util.List;

/**
 * Writer interface for writing TxtRecords to an OutputStream.
 */
public interface TxtWriter {

    /**
     * Writes a list of TxtRecords into the given OutputStream.
     *
     * @param records List of TxtRecords to write
     * @param output  OutputStream to write the TXT file
     */
    void write(List<TxtRecord> records, OutputStream output);
}
