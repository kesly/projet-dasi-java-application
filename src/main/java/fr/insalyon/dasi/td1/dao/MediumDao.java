/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.dao;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Medium;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author keslygassant
 */
public class MediumDao {

    public void create(Medium medium){
        JpaUtil.obtenirContextePersistance().persist(medium);
    }

    public void update(Medium medium){
        JpaUtil.obtenirContextePersistance().merge(medium);
    }

    public Medium findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    
    public List<Medium> findAll(){
        String s = "SELECT m FROM Medium m";
        TypedQuery<Medium> query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }


    public List<Medium> findTop5(){
        String s = "SELECT m, count(c) as nb from Medium m left join m.consultations c GROUP BY m ORDER BY nb DESC";
        Query query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        List<Object[]> results = query.setMaxResults(5).getResultList();
        List<Medium> mediums = null;

        int i = 0;
        for ( Object[] object : results){
            Object test = object[0];
//            mediums.add(test);
        }            
        
        
        
        
        return mediums;
    }
    
    public Map<Medium, Integer> getNombreConsultationParMedium(int limite)
    {
        Map<Medium, Integer> mediums = null;
        
        String q = new StringBuilder()
                       .append("select c.medium as medium, count(c) as total ")
                       .append("from Consultation c ")
                       .append("group by c.medium ")
                       .append("order by count(c) desc")
                       .toString();
        Query query = JpaUtil.obtenirContextePersistance()
           .createQuery(q);
        
        if (limite > 0)
        {
            query.setMaxResults(limite);
        }
        
        List<Object[]> list = query.getResultList();
        
        mediums = new LinkedHashMap<Medium, Integer>();
        for (Object[] ob : list)
        {
            mediums.put((Medium) ob[0], (int) (long) ob[1]);
        }
        
        return mediums;
    }
}
