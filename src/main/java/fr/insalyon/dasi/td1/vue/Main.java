/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.vue;

/**
 *
 * @author keslygassant
 */

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.service.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import fr.insalyon.dasi.td1.dao.JpaUtil;
import fr.insalyon.dasi.td1.metier.modele.Consultation;
import java.util.Date;
import java.util.List;



public class Main {

    public static void main(String args[]) {
        System.out.print("Hello World");
        JpaUtil.init();
        
        //initialiserClients();// question 3
        testerInscriptionClient(); // question 4 et 5 
        //testerRechercheClient(); // question 6
        //testerListeClient(); question 7
        //testerAuthentificationClient(); // question 8
        
        testerListeConsultationParClient();
        
        
        JpaUtil.destroy();
    }


    
    public static void testerInscriptionClient(){
        
        
        Client c1 = new Client("Kesly", "Gassant", "kekes@gmail.com", "password");
        //Client c2 = new Client("test", "test", "kekes@gmail.com", "password2");
        Service service = new Service();
        
        
       Consultation consultation = new Consultation();
       consultation.setDateHeureDemande(new Date());
       consultation.setDateHeureDebut(null);
       consultation.setDateHeureFin(null);
       consultation.setCommentaire("test commenbtaire");

       
       c1.addConsultation(consultation);
       
       
        
        service.inscriptionClient(c1);
        service.createConsultation(consultation);
        //service.recruter(c2);
        

               
        Logger.getAnonymousLogger().log(Level.INFO, "Succès inscription");        
        Logger.getAnonymousLogger().log(Level.INFO, ""+ c1);
        
    }
    
    public static void ajoutConsultationPourClient(){
        
        // recuperer client
        
        Service service = new Service();
        
        
        
        // ajouter consultation
        
    }
    
    
    public static void testerListeConsultationParClient(){
         
        Service service = new Service();
        
        List<Consultation> listConsultation= service.findAllConsultationByClient(service.findClientById(new Long(1)));
               
        Logger.getAnonymousLogger().log(Level.INFO, "Succès lister");        
        Logger.getAnonymousLogger().log(Level.INFO, ""+ listConsultation.toString());
        
    }
    
    public static void testerRechercheClient(){
         
        Service service = new Service();
        Client c = service.findClientById(new Long(1));
               
        Logger.getAnonymousLogger().log(Level.INFO, "Succès recherche");        
        Logger.getAnonymousLogger().log(Level.INFO, ""+ c);
        
    }
    
    public static void testerListeClient(){
         
        Service service = new Service();
        List<Client> listClient= service.findAllClient();
               
        Logger.getAnonymousLogger().log(Level.INFO, "Succès lister");        
        Logger.getAnonymousLogger().log(Level.INFO, ""+ listClient.toString());
        
    }
    
        public static void testerAuthentificationClient(){
         
        Service service = new Service();
        Client c = service.getClientByAuthentication("kekes@gmail.com", "password");
       
               
        Logger.getAnonymousLogger().log(Level.INFO, "Succès authentification");        
        Logger.getAnonymousLogger().log(Level.INFO, ""+ c.toString());
        
    }
    
    
    public static void  initialiserClients(){

        EntityTransaction tx= null;
        EntityManagerFactory emf = null;
        EntityManager em = null;

        Client kesly = new Client("Kesly", "Gassant", "Kesly.Gassant@insa.lyon.fr", "azerty");
        Client niels = new Client("Niels", "De Barbanson", "Niels.Barbanson@insa.lyon.fr", "azerty");

        try{
            
            emf = Persistence.createEntityManagerFactory("maBase");
            em = emf.createEntityManager();
            tx = em.getTransaction();

            tx.begin();

            em.persist(kesly);
            em.persist(niels);

            tx.commit();

            Logger.getAnonymousLogger().log(Level.INFO, "succès création Client : " + kesly);

        }catch(Exception ex){

            Logger.getAnonymousLogger().log(Level.INFO, "echec création Client : " + ex );

            if(tx != null && tx.isActive()){
                tx.rollback();
            }

            System.out.println("Something went wrong.");
        } finally{
            System.out.println("The 'try catch' is finished.");
            if(em != null){
               em.close();
            }

        }

    }




}
