package com.cooperawiki.Wiki.domain.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.domain.models.Topic;
import com.cooperawiki.Wiki.domain.models.User;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID>{
    List<Topic> findByCompany(Company company);
    List<Topic> findByAuthor(User author);
}
