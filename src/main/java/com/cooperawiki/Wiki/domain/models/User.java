package com.cooperawiki.Wiki.domain.models;

import java.util.List;

import com.cooperawiki.Wiki.infra.mappers.dto.input.UserInputDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "passwd", nullable = false)
    private String passwd;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discordId", referencedColumnName = "id")
    private DiscordAccount discordAccount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("accessUserId")
    private List<Access> access;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("likedUserId")
    private List<Relevance> liked;

    public User(UserInputDto dto, DiscordAccount discordAccount) {
        this.name = dto.name();
        this.passwd = dto.passwd();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.discordAccount = discordAccount;
    }
}
