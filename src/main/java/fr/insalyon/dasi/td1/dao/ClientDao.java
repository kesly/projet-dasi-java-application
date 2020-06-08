/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.dao;
import fr.insalyon.dasi.td1.metier.modele.Client;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.TypedQuery;


/**
 *
 * @author keslygassant
 */
public class ClientDao {
    
    public void create(Client client){
        JpaUtil.obtenirContextePersistance().persist(client);
    }

    public void update(Client client){
        JpaUtil.obtenirContextePersistance().merge(client);
    }


    public Client findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }
    
    public List<Client> findAll(){
        String s = "SELECT c FROM Client c ORDER BY c.nom ASC";
        TypedQuery<Client> query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        return query.getResultList();
    }
    
    public Client authenticate(String mail, String motDePasse)
    {
        String s = "SELECT c FROM Client c WHERE c.mail = :mail and c.motDePasse = :mdp";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(s);
        query.setParameter("mail", mail);
        query.setParameter("mdp", motDePasse);
        return (Client) query.getSingleResult();
    }
}
