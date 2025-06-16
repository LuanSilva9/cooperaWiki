package com.cooperawiki.Wiki.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "access")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonManagedReference("userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    @JsonManagedReference("companyId")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role role;

    public Access(User user, Company company, Role role) {
        this.user = user;
        this.company = company;
        this.role = role;
    }
}
