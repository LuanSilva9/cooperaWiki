package com.cooperawiki.Wiki.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.domain.models.Topic;
import com.cooperawiki.Wiki.domain.models.User;
import com.cooperawiki.Wiki.domain.repositories.TopicRepository;
import com.cooperawiki.Wiki.infra.mappers.dto.input.TopicInputDto;

@Service
public class TopicService {
    @Autowired private TopicRepository topicRepository;
    @Autowired private CompanyService companyService;
    @Autowired private UserService userService;
    @Autowired private AccessService accessService;

    public Topic createTopic(TopicInputDto dto) throws Exception {
        User user = userService.getUserById(dto.authorId());
        Company company = companyService.getCompanyById(dto.companyId());

        boolean hasAccess = accessService.existsAccessByCompanyAndUser(dto.companyId(), dto.authorId());

        if (!hasAccess) {
            throw new Exception("Usuário não tem acesso à empresa e não pode criar tópicos.");
        }

        Topic newTopic = new Topic(dto, user, company);
        
        return topicRepository.save(newTopic);
    }

    public List<Topic> getTopicByCompany(Long companyId) throws Exception {
        Company company = companyService.getCompanyById(companyId);

        return topicRepository.findByCompany(company);
    }

    public List<Topic> getTopicByAuthor(Long authorId) throws Exception {
        User author = userService.getUserById(authorId);

        return topicRepository.findByAuthor(author);
    }

    public Topic getTopicById(UUID id) throws Exception {
        return topicRepository.findById(id).orElseThrow(() -> new Exception("Topico não encontrado"));
    }
}
