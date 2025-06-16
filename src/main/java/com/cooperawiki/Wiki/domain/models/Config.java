package com.cooperawiki.Wiki.domain.models;

import com.cooperawiki.Wiki.domain.enums.Theme;
import com.cooperawiki.Wiki.infra.mappers.dto.input.ConfigInputDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "configs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Theme theme;
    private Boolean readOnly;
    private String logo;

    public Config(ConfigInputDto dto) {
        this.theme = dto.theme();
        this.readOnly = dto.readOnly();
        this.logo = dto.logo();
    }
}
