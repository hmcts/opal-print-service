/**
* OPAL Program
*
* MODULE      : insert_print_definition.sql
*
* DESCRIPTION : Inserts rows of data into the PRINT_DEFINITION table. This a simplified pdf example to test the concept
*
* VERSION HISTORY:
*
* Date          Author      Version     Nature of Change
* ----------    -------     --------    ---------------------------------------------------------------------------------------------------------
* 09/03/2024    T Reed      1.0        PO-209 POC Inserts rows of data into the PRINT_DEFINITION table
**/
INSERT INTO print_definition (
  print_definition_id,
  doc_type,
  doc_description,
  dest_main,
  dest_sec1,
  dest_sec2,
  format,
  auto_mode,
  expiry_duration,
  system,
  created_date,
  template_id,
  address_val_element,
  doc_doc_id,
  xslt,
  linked_areas,
  template_file
)
VALUES (
  600000000,
  'AAA',
  'Application for benefits deductions',
  'PORTAL',
  null,
  null,
  'PDF',
  'ON',
  7,
  'LIBRA',
  null,
  '00_1',
  null,
  2366,
  '<?xml version="1.0" encoding="UTF-8"?>
  <xsl:stylesheet version="1.0"
                  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                  xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
      <fo:root>
        <fo:layout-master-set>
          <fo:simple-page-master master-name="simple">
            <fo:region-body margin="1in"/>
          </fo:simple-page-master>
        </fo:layout-master-set>
        <fo:page-sequence master-reference="simple">
          <fo:flow flow-name="xsl-region-body">
            <fo:block font-size="12pt" font-family="sans-serif">
              <xsl:value-of select="document/data/job/accountnumber"/>
            </fo:block>
            <fo:block font-size="12pt" font-family="sans-serif">
              <xsl:value-of select="document/data/job/amountoutstanding"/>
            </fo:block>
            <fo:block font-size="12pt" font-family="sans-serif">
              <xsl:value-of select="document/data/job/casenumber"/>
            </fo:block>
          </fo:flow>
        </fo:page-sequence>
      </fo:root>
    </xsl:template>
  </xsl:stylesheet>',
  null,
  'AAA-00_1-postscript.xsl'
);
