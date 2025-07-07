package com.cooperawiki.Wiki.domain.models;

import com.cooperawiki.Wiki.domain.enums.TypeRoleAccess;
import com.cooperawiki.Wiki.infra.mappers.dto.input.RoleInputDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles_per_company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private Company company;

    private String role;
    
    private TypeRoleAccess typeRoleAccess;

    public Role(Company company, RoleInputDto dto) {
        this.company = company;
        this.role = dto.role();
    }

}
