package com.cooperawiki.Wiki.domain.models;


import java.util.UUID;

import com.cooperawiki.Wiki.infra.mappers.dto.input.ReplaceCommentInputDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "replace_comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ReplaceComment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "commentId", referencedColumnName = "id")
    @JsonBackReference("replaceId")
    private Comments comment;

    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    private User author;

    private String replace;
    private Integer relevance;

    public ReplaceComment(Comments comment, User author, ReplaceCommentInputDto dto) {
        this.comment = comment;
        this.author = author;
        this.replace = dto.replace();
        this.relevance = 0;
    }
}
