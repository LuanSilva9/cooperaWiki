package com.cooperawiki.Wiki.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.domain.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    List<Role> findByCompany(Company company);
}
