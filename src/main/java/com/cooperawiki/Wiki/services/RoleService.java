package com.cooperawiki.Wiki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.domain.models.Role;
import com.cooperawiki.Wiki.domain.repositories.RoleRepository;
import com.cooperawiki.Wiki.infra.mappers.dto.input.RoleInputDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {
    @Autowired private RoleRepository roleRepository;
    @Autowired private CompanyService companyService;

    // CUD Here

    public Role createRole(RoleInputDto dto) throws Exception{
        Company company = companyService.getCompanyById(dto.companyId());

        Role newRole = new Role(company, dto);

        return roleRepository.save(newRole);
    }

    public Role deleteRole(Long id) throws Exception {
        Role role = getRoleById(id);

        roleRepository.delete(role);

        return role;
    }

    public Role updateRole(Long id, RoleInputDto dto) throws Exception {
        Role role = getRoleById(id);

        role.setRole(dto.role());

        return roleRepository.save(role);
    }

    public List<Role> getRolesByCompanyId(Long companyId) throws Exception {
        Company company = companyService.getCompanyById(companyId);

        return roleRepository.findByCompany(company);
    }

    public Role getRoleById(Long id) throws Exception{
        return roleRepository.findById(id).orElseThrow(() -> new Exception("Cargo não encontrado"));
    }
}
