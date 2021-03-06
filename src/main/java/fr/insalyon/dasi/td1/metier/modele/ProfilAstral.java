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
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author keslygassant
 */
@Entity
public class ProfilAstral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String signeZodiac;

    private String signeAstrologiqueChinois;
    
    private String couleurPorteBonheur;
    
    private String animalTotem;

    public ProfilAstral() {
        
    }
    
    public ProfilAstral(String signeZodiac, String signeAstrologiqueChinois, String couleurPorteBonheur, String animalTotem) {
        this.signeZodiac = signeZodiac;
        this.signeAstrologiqueChinois = signeAstrologiqueChinois;
        this.couleurPorteBonheur = couleurPorteBonheur;
        this.animalTotem = animalTotem;
    }


    public String getSigneZodiac() {
        return signeZodiac;
    }

    public void setSigneZodiac(String signeZodiac) {
        this.signeZodiac = signeZodiac;
    }

    public String getSigneAstrologiqueChinois() {
        return signeAstrologiqueChinois;
    }

    public void setSigneAstrologiqueChinois(String signeAstrologiqueChinois) {
        this.signeAstrologiqueChinois = signeAstrologiqueChinois;
    }

    public String getCouleurPorteBonheur() {
        return couleurPorteBonheur;
    }

    public void setCouleurPorteBonheur(String couleurPorteBonheur) {
        this.couleurPorteBonheur = couleurPorteBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
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
        if (!(object instanceof ProfilAstral)) {
            return false;
        }
        ProfilAstral other = (ProfilAstral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.insalyon.dasi.td1.metier.modele.ProfilAstral[ id=" + id + " ]";
    }
    
}
