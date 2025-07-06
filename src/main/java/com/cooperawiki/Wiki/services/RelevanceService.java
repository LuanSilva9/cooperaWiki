package com.cooperawiki.Wiki.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperawiki.Wiki.domain.models.Content;
import com.cooperawiki.Wiki.domain.models.Relevance;
import com.cooperawiki.Wiki.domain.models.User;
import com.cooperawiki.Wiki.domain.repositories.ContentRepository;
import com.cooperawiki.Wiki.domain.repositories.RelevanceRepository;
import com.cooperawiki.Wiki.infra.mappers.dto.input.RelevanceInputDto;

@Service
public class RelevanceService {
    @Autowired private RelevanceRepository relevanceRepository;
    @Autowired private ContentRepository contentRepository;

    @Autowired private ContentService contentService;
    @Autowired private UserService userService;

    public Relevance rate(UUID targetId, RelevanceInputDto dto) throws Exception {
        User user = userService.getUserById(dto.userId());

        Optional<Relevance> reaction = relevanceRepository.findByUserAndTargetIdAndTargetType(user, targetId, dto.targetType());

        if(reaction.isPresent()) {
            throw new Exception("Você já avaliou esse conteudo!");
        }

        Relevance newRelevance = new Relevance(user, targetId, dto.targetType(), dto.liked());

        updateRelevanceByRate(targetId, dto.liked());

        return relevanceRepository.save(newRelevance);
    }

    public Relevance removeRelevance(UUID targetId) throws Exception {
        Relevance relevance = getRelevanceByTargetId(targetId);
        
        discardRelevanceByRate(targetId, relevance.getLiked());

        relevanceRepository.delete(relevance);

        return relevance;
    }

    private Relevance getRelevanceByTargetId(UUID targetId) throws Exception {
        return relevanceRepository.findByTargetId(targetId).orElseThrow(() -> new Exception("Avaliação não encontrada"));
    }

    private void updateRelevanceByRate(UUID contentId, Boolean liked) throws Exception {
        Content content = contentService.getContentById(contentId);

        content.setRelevance(liked ? content.getRelevance() + 1 : content.getRelevance() - 1);

        contentRepository.save(content);
    }


    private void discardRelevanceByRate(UUID contentId, Boolean liked) throws Exception {
        Content content = contentService.getContentById(contentId);

        content.setRelevance(liked ? content.getRelevance() - 1 : content.getRelevance() + 1);

        contentRepository.save(content);
    }
}
