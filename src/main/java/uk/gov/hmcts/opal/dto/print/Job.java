package uk.gov.hmcts.opal.dto.print;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    private Header header;
    private String division;
    private String accountnumber;
    private String casenumber;
    private String dob;
    private String defendantname;
    private String sex;
    private Schema schema;
    private DefendantAddress defendantaddress;
    private String amountoutstanding;
    private String defendantindefault;
    private String ninumber;
    private String dwpapnumber;
    private Offences offences;
    private String dateproduced;

    @JsonProperty("session_id")
    private String sessionId;
    private String registervalidated;
    private String dateoforder;
    private String signature;

    @JsonProperty("session_id")
    private String endTime;
    private String elapsedsecs;
    private String jobcentrename;
    private JobCentreAddress jobcentreaddress;
}
