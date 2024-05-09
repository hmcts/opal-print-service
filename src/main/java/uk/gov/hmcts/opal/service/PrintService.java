package uk.gov.hmcts.opal.service;

import jakarta.transaction.Transactional;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.opal.dto.print.Document;
import uk.gov.hmcts.opal.entity.PrintDefinition;
import uk.gov.hmcts.opal.repository.PrintDefinitionRepository;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j(topic = "PrintService")
public class PrintService {

    private final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

    private final PrintDefinitionRepository printDefinitionRepository;

    public byte[] generatePdf(Document request) {
        try {
            // Get print definition from database
            final PrintDefinition printDef = getPrintDefinition(request.getInfo().getGeneral().getDocref(),
                                                          request.getInfo().getGeneral().getVersion());
            // Load XSLT template
            Source xslt = new StreamSource(new StringReader(printDef.getXslt()));

            // Create a transformer factory
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(xslt);

            // Convert Java object to XML
            String xmlData = convertObjectToXml(request);
            Source xmlSource = new StreamSource(new StringReader(xmlData));

            // Create PDF bytes
            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            FopFactory fopFactory = FopFactory.newInstance(getClass().getClassLoader()
                                                               .getResource("fop.xml").toURI());
            final FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, pdfOutputStream);

            // Apply XSLT transformation
            final Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);

            // Get PDF bytes
            byte[] pdfBytes = pdfOutputStream.toByteArray();

            // Close resources
            pdfOutputStream.close();

            return pdfBytes;
        } catch (Exception e) {

            return null;
        }
    }

    private PrintDefinition getPrintDefinition(String docType, String templateId) {

        return printDefinitionRepository.findByDocTypeAndTemplateId(docType, templateId);
    }

    private String convertObjectToXml(Document request) {
        try {
            // Create JAXBContext for the class of the object
            JAXBContext context = JAXBContext.newInstance(Document.class);

            // Create Marshaller
            Marshaller marshaller = context.createMarshaller();

            // Set properties for formatting
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Marshal the object to XML
            StringWriter writer = new StringWriter();
            marshaller.marshal(request, writer);

            // Get XML as string
            return writer.toString();
        } catch (JAXBException e) {

            return null;
        }
    }
}
