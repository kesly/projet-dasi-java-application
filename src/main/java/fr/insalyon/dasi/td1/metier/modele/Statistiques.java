/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.metier.modele;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ndebarbans
 */
public class Statistiques {
    
    
    private LinkedHashMap<Medium,Integer> nbConsultationParEmploye;
    
    private LinkedHashMap<Employe,Integer> clientsParEmploye;
    
    private LinkedHashMap<Medium,Integer> top5Mediums;

    public Statistiques() {
    }

    public LinkedHashMap<Medium, Integer> getNbConsultationParEmploye() {
        return nbConsultationParEmploye;
    }

    public void setNbConsultationParEmploye(LinkedHashMap<Medium, Integer> nbConsultationParEmploye) {
        this.nbConsultationParEmploye = nbConsultationParEmploye;
    }

    public LinkedHashMap<Employe, Integer> getClientsParEmploye() {
        return clientsParEmploye;
    }

    public void setClientsParEmploye(LinkedHashMap<Employe, Integer> clientsParEmploye) {
        this.clientsParEmploye = clientsParEmploye;
    }

    public LinkedHashMap<Medium, Integer> getTop5Mediums() {
        return top5Mediums;
    }

    public void setTop5Mediums(LinkedHashMap<Medium, Integer> top5Mediums) {
        this.top5Mediums = top5Mediums;
    }

    

}
