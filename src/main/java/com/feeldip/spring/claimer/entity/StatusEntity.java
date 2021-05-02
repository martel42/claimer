package com.feeldip.spring.claimer.entity;

import jdk.jfr.Unsigned;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Unsigned
    @Column(name = "id")
    private long idStatus;

    @NotNull
    @Column(name = "name")
    private String nameStatus;


    public StatusEntity() { }

    public StatusEntity(int idStatus, String nameStatus) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }

    public long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(long idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

}
