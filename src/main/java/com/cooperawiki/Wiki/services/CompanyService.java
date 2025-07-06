package com.cooperawiki.Wiki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperawiki.Wiki.domain.enums.Theme;
import com.cooperawiki.Wiki.domain.models.Access;
import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.domain.models.Config;
import com.cooperawiki.Wiki.domain.models.Role;
import com.cooperawiki.Wiki.domain.models.User;
import com.cooperawiki.Wiki.domain.repositories.AccessRepository;
import com.cooperawiki.Wiki.domain.repositories.CompanyRepository;
import com.cooperawiki.Wiki.domain.repositories.ConfigRepository;
import com.cooperawiki.Wiki.domain.repositories.RoleRepository;
import com.cooperawiki.Wiki.infra.mappers.dto.input.CompanyInputDto;
import com.cooperawiki.Wiki.infra.mappers.dto.input.ConfigInputDto;
import com.cooperawiki.Wiki.infra.mappers.dto.input.RoleInputDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CompanyService {
    @Autowired private CompanyRepository companyRepository;
    @Autowired private ConfigRepository configRepository;
    @Autowired private AccessRepository accessRepository;
    @Autowired private RoleRepository roleRepository;

    @Autowired private UserService userService;


    public List<Company> getAllCompanys() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id)  throws Exception {
        return companyRepository.findById(id).orElseThrow(() -> new Exception("Empresa / Cooperativa não encontrada"));
    }

    public Company createCompany(CompanyInputDto dto) throws Exception {
        User representanteMaster = userService.getUserById(dto.masterId());

        Config config = createDefaultConfig(dto);

        Company newCompany = companyRepository.save(new Company(dto, representanteMaster, config));

        Role master = createRole(new RoleInputDto(newCompany.getId(), "MASTER"));

        Access newAccess = new Access(representanteMaster, newCompany, master);

        accessRepository.save(newAccess);

        return newCompany;
    }

    public Config createDefaultConfig(CompanyInputDto dto) {
        ConfigInputDto newObjectDefault = new ConfigInputDto(Theme.LIGHT, true, dto.name());

        Config newConfig = new Config(newObjectDefault);

        return configRepository.save(newConfig);
    }

    public Role createRole(RoleInputDto dto) throws Exception{
        Company company = getCompanyById(dto.companyId());

        Role newRole = new Role(company, dto);

        return roleRepository.save(newRole);
    }
}
