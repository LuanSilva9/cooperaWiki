package com.cooperawiki.Wiki.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperawiki.Wiki.domain.models.Content;
import com.cooperawiki.Wiki.infra.mappers.dto.input.ContentInputDto;
import com.cooperawiki.Wiki.services.ContentService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/content")
public class ContentController {
    @Autowired private ContentService contentService;


    @PostMapping("/new")
    public ResponseEntity<Content> createContent(@RequestBody ContentInputDto dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(contentService.createContent(dto));
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<Content>> getContentByCompany(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(contentService.getContentByCompany(id));
    }
    
    @GetMapping("/author/{id}")
    public ResponseEntity<List<Content>> getContentByAuthor(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(contentService.getContentByAuthor(id));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok().body(contentService.getContentById(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable UUID id, ContentInputDto dto) throws Exception{
        return ResponseEntity.ok().body(contentService.updateContent(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Content> deleteContent(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok().body(contentService.deleteContent(id));
    }
}
