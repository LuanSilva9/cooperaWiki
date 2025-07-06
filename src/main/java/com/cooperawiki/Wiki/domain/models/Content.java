package com.cooperawiki.Wiki.domain.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.cooperawiki.Wiki.domain.enums.TypeContent;
import com.cooperawiki.Wiki.infra.mappers.dto.input.ContentInputDto;

import jakarta.persistence.Column;
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
@Table(name = "contents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "head_id")
    private Content head;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    private String title;

    @Column(length = 10000)
    private String contentMarkdown;

    private Integer relevance;
    private TypeContent typeContent;


    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Content(ContentInputDto dto, User author, Company company, Content head) {
        this.author = author;
        this.company = company;
        this.title = dto.title();
        this.contentMarkdown = dto.contentMarkdown();
        this.typeContent = dto.typeContent();

        this.relevance = 0;

        this.createdAt = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.head = head;
    }
}