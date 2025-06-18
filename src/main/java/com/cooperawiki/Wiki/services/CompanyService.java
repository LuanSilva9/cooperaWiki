package com.cooperawiki.Wiki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cooperawiki.Wiki.domain.enums.Theme;
import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.domain.models.Config;
import com.cooperawiki.Wiki.domain.models.User;
import com.cooperawiki.Wiki.domain.repositories.CompanyRepository;
import com.cooperawiki.Wiki.domain.repositories.ConfigRepository;
import com.cooperawiki.Wiki.infra.mappers.dto.input.CompanyInputDto;
import com.cooperawiki.Wiki.infra.mappers.dto.input.ConfigInputDto;

public class CompanyService {
    @Autowired private CompanyRepository companyRepository;

    @Autowired private UserService userService;

    @Autowired private ConfigRepository configRepository;

    public List<Company> getAllCompanys() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(null);
    }

    public Company createCompany(CompanyInputDto dto) throws Exception {
        User representanteMaster = userService.getUser(dto.masterId());
        Config config = createDefaultConfig(dto);

        if(representanteMaster.equals(null)) {
            throw new Exception("Sem representante!"); // Mudar para uma exceção personalizada.
        }

        Company newCompany = new Company(dto, representanteMaster, config);

        return companyRepository.save(newCompany);
    }

    public Config createDefaultConfig(CompanyInputDto dto) {
        ConfigInputDto newObjectDefault = new ConfigInputDto(Theme.LIGHT, true, dto.name());

        Config newConfig = new Config(newObjectDefault);

        return configRepository.save(newConfig);
    }
}
