package com.cooperawiki.Wiki.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperawiki.Wiki.domain.models.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long>{
    
}
