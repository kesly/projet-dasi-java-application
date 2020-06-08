package fr.insalyon.dasi.td1.dao;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Employe;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeDao {

    public void create(Employe employe){
        JpaUtil.obtenirContextePersistance().persist(employe);
    }

    public void update(Employe employe){
        JpaUtil.obtenirContextePersistance().merge(employe);
    }


    public Employe findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Employe.class, id);
    }

//    public List<Employe> findAll(){
//        String s = "SELECT c FROM Client c ORDER BY c.nom ASC";
//        TypedQuery<Client> query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
//        return query.getResultList();
//    }

    public Employe authenticate(String mail, String motDePasse)
    {
        String s = "SELECT e FROM Employe e WHERE e.mail = :mail and e.motDePasse = :mdp";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(s);
        query.setParameter("mail", mail);
        query.setParameter("mdp", motDePasse);
        return (Employe) query.getSingleResult();
    }

}
