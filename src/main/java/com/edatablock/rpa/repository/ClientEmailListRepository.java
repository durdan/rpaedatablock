package com.edatablock.rpa.repository;

import com.edatablock.rpa.domain.ClientEmailList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClientEmailList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientEmailListRepository extends JpaRepository<ClientEmailList, Long> {

}
