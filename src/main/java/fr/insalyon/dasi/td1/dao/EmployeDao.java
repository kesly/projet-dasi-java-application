package fr.insalyon.dasi.td1.dao;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Employe;
import fr.insalyon.dasi.td1.metier.modele.Medium;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeDao {

    public void create(Employe employe) {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }

    public void update(Employe employe) {
        JpaUtil.obtenirContextePersistance().merge(employe);
    }


    public Employe findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Employe.class, id);
    }

    public List<Employe> findAll() {
        String s = "SELECT e FROM Employe e";
        TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        return query.getResultList();
    }

    public List<Employe> findAllGoodEmploye(String genre) {
        String s = "SELECT e FROM Employe e where e.genre =:genre AND e.estDisponible = :estDisponible";
        TypedQuery<Employe> query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        query.setParameter("genre", genre);
        query.setParameter("estDisponible", true);
        return query.getResultList();
    }

    public Employe authenticate(String mail, String motDePasse) {
        String s = "SELECT e FROM Employe e WHERE e.mail = :mail and e.motDePasse = :mdp";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(s);
        query.setParameter("mail", mail);
        query.setParameter("mdp", motDePasse);
        return (Employe) query.getSingleResult();
    }

}
