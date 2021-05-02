package com.feeldip.spring.claimer.entity;

import jdk.jfr.Unsigned;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private long idRole;

    @NotNull
    @Column(name = "authority")
    private String authorityRole;

    public RoleEntity() {
    }

    public RoleEntity(String authorityRole) {
        this.authorityRole = authorityRole;
    }

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public String getAuthorityRole() {
        return authorityRole;
    }

    public void setAuthorityRole(String authorityRole) {
        this.authorityRole = authorityRole;
    }
}
