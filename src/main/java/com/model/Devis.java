package com.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Devis {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String nom;

    private int numero;

    private Date dateEstimee;

    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name="client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name="commercial_id")
    private User commercial;

    @ManyToOne
    @JoinColumn(name="maison_id")
    private Maison maison;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDateEstimee() {
        return dateEstimee;
    }

    public void setDateEstimee(Date dateEstimee) {
        this.dateEstimee = dateEstimee;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getCommercial() {
        return commercial;
    }

    public void setCommercial(User commercial) {
        this.commercial = commercial;
    }

    public Maison getMaison() {
        return maison;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }
}
