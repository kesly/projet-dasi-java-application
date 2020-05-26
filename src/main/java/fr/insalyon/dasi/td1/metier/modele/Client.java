/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author keslygassant
 */
@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private String prenom;

    @Column(nullable = true, unique = true)
    private String mail;

    private String motDePasse;

    public Client(){


    }


     public Client(String nom, String prenom, String mail, String motDePasse)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }

    public String getNom()
    {
        return this.nom;
    }

    void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return this.prenom;
    }

    void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getMail()
    {
        return this.mail;
    }

    void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getMotDePasse()
    {
        return this.motDePasse;
    }

    void setMotDePasse(String motDePasse)
    {
        this.motDePasse = motDePasse;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id + "nom = "+ nom + "; prenom =  "+ prenom + " mail = "+mail +"; mot de passe = "+ motDePasse;
    }

}
