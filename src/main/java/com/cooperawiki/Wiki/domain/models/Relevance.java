package com.cooperawiki.Wiki.domain.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.cooperawiki.Wiki.domain.enums.TypeContent;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "relevance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Relevance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonManagedReference("likedUserId")
    private User user;

    private UUID targetId;
    private TypeContent targetType; 

    private Boolean liked; 

    private ZonedDateTime reactedAt;

    public Relevance(User user, UUID targetId, TypeContent typeRelevance, Boolean liked) {
        this.user = user;
        this.targetId = targetId;
        this.targetType = typeRelevance;
        this.liked = liked;
        
        this.reactedAt = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}
