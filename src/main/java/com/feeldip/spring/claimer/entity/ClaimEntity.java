package com.feeldip.spring.claimer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jfr.Unsigned;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "Claim")
public class ClaimEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Unsigned
    @Column(name = "id")
    private long idClaim;

    @NotNull
    @Column(name = "title")
    private String titleClaim;

    @NotNull
    @Column (name = "text")
    private String textClaim;

    @NotNull
    @Column(name = "date")
    @Type(type = "timestamp")
    private Timestamp dateClaim;

    @NotNull
    @Column (name = "answered")
    private boolean isAnswered;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "type_id")
    private TypeEntity typeEntity;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "claimer_id")
    private ClaimerEntity claimerEntity;


    public ClaimEntity() { }

    public ClaimEntity(String titleClaim, String textClaim, Timestamp dateClaim) {
        this.titleClaim = titleClaim;
        this.textClaim = textClaim;
        this.dateClaim = dateClaim;
        this.isAnswered = false;
    }

    public long getIdClaim() {
        return idClaim;
    }

    public void setIdClaim(long idClaim) {
        this.idClaim = idClaim;
    }

    public String getTitleClaim() {
        return titleClaim;
    }

    public void setTitleClaim(String titleClaim) {
        this.titleClaim = titleClaim;
    }

    public String getTextClaim() {
        return textClaim;
    }

    public void setTextClaim(String claimText) {
        this.textClaim = claimText;
    }

    public Timestamp getDateClaim() {
        return dateClaim;
    }

    public void setDateClaim(Timestamp dateClaim) {
        this.dateClaim = dateClaim;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }

    public ClaimerEntity getClaimerEntity() {
        return claimerEntity;
    }

    public void setClaimerEntity(ClaimerEntity claimerEntity) {
        this.claimerEntity = claimerEntity;
    }
}
