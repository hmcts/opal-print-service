package uk.gov.hmcts.opal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.hmcts.opal.entity.PrintDefinition;

@Repository
public interface PrintDefinitionRepository extends JpaRepository<PrintDefinition, Long> {

    PrintDefinition findByDocTypeAndTemplateId(String docType, String templateId);
}
