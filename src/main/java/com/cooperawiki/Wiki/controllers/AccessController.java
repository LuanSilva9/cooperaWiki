package com.cooperawiki.Wiki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cooperawiki.Wiki.domain.models.Access;
import com.cooperawiki.Wiki.infra.mappers.dto.input.AccessInputDto;
import com.cooperawiki.Wiki.services.AccessService;

@RestController
@RequestMapping("/api/access")
public class AccessController {

    @Autowired
    private AccessService accessService;

    @PostMapping
    public ResponseEntity<Access> createAccess(@RequestBody AccessInputDto dto) {
        try {
            Access access = accessService.createAccess(dto);
            return ResponseEntity.ok(access);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Access> deleteAccess(@PathVariable Long id) {
        try {
            Access deleted = accessService.deleteAccess(id);
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/role/{roleId}")
    public ResponseEntity<Access> updateAccessRole(@PathVariable Long id, @PathVariable Long roleId) {
        try {
            Access updated = accessService.updateAccess(id, roleId);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Access> getAccessById(@PathVariable Long id) {
        try {
            Access access = accessService.getAccessById(id);
            return ResponseEntity.ok(access);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/company/{company_id}")
    public ResponseEntity<List<Access>> getAccessByCompany(@PathVariable Long company_id) {
        try {
            List<Access> access = accessService.getAllAccessByCompany(company_id);
            return ResponseEntity.ok(access);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
