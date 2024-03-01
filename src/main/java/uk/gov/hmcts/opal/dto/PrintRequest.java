package uk.gov.hmcts.opal.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.hmcts.opal.dto.print.DefendantAddress;
import uk.gov.hmcts.opal.dto.print.Header;
import uk.gov.hmcts.opal.dto.print.Offences;
import uk.gov.hmcts.opal.dto.print.Schema;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class PrintRequest {

    private Header header;
    private String division;
    private String accountNumber;
    private String caseNumber;
    private String dob;
    private String defendantName;
    private String sex;
    private Schema schema;
    private DefendantAddress defendantAddress;
    private String amountOutstanding;
    private String defendantInDefault;
    private String dwpapNumber;
    private Offences offences;
    private String dateProduced;
    private String sessionId;
    private String registerValidated;
    private String dateOfOrder;
    private String signature;
    private String endTime;
    private String elapsedSecs;

}
