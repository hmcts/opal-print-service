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

    private String dateimposed;
    private String casenumber;
    private String offencecode;
    private String offencetitle;
    private String ticketnumber;
    private String ctoname;
    private String vehiclereg;
    private String timeofoffence;
    private String placeofoffence;
    private String ntonth;
    private String dateissued;
    private String licenceno;
    private Impositions impositions;
    private String offencetotal;
}
