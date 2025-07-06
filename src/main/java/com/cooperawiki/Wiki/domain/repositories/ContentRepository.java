package com.cooperawiki.Wiki.domain.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.domain.models.Content;
import com.cooperawiki.Wiki.domain.models.User;

@Repository
public interface ContentRepository extends JpaRepository<Content, UUID>{
    List<Content> findByCompany(Company company);
    List<Content> findByAuthor(User author);
}
