package uk.gov.hmcts.opal.dto.print;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offence {

    private String caseNumber;
    private String dateImposed;
    private String offenceSuffix;
    private String offenceTitle;
    private String offenceStartDate;
    private String dvla;
    private String dateBegun;
    private Impositions impositions;
}
