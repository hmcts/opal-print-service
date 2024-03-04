package uk.gov.hmcts.opal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.opal.dto.PrintRequest;
import uk.gov.hmcts.opal.service.PrintService;

@RestController
@RequestMapping("/api/print")
@Slf4j
@Tag(name = "Print Controller")
@RequiredArgsConstructor
public class PrintRequestController {

    private final PrintService printService;

    @PostMapping (value = "/generate-pdf", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Generates a PDF from a provided print request")
    public ResponseEntity<byte[]> postPrintRequest(@RequestBody PrintRequest printRequest) {
        log.info(":POST:postPrintRequest: query: \n{}", printRequest.toString());


        byte[] response = printService.generatePdf(printRequest);

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        // Set headers for PDF response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "output.pdf");

        return ResponseEntity.ok().headers(headers).body(response);
    }

}

