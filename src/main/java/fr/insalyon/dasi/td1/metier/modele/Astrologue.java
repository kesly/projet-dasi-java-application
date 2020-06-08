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
    
    private String promotion;

    private String anneepromotion; 

    public Astrologue() {
    }

    public Astrologue(String promotion, String anneepromotion, String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
        this.promotion = promotion;
        this.anneepromotion = anneepromotion;
    }

    
    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getAnneepromotion() {
        return anneepromotion;
    }

    public void setAnneepromotion(String anneepromotion) {
        this.anneepromotion = anneepromotion;
    }
    
    
}
