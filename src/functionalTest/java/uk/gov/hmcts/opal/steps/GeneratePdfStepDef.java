package uk.gov.hmcts.opal.steps;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class GeneratePdfStepDef extends BaseStepDef {
    static final Logger log = Logger.getLogger(GeneratePdfStepDef.class.getName());
    ThreadLocal<String> pdfName = new ThreadLocal<>();

    private static void savePdfToFile(byte[] pdfContent, String filePath) {
        log.info("Saving the PDF file: " + filePath);
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            fos.write(pdfContent);
        } catch (IOException e) {
            log.info("Error occurred while saving PDF to file: " + e.getMessage());
        }
    }

    public static String dateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy-HHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @When("I make a request to the generate pdf api with {string}")
    public void generatePdfApi(String pathToFile) throws IOException {
        String jsonToPost = new String(Files.readAllBytes(Paths.get(getBaseJsonPath() + pathToFile)));
        log.info("json payload to test: " + jsonToPost);
        Response response = SerenityRest
            .given()
            .accept("*/*")
            .contentType("application/json")
            .body(jsonToPost)
            .when()
            .post(getTestUrl() + "/api/print/generate-pdf");
        pdfName.set("ABD_" + dateTime() + "_.pdf");
        savePdfToFile(response.getBody().asByteArray(), pdfName.get());
    }

    @When("The pdf is syntactically correct")
    public void pdfSyntaxCorrect() throws IOException {
        try (PDDocument pdf = PDDocument.load(new File(pdfName.get()))) {
            log.info("Loaded PDF file + " + pdfName.get());
        } catch (IOException e) {
            log.info("Error loading PDF file + " + pdfName.get());
            log.info("PDF syntax is incorrect, could not be loaded: " + e);
            throw e;
        }
    }
}
