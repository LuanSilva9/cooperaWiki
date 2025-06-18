package com.cooperawiki.Wiki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperawiki.Wiki.domain.models.Company;
import com.cooperawiki.Wiki.infra.mappers.dto.input.CompanyInputDto;
import com.cooperawiki.Wiki.services.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanys() {
        return ResponseEntity.ok().body(companyService.getAllCompanys());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok().body(companyService.getCompanyById(id));
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyInputDto dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(dto));
    }
    
}
