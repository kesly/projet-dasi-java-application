/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.metier.modele;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ndebarbans
 */
public class Statistiques {
    
    
    private HashMap<Long,Integer> nbConsultationParEmploye;
    
    private HashMap<Long,Integer> clientsParEmploye;
    
    private List<Long> top5Mediums;

    public Statistiques() {
    }

    public HashMap<Long, Integer> getNbConsultationParEmploye() {
        return nbConsultationParEmploye;
    }

    public void setNbConsultationParEmploye(HashMap<Long, Integer> nbConsultationParEmploye) {
        this.nbConsultationParEmploye = nbConsultationParEmploye;
    }

    public HashMap<Long, Integer> getClientsParEmploye() {
        return clientsParEmploye;
    }

    public void setClientsParEmploye(HashMap<Long, Integer> clientsParEmploye) {
        this.clientsParEmploye = clientsParEmploye;
    }

    public List<Long> getTop5Mediums() {
        return top5Mediums;
    }

    public void setTop5Mediums(List<Long> top5Mediums) {
        this.top5Mediums = top5Mediums;
    }
    
   
}
