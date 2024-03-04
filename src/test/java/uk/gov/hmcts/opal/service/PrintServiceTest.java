package uk.gov.hmcts.opal.service;

import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.hmcts.opal.dto.PrintRequest;

import java.io.ByteArrayOutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PrintServiceTest {

    @Mock
    private FopFactory fopFactory;

    @Mock
    private TransformerFactory transformerFactory;

    @InjectMocks
    private PrintService printService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGeneratePdf() throws Exception {
        // Mock input
        PrintRequest printRequest = new PrintRequest();

        // Mock dependencies
        Transformer transformer = mock(Transformer.class);
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        when(transformerFactory.newTransformer(any(StreamSource.class))).thenReturn(transformer);
        when(fopFactory.newFop(eq(MimeConstants.MIME_PDF), any(), eq(pdfOutputStream))).thenReturn(null);

        // Call the method
        byte[] result = printService.generatePdf(printRequest);

        // Verify dependencies are called
        verify(transformerFactory).newTransformer(any(StreamSource.class));
        verify(fopFactory).newFop(eq(MimeConstants.MIME_PDF), any(), eq(pdfOutputStream));

        // Assert the result
        assertNotNull(result);
    }
}
