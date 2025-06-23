package com.cooperawiki.Wiki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperawiki.Wiki.domain.models.Access;
import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.domain.models.Role;
import com.cooperawiki.Wiki.domain.models.User;
import com.cooperawiki.Wiki.domain.repositories.AccessRepository;
import com.cooperawiki.Wiki.infra.mappers.dto.input.AccessInputDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccessService {
    @Autowired private AccessRepository accessRepository;
    
    @Autowired private CompanyService   companyService;
    @Autowired private UserService      userService;
    @Autowired private RoleService      roleService;

    public Access createAccess(AccessInputDto dto) throws Exception {
        Company company = companyService.getCompanyById(dto.companyId());
        User user = userService.getUserById(dto.userId());
        Role role = roleService.getRoleById(dto.roleId());

        Access newAccess = new Access(user, company, role);

        return accessRepository.save(newAccess);
    }


    public Access deleteAccess(Long id) throws Exception {
        Access access = getAccessById(id);

        accessRepository.delete(access);

        return access;
    }

    public Access updateAccess(Long id, Long roleId) throws Exception {
        Access access = getAccessById(id);

        Role role = roleService.getRoleById(roleId);

        access.setRole(role);

        return accessRepository.save(access);
    }



    public Access getAccessById(Long id) throws Exception {
        Access access = accessRepository.findById(id).orElseThrow(() -> new Exception("Accesso não encontrado"));

        return access;
    }

    public List<Access> getAllAccessByCompany(Long companyId) throws Exception {
        Company company = companyService.getCompanyById(companyId);

        return accessRepository.findByCompany(company);
    }   

    public Boolean existsAccessByCompanyAndUser(Long companyId, Long userId) throws Exception {
        Company company = companyService.getCompanyById(companyId);
        User user = userService.getUserById(userId);

        return accessRepository.existsByCompanyAndUser(company, user);
    }
}
