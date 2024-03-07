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

    private String impositionCode;
    private String impositionText;
    private String impositionType;
    private String amountImposed;
    private String paid;
    private String balance;
}
