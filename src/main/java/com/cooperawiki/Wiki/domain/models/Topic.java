package com.cooperawiki.Wiki.domain.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.cooperawiki.Wiki.infra.mappers.dto.input.TopicInputDto;

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
@Table(name = "topics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    private User author;
    
    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private Company company;

    private String title;

    private String contentMarkdown;

    private Integer relevance;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Topic(TopicInputDto dto, User author, Company company) {
        this.author = author;
        this.company = company;
        this.title = dto.title();
        this.contentMarkdown = dto.contentMarkdown();

        this.relevance = 0;
        this.createdAt = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}
