package uk.gov.hmcts.opal.dto.print;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offences {

    private List<Offence> offenceList;
    private String totalImposition;
    private String accountTotal;
    private String totalPaid;

}
