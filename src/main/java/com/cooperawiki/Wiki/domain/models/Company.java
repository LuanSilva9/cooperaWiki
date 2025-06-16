package com.cooperawiki.Wiki.domain.models;

import java.util.List;

import com.cooperawiki.Wiki.infra.mappers.dto.input.CompanyInputDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "masterId", referencedColumnName = "id")
    private User master;

    @ManyToOne
    @JoinColumn(name = "configId", referencedColumnName = "id")
    private Config configs;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("companyId")
    private List<Access> access;

    public Company(CompanyInputDto dto, User master, Config configs) {
        this.name = dto.name();
        this.cnpj = dto.cnpj();
        this.master = master;
        this.configs = configs;
    }
}
