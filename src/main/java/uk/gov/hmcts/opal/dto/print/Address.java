package uk.gov.hmcts.opal.dto.print;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String line1;
    private String line2;
    private String line3;
    private String postcode;
}
