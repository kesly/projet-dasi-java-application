/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.metier.modele;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author imad9
 */
@Entity
public class Astrologue extends Medium implements Serializable{
    
    private String formation;

    private String anneePromotion;

    public Astrologue() {
    }

    public Astrologue(String formation, String anneepromotion, String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.anneePromotion = anneepromotion;
    }

    
    public String getFormation() {
        return formation;
    }

    public void setFormation(String promotion) {
        this.formation = promotion;
    }

    public String getAnneePromotion() {
        return anneePromotion;
    }

    public void setAnneePromotion(String anneepromotion) {
        this.anneePromotion = anneepromotion;
    }
    
    
}
