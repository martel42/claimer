package com.feeldip.spring.claimer.entity;

import jdk.jfr.Unsigned;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Unsigned
    @Column(name = "id")
    private long idType;

    @NotNull
    @Column(name = "name")
    private String nameType;

    @Column(name = "description")
    private String descriptionType;


    public TypeEntity() { }

    public TypeEntity(String nameType, String descriptionTypej) {
        this.nameType = nameType;
        this.descriptionType = descriptionTypej;
    }

    public long getIdType() {
        return idType;
    }

    public void setIdType(long idType) {
        this.idType = idType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(String descriptionType) {
        this.descriptionType = descriptionType;
    }

}
