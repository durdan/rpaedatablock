package com.edatablock.rpa.repository;

import com.edatablock.rpa.domain.TemplateDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TemplateDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TemplateDetailsRepository extends JpaRepository<TemplateDetails, Long> {

}
