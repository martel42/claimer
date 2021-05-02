package com.feeldip.spring.claimer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.jfr.Unsigned;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Claimer")
public class ClaimerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Unsigned
    @Column(name = "id")
    private long idUser;

    @NotNull
    @Column(name = "name")
    private String nameUser;

    @NotNull
    @Column(name = "surname")
    private  String surnameUser;

    @NotNull
    @Column (name = "email")
    private String emailUser;


    @NotNull
    @Column (name = "phone")
    private String phoneUser;

    public ClaimerEntity(){ }

    public ClaimerEntity(String nameUser, String surnameUser, String emailUser, String passwordUser, String phoneUser) {
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
        this.emailUser = emailUser;
        this.phoneUser = phoneUser;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getSurnameUser() {
        return surnameUser;
    }

    public void setSurnameUser(String surnameUser) {
        this.surnameUser = surnameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

}
