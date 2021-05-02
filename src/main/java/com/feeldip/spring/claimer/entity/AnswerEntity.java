package com.feeldip.spring.claimer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jfr.Unsigned;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "Answer")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Unsigned
    @Column(name = "id")
    private long idAnswer;

    @NotNull
    @Column(name = "title")
    private String titleAnswer;

    @NotNull
    @Column(name = "text")
    private String textAnswer;

    @NotNull
    @Column(name = "date")
    @Type(type = "timestamp")
    private Timestamp dateAnswer;

    //Связь с таблицами
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "status_id")
    private StatusEntity statusEntity;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "claim_id")
    private ClaimEntity claimEntity;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "admin_id")
    private AdminEntity adminEntity;



    public AnswerEntity() { }

    public AnswerEntity(String titleAnswer, String textAnswer, Timestamp dateClaim) {
        this.titleAnswer = titleAnswer;
        this.textAnswer = textAnswer;
        this.dateAnswer = dateClaim;
    }

    public long getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(long idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getTitleAnswer() {
        return titleAnswer;
    }

    public void setTitleAnswer(String titleAnswer) {
        this.titleAnswer = titleAnswer;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public Timestamp getDateAnswer() {
        return dateAnswer;
    }

    public void setDateAnswer(Timestamp dateClaim) {
        this.dateAnswer = dateClaim;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }

    public ClaimEntity getClaimEntity() {
        return claimEntity;
    }

    public void setClaimEntity(ClaimEntity claimEntity) {
        this.claimEntity = claimEntity;
    }

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

}
