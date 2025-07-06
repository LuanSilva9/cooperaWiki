package com.cooperawiki.Wiki.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperawiki.Wiki.domain.models.Relevance;
import com.cooperawiki.Wiki.infra.mappers.dto.input.RelevanceInputDto;
import com.cooperawiki.Wiki.services.RelevanceService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/rate")
public class RateController {
    @Autowired private RelevanceService relevanceService;
    
    @PutMapping("/{targetId}")
    public ResponseEntity<Relevance> rate(@PathVariable UUID targetId, @RequestBody RelevanceInputDto dto) throws Exception {
        return ResponseEntity.ok().body(relevanceService.rate(targetId, dto));
    }
    
    @DeleteMapping("/{targetId}")
    public ResponseEntity<Relevance> removeRate(@PathVariable UUID targetId) throws Exception {
        return ResponseEntity.ok().body(relevanceService.removeRelevance(targetId));
    }
}
