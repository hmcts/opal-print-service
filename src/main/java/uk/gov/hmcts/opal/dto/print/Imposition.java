package uk.gov.hmcts.opal.dto.print;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imposition {

    private String impositioncode;
    private String impositiontype;
    private String impositiontext;
    private String amountimposed;
    private String paid;
    private String balance;
    private String creditorname;
}
