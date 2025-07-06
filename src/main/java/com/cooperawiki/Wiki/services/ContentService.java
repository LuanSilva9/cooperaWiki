package com.cooperawiki.Wiki.services;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperawiki.Wiki.domain.enums.TypeContent;
import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.domain.models.Content;
import com.cooperawiki.Wiki.domain.models.User;
import com.cooperawiki.Wiki.domain.repositories.ContentRepository;
import com.cooperawiki.Wiki.infra.mappers.dto.input.ContentInputDto;

@Service
public class ContentService {
    @Autowired private ContentRepository ContentRepository;
    @Autowired private CompanyService companyService;
    @Autowired private UserService userService;
    @Autowired private AccessService accessService;

    public Content createContent(ContentInputDto dto) throws Exception {
        User user = userService.getUserById(dto.authorId());
        Company company = companyService.getCompanyById(dto.companyId());
        
        if (!accessService.existsAccessByCompanyAndUser(dto.companyId(), dto.authorId())) {
            throw new Exception("Usuário não tem acesso à empresa e não pode criar conteúdos.");
        }

        Content head = null;

        if (dto.typeContent() != TypeContent.TOPIC) {
            head = getContentById(dto.head());

            if (head == null) {
                throw new Exception("Conteúdo pai não encontrado.");
            }
        }

        Content newContent = new Content(dto, user, company, head);
        return ContentRepository.save(newContent);
    }

    public Content updateContent(UUID id, ContentInputDto dto) throws Exception {
        Content Content = getContentById(id);

        Content.setTitle(dto.title());
        Content.setContentMarkdown(dto.contentMarkdown());
        Content.setUpdatedAt(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));

        return ContentRepository.save(Content);
    }

    public Content deleteContent(UUID id)  throws Exception {
        Content Content = getContentById(id);

        ContentRepository.delete(Content);

        return Content;
    }

    public List<Content> getContentByCompany(Long companyId) throws Exception {
        Company company = companyService.getCompanyById(companyId);

        return ContentRepository.findByCompany(company);
    }

    public List<Content> getContentByAuthor(Long authorId) throws Exception {
        User author = userService.getUserById(authorId);

        return ContentRepository.findByAuthor(author);
    }

    public Content getContentById(UUID id) throws Exception {
        return ContentRepository.findById(id).orElseThrow(() -> new Exception("Conteudo não encontrado"));
    }

}
