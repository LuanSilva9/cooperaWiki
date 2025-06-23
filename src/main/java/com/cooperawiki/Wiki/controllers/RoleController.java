package com.cooperawiki.Wiki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cooperawiki.Wiki.domain.models.Role;
import com.cooperawiki.Wiki.infra.mappers.dto.input.RoleInputDto;
import com.cooperawiki.Wiki.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleInputDto dto) throws Exception {
        Role createdRole = roleService.createRole(dto);
        return ResponseEntity.ok(createdRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleInputDto dto) {
        try {
            Role updatedRole = roleService.updateRole(id, dto);
            return ResponseEntity.ok(updatedRole);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable Long id) {
        try {
            Role deletedRole = roleService.deleteRole(id);
            return ResponseEntity.ok(deletedRole);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        try {
            Role role = roleService.getRoleById(id);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Role>> getRolesByCompanyId(@PathVariable Long companyId) throws Exception {
        List<Role> roles = roleService.getRolesByCompanyId(companyId);
        return ResponseEntity.ok(roles);
    }
}