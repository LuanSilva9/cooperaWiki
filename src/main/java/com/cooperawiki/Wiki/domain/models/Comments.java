package com.cooperawiki.Wiki.domain.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.cooperawiki.Wiki.infra.mappers.dto.input.CommentsInputDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "topicId", referencedColumnName = "id")
    private Topic topic;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("commentId")
    private List<ReplaceComment> replaceComments;

    private String title;
    private String contentMarkdown;
    private Integer relevance;

    private ZonedDateTime createdAt;

    public Comments(User author, Topic topic, CommentsInputDto dto) {
        this.author = author;
        this.topic = topic;
        this.title = dto.title();
        this.contentMarkdown = dto.content();

        this.relevance = 0;
        this.createdAt = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}
