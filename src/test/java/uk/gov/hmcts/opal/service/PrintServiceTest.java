package uk.gov.hmcts.opal.service;

import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.hmcts.opal.dto.PrintRequest;
import uk.gov.hmcts.opal.dto.print.Address;
import uk.gov.hmcts.opal.dto.print.DefendantAddress;
import uk.gov.hmcts.opal.dto.print.Header;
import uk.gov.hmcts.opal.dto.print.Offences;
import uk.gov.hmcts.opal.dto.print.Schema;

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

        // Mock dependencies
        Transformer transformer = mock(Transformer.class);
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        when(transformerFactory.newTransformer(any(StreamSource.class))).thenReturn(transformer);
        when(fopFactory.newFop(eq(MimeConstants.MIME_PDF), any(), eq(pdfOutputStream))).thenReturn(null);

        // Call the method
        byte[] result = printService.generatePdf(buildPrintRequest());

        // Assert the result
        assertNotNull(result);
    }

    private PrintRequest buildPrintRequest() {
        return PrintRequest.builder()
            .accountNumber("12345")
            .caseNumber("2")
            .amountOutstanding("100")
            .dateOfOrder("10/12/2000")
            .dateProduced("10/12/2000")
            .defendantAddress(buildDefendantAddress())
            .dob("10/12/2000")
            .defendantInDefault("true")
            .defendantName("John Smith")
            .division("10")
            .dwpapNumber("1")
            .elapsedSecs("1000")
            .endTime("1000")
            .sex("M")
            .header(buildHeader())
            .offences(buildOffences())
            .registerValidated("true")
            .schema(buildSchema())
            .sessionId("AA11")
            .signature("Signature")
            .build();
    }

    private DefendantAddress buildDefendantAddress() {
        return DefendantAddress.builder()
            .address(Address.builder()
                         .line1("1 High Street")
                         .postcode("W1 1AA")
                         .build())
            .name("John Smith")
            .build();
    }

    private Header buildHeader() {
        return Header.builder()
            .code("code")
            .line1("1 High Street")
            .courtName("The court")
            .build();
    }

    private Offences buildOffences() {
        return Offences.builder()
            .accountTotal("2000")
            .totalImposition("10000")
            .build();
    }

    private Schema buildSchema() {
        return Schema.builder()
            .met("22")
            .libra("Schema")
            .build();
    }
}
