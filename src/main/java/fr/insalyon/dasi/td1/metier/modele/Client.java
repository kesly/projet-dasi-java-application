/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.metier.modele;

import fr.insalyon.dasi.td1.dao.JpaUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private String adressePostal;
    
    private String tel;
    
    private String civilite;
    
    @Column(nullable = false, unique = true)
    private String mail;

    private String motDePasse;
    
    @OneToOne(cascade=CascadeType.ALL)
    private ProfilAstral profilAstral;

    
    
    @OneToMany(mappedBy="client")
    private List<Consultation> consultations;

    public Client(){
        this.consultations = new ArrayList<Consultation>();
    }
    
    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }

    public Client(String prenom, String nom, String mail, String motDePasse) {
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        
        this.consultations = new ArrayList<Consultation>();
    }

    public Client(String nom, String prenom, Date dateNaissance, String adressePostal, String tel, String civilite, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adressePostal = adressePostal;
        this.tel = tel;
        this.civilite = civilite;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
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
    
    
    public void addConsultation(Consultation consultation)
    {
         Logger.getAnonymousLogger().log(Level.INFO, "consul " + consultation.toString());
        
        if (!this.consultations.contains(consultation)) {
            this.consultations.add(consultation);
            consultation.setClient(this);
        }
    }

    public List<Consultation> getConsultations() {
        return consultations;
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
