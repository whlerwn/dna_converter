package org.example.dnaconverter.converter;

import org.example.dnaconverter.model.TxtRecord;
import org.example.dnaconverter.model.VcfRecord;
import org.springframework.stereotype.Component;

@Component
public class GenotypeConverterImpl implements GenotypeConverter {

    @Override
    public TxtRecord convert(VcfRecord record) {
        String chromosome = normalizeChromosome(record.getChromosome());
        String genotype = convertGenotype(record.getGenotype(), record.getRef(), record.getAlt());
        String rsid = buildRsid(record, chromosome);

        return new TxtRecord(rsid, chromosome, record.getPosition(), genotype);
    }

    private static String normalizeChromosome(String chr) {
        String c = chr.replace("chr", "");

        return switch (c) {
            case "23" -> "X";
            case "24" -> "Y";
            case "M", "MT" -> "MT";
            default -> c;
        };
    }

    private static String convertGenotype(String gt, String ref, String alt) {
        return switch (gt) {
            case "0|0", "0/0" -> ref + ref;
            case "0|1", "1|0", "0/1", "1/0" -> ref + alt;
            case "1|1", "1/1" -> alt + alt;
            case "./.", ".|." -> "--";
            default -> "NN";
        };
    }

    private static String buildRsid(VcfRecord record, String chromosome) {
        if (!record.getId().equals(".")) {
            return record.getId().split(";")[0];
        }

        return chromosome + "_" + record.getPosition();
    }
}