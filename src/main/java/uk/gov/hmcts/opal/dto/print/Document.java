package uk.gov.hmcts.opal.dto.print;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Document {

    private Info info;
    private Data data;
}
