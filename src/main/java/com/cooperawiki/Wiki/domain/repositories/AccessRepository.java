package com.cooperawiki.Wiki.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperawiki.Wiki.domain.models.Access;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {
    
}
