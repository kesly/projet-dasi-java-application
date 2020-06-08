/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.metier.modele;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ndebarbans
 */
public class Statistiques {
    
    
    private Map<Long,Integer> nbConsultationParEmploye;
    
    private Map<Long,Long> clientsParEmploye;
    
    private List<Long> top5Mediums;

    public Statistiques() {
    }
    
    public Map<Long, Integer> getNbConsultationParEmploye() {
        return nbConsultationParEmploye;
    }

    public void setNbConsultationParEmploye(Map<Long, Integer> nbConsultationParEmploye) {
        this.nbConsultationParEmploye = nbConsultationParEmploye;
    }

    public List<Long> getTop5Mediums() {
        return top5Mediums;
    }

    public void setTop5Mediums(List<Long> top5Mediums) {
        this.top5Mediums = top5Mediums;
    }

    public Map<Long, Long> getClientsParEmploye() {
        return clientsParEmploye;
    }

    public void setClientsParEmploye(Map<Long, Long> clientsParEmploye) {
        this.clientsParEmploye = clientsParEmploye;
    }
}
