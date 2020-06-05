/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.dao;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Medium;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author keslygassant
 */
public class MediumDao {

    public void create(Medium medium){
        JpaUtil.obtenirContextePersistance().persist(medium);
    }

    public Medium findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    
    public List<Medium> findAll(){
        String s = "SELECT m FROM Medium m";
        TypedQuery<Medium> query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    
}
