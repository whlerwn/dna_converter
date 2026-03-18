package org.example.dnaconverter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Domain model representing a single VCF record.
 * This is an intermediate object after parsing the VCF file.
 */
@Getter
@AllArgsConstructor
public class VcfRecord {

    private final String chromosome;
    private final String position;
    private final String id;
    private final String ref;
    private final String alt;
    private final String genotype;

}
