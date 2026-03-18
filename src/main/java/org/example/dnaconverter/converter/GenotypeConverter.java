package org.example.dnaconverter.converter;

import org.example.dnaconverter.model.TxtRecord;
import org.example.dnaconverter.model.VcfRecord;

/**
 * Converter interface for converting VcfRecord into TxtRecord.
 */
public interface GenotypeConverter {

    /**
     * Converts a VcfRecord into a TxtRecord (ready for TXT output).
     *
     * @param record VcfRecord to convert
     * @return TxtRecord ready for writing
     */
    TxtRecord convert(VcfRecord record);
}
