package org.example.dnaconverter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Model representing a record ready for TXT output.
 * Contains RSID, chromosome, position, and final genotype.
 */
@AllArgsConstructor
@Getter
public class TxtRecord {

    private final String rsid;
    private final String chromosome;
    private final String position;
    private final String genotype;
}
