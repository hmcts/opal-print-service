package uk.gov.hmcts.opal.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.opal.dto.PrintRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

@Service
@Slf4j(topic = "PrintService")
public class PrintService {

    // FOP Configuration
    private final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

    public byte[] generatePdf(PrintRequest request) {
        try {
            // Load XSLT template
            Source xslt = new StreamSource(new File("src/main/resources/ABD-25_0-postscript.xsl"));

            // Create a transformer factory
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(xslt);

            // Convert Java object to XML
            String xmlData = convertObjectToXml(request);
            Source xmlSource = new StreamSource(new StringReader(xmlData));

            // Create PDF bytes
            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            FopFactory fopFactory = FopFactory.newInstance(new File("src/main/resources/fop.xml").toURI());
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

    private String convertObjectToXml(PrintRequest request) {
        try {
            // Create JAXBContext for the class of the object
            JAXBContext context = JAXBContext.newInstance(PrintRequest.class);

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
