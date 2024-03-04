package uk.gov.hmcts.opal.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.opal.dto.PrintRequest;
import uk.gov.hmcts.opal.service.PrintService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PrintRequestControllerTest {

    @Mock
    private PrintService printService;

    @InjectMocks
    private PrintRequestController printRequestController;

    @Test
    void testPostPrintRequest() {
        // Mock input
        PrintRequest printRequest = new PrintRequest();

        // Mock service response
        byte[] mockPdfData = "Mock PDF Data".getBytes();
        when(printService.generatePdf(printRequest)).thenReturn(mockPdfData);

        // Call the controller method
        ResponseEntity<byte[]> responseEntity = printRequestController.postPrintRequest(printRequest);

        // Verify the response
        assertEquals(MediaType.APPLICATION_PDF, responseEntity.getHeaders().getContentType());
        assertEquals("attachment; filename=output.pdf", responseEntity.getHeaders().getContentDisposition());
        assertEquals(mockPdfData, responseEntity.getBody());

        // Verify service method invocation
        verify(printService, times(1)).generatePdf(printRequest);
    }
}
