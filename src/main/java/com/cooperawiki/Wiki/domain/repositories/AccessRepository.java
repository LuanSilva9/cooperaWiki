package com.cooperawiki.Wiki.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperawiki.Wiki.domain.models.Access;
import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.domain.models.User;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {
    List<Access> findByCompany(Company company);
    boolean existsByCompanyAndUser(Company company, User user);

}
