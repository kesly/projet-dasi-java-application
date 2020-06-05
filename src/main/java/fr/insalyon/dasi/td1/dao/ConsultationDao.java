/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.dao;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Consultation;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author keslygassant
 */
public class ConsultationDao {
    
    public void create(Consultation consultation){
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
    
    public void update(Consultation consultation){
        JpaUtil.obtenirContextePersistance().merge(consultation);
    }
    
    public Consultation findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Consultation.class, id);
    }
    
    public List<Consultation> findAllByClientId(Long id){
        String s = "SELECT con FROM Client c JOIN c.consultations con WHERE c.id =:id";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(s);
        query.setParameter("id", id);
        return (List<Consultation>)query.getResultList();
    }
    
    public List<Consultation> findAll(){
        String s = "SELECT c FROM Consultation c";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(s);
        return (List<Consultation>)query.getResultList();
    }
    
}
