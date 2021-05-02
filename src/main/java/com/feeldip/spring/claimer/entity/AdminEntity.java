package com.feeldip.spring.claimer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Unsigned;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Admin")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Unsigned
    @Column(name = "id")
    private long idAdmin;

    @NotNull
    @Column(name = "name")
    private String nameAdmin;

    @NotNull
    @Column(name = "surname")
    private String surnameAdmin;

    @NotNull
    @Column(name = "username")
    private String usernameAdmin;

    @JsonIgnore
    @NotNull
    @Column(name = "password")
    private String passwordAdmin;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

    public AdminEntity() { }

    public AdminEntity(String nameAdmin, String surnameAdmin, String usernameAdmin, String passwordAdmin) {
        this.nameAdmin = nameAdmin;
        this.surnameAdmin = surnameAdmin;
        this.usernameAdmin = usernameAdmin;
        this.passwordAdmin = passwordAdmin;
    }

    public long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getSurnameAdmin() {
        return surnameAdmin;
    }

    public void setSurnameAdmin(String surnameAdmin) {
        this.surnameAdmin = surnameAdmin;
    }

    public String getUsernameAdmin() {
        return usernameAdmin;
    }

    public void setUsernameAdmin(String usernameAdmin) {
        this.usernameAdmin = usernameAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
                "idAdmin=" + idAdmin +
                ", nameAdmin='" + nameAdmin + '\'' +
                ", surnameAdmin='" + surnameAdmin + '\'' +
                ", usernameAdmin='" + usernameAdmin + '\'' +
                ", passwordAdmin='" + passwordAdmin + '\'' +
                ", roleEntity=" + roleEntity +
                '}';
    }

}
