package uk.gov.hmcts.opal.steps;

public class BaseStepDef {

    private static final String TEST_URL = System.getenv().getOrDefault("TEST_URL", "http://localhost:4660");
    private static final String JSON_PATH = "src/functionalTest/resources/jsonForPdf/";
    private static final String PDF_OUTPUT_PATH = "src/functionalTest/resources/pdfOutput/";


    protected static String getTestUrl() {
        return TEST_URL;
    }
    protected static String getBaseJsonPath(){ return JSON_PATH;}
    protected static String pdfOutputPath(){ return PDF_OUTPUT_PATH;}



}
