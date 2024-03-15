package uk.gov.hmcts.opal.service;

import lombok.SneakyThrows;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.hmcts.opal.dto.print.Address;
import uk.gov.hmcts.opal.dto.print.Data;
import uk.gov.hmcts.opal.dto.print.DefendantAddress;
import uk.gov.hmcts.opal.dto.print.Document;
import uk.gov.hmcts.opal.dto.print.General;
import uk.gov.hmcts.opal.dto.print.Header;
import uk.gov.hmcts.opal.dto.print.Info;
import uk.gov.hmcts.opal.dto.print.Job;
import uk.gov.hmcts.opal.dto.print.JobCentreAddress;
import uk.gov.hmcts.opal.dto.print.Offences;
import uk.gov.hmcts.opal.dto.print.Schema;
import uk.gov.hmcts.opal.entity.PrintDefinition;
import uk.gov.hmcts.opal.repository.PrintDefinitionRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PrintServiceTest {

    @Mock
    private FopFactory fopFactory;

    @Mock
    private TransformerFactory transformerFactory;

    @Mock
    private PrintDefinitionRepository printDefinitionRepository;

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
        when(printDefinitionRepository.findByDocTypeAndTemplateId(any(), any())).thenReturn(buildPrintDefinition());
        when(transformerFactory.newTransformer(any(StreamSource.class))).thenReturn(transformer);
        when(fopFactory.newFop(eq(MimeConstants.MIME_PDF), any(), eq(pdfOutputStream))).thenReturn(null);

        // Call the method
        byte[] result = printService.generatePdf(buildPrintRequest());

        // Assert the result
        assertNotNull(result);
    }

    private Document buildPrintRequest() {
        return Document.builder()
            .info(buildInfo())
            .data(buildData())
            .build();
    }

    private Info buildInfo() {
        return Info.builder()
            .general(buildGeneral())
            .build();
    }

    private General buildGeneral() {
        return General.builder()
            .docref("ABD")
            .version("25_0")
            .build();
    }

    private Data buildData() {
        return Data.builder()
            .job(buildJob())
            .build();
    }

    private Job buildJob() {
        return Job.builder()
            .header(buildHeader())
            .division("073")
            .accountnumber("AABB2211")
            .casenumber("222")
            .dob("10/12/2001")
            .defendantname("John Smith")
            .sex("M")
            .schema(buildSchema())
            .defendantaddress(buildDefendantAddress())
            .amountoutstanding("100")
            .defendantindefault("100")
            .ninumber("PA A 01")
            .dwpapnumber("111")
            .offences(buildOffences())
            .dateproduced("01/01/2020")
            .session_id("111")
            .registervalidated("Y")
            .dateoforder("01/01/2021")
            .signature("signature")
            .end_time("01:00")
            .elapsedsecs("20")
            .jobcentrename("Big Job Centre")
            .jobcentreaddress(buildJobCentreAddress())
            .build();
    }

    @SneakyThrows
    private PrintDefinition buildPrintDefinition() {

        // Load XSL file content from test resources
        final String xsltContent = new String(Files.readAllBytes(Path.of("src/test/resources/ABD-25_0-postscript.xsl")));

        return PrintDefinition.builder()
            .printDefinitionId(1L)
            .docType("ABD")
            .templateId("25_0")
            .xslt(xsltContent)
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
            .build();
    }

    private Offences buildOffences() {
        return Offences.builder()
            .accountTotal("2000")
            .build();
    }

    private Schema buildSchema() {
        return Schema.builder()
            .met("22")
            .libra("Schema")
            .build();
    }

    private JobCentreAddress buildJobCentreAddress() {
        return JobCentreAddress.builder()
            .address(Address.builder()
                         .line1("2 High Street")
                         .postcode("W1 1AA")
                         .build())
            .build();
    }
}
