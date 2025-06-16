package com.cooperawiki.Wiki.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperawiki.Wiki.domain.models.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID>{
}
