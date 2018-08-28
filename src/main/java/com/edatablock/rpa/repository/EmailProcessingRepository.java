package com.edatablock.rpa.repository;

import com.edatablock.rpa.domain.EmailProcessing;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EmailProcessing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmailProcessingRepository extends JpaRepository<EmailProcessing, Long> {

}
