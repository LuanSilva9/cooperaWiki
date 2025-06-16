package com.cooperawiki.Wiki.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperawiki.Wiki.domain.models.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, UUID>{
    
}
