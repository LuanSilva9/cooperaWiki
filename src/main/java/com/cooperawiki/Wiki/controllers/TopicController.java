package com.cooperawiki.Wiki.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperawiki.Wiki.domain.models.Topic;
import com.cooperawiki.Wiki.infra.mappers.dto.input.TopicInputDto;
import com.cooperawiki.Wiki.services.TopicService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired private TopicService topicService;

    @PostMapping("/new")
    public ResponseEntity<Topic> createTopic(@RequestBody TopicInputDto dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.createTopic(dto));
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<Topic>> getTopicByCompany(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(topicService.getTopicByCompany(id));
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Topic>> getTopicByAuthor(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(topicService.getTopicByAuthor(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok().body(topicService.getTopicById(id));
    }
    
    
    
}
