package uk.gov.hmcts.opal.dto.print;

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
    //CHECKSTYLE:OFF
    private String session_id;
    //CHECKSTYLE:ON
    private String registervalidated;
    private String dateoforder;
    private String signature;
    //CHECKSTYLE:OFF
    private String end_time;
    //CHECKSTYLE:ON
    private String elapsedsecs;
    private String jobcentrename;
    private JobCentreAddress jobcentreaddress;
}
