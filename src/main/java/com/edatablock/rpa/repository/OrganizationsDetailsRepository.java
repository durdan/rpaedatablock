package com.edatablock.rpa.repository;

import com.edatablock.rpa.domain.OrganizationsDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrganizationsDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizationsDetailsRepository extends JpaRepository<OrganizationsDetails, Long> {

}
