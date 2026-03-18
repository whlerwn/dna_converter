package org.example.dnaconverter.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dnaconverter.service.VcfProcessingService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * REST controller for handling VCF file conversion requests.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/vcf")
public class VcfController {

    private final VcfProcessingService processingService;

    public VcfController(VcfProcessingService processingService) {
        this.processingService = processingService;
    }

    /**
     * Endpoint to convert a VCF file to correct TXT file.
     *
     * @param file MultipartFile uploaded by the client
     * @return ResponseEntity containing the converted TXT file
     * @throws IOException if file reading fails
     */
    @PostMapping("/convert")
    public ResponseEntity<Resource> convert(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Received file: {}, size: {}", file.getOriginalFilename(), file.getSize());

        InputStream inputStream = file.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        processingService.process(inputStream, outputStream);

        log.info("File processed successfully");

        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + buildOutputFileName(file.getOriginalFilename()) + "\"")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(resource.contentLength())
                .body(resource);
    }

    private String buildOutputFileName(String originalFilename) {
        if (originalFilename == null || originalFilename.isBlank()) {
            return "result.txt";
        }

        return originalFilename.replaceFirst("(?i)\\.vcf$", "") + "_converted.txt";
    }
}
