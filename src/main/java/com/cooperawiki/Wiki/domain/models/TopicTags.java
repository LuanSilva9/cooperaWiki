package com.cooperawiki.Wiki.domain.models;

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
@Table(name = "topic_tags")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "topicId", referencedColumnName = "id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "tagsId", referencedColumnName = "id")
    private TagsDefined tag;

    public TopicTags(Topic topic, TagsDefined tags) {
        this.topic = topic;
        this.tag = tags;
    }
}
