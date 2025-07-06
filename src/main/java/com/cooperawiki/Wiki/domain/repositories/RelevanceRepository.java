package com.cooperawiki.Wiki.domain.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperawiki.Wiki.domain.enums.TypeContent;
import com.cooperawiki.Wiki.domain.models.Relevance;
import com.cooperawiki.Wiki.domain.models.User;

@Repository
public interface RelevanceRepository extends JpaRepository<Relevance, UUID> {
    Optional<Relevance> findByUserAndTargetIdAndTargetType(User user, UUID targetId, TypeContent targetType);
    Optional<Relevance> findByTargetId(UUID targetId);
}
