/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.vue;

/**
 * @author keslygassant
 */

import fr.insalyon.dasi.td1.metier.modele.*;
import fr.insalyon.dasi.td1.metier.service.Service;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.insalyon.dasi.td1.dao.JpaUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    private static Logger logger = Logger.getAnonymousLogger();

    public static void main(String args[]) throws IOException, ParseException {
        
       
        JpaUtil.init();

        testerInscriptionClient();
//        testerAuthentificationClient();
//        testerObtenirPredictions();
//        testerCreationMedium();
//        testerDemandeConsultation();
//        testerDemarrerConsultation();
//        testerTerminerConsultation();
//        testerConsulterHistorique();
//        testerAfficherStatistiques();

        Service s = new Service();
        s.initialisationSysteme();
        

        //testerRechercheClient();
        //testerListeClient();
        //testerAuthentificationClient(); // question 8

//        testerListeConsultationParClient();


        JpaUtil.destroy();
    }


    public static void testerInscriptionClient() throws IOException, ParseException {


        Client c1 = new Client("Kesly", "Gassant", "kekes@gmail.com", "password");
        Client c2 = new Client("test", "test", "kekes@gmail.com", "password2");
        Client c3 = new Client("Niels", "de Barbanson", "nielsdebarbanson@gmail.com", "mot2passe");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = "22/06/2006";

        c1.setDateNaissance(simpleDateFormat.parse("25/11/1999"));
        c2.setDateNaissance(simpleDateFormat.parse("20/01/2000"));
        c3.setDateNaissance(simpleDateFormat.parse("13/09/1997"));

        Service service = new Service();
        service.inscriptionClient(c1);
        service.inscriptionClient(c2);
        service.inscriptionClient(c3);

    }


    public static void testerCreationMedium() {

        Medium medium1 = new Cartomancien("cartomacien", "M", "je suis le meilleur");
        Astrologue as1 = new Astrologue("INSA","2020","Astr","M","C'est moi");
        Service service = new Service();
        service.createMedium(medium1);
        service.createMedium(as1);

    }


    public static void testerObtenirPredictions() throws IOException {
        Service service = new Service();
        List<String> predictions;

        Client client1 = service.findClientById(1L);
        Client client2 = service.findClientById(5L);

        predictions = service.obtenirPredictions(client1, 3, 3, 1);
        System.out.println("");
        System.out.println("~<[Premières Prédictions]>~");
        System.out.println("[ Amour ] " + predictions.get(0));
        System.out.println("[ Santé ] " + predictions.get(1));
        System.out.println("[Travail] " + predictions.get(2));
        System.out.println("");

        predictions = service.obtenirPredictions(client2, 2, 1, 4);
        System.out.println("");
        System.out.println("~<[Secondes Prédictions ]>~");
        System.out.println("[ Amour ] " + predictions.get(0));
        System.out.println("[ Santé ] " + predictions.get(1));
        System.out.println("[Travail] " + predictions.get(2));
        System.out.println("");
    }

    public static void testerDemandeConsultation() {

        // recuperer client

        Service service = new Service();
        Client client = service.findClientById(1L);

//        System.out.println("test" + client.toString() );

//        client.toString();

        Medium medium = service.findMediumById(7L);

        Consultation c1 = service.demandeConsultation(client, medium);
        Consultation c2 = service.demandeConsultation(client, medium);

        System.out.println("consultation créé" + c1.toString() );


        // ajouter consultation

    }


    public static void testerListeConsultationParClient() throws IOException {


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
        //service.createConsultation(consultation);
        //service.recruter(c2);


        List<Consultation> listConsultation = service.findAllConsultationByClient(service.findClientById(new Long(1)));

    }

    public static void testerRechercheClient() {

        Service service = new Service();
        Client c = service.findClientById(new Long(1));

        logger.info("Succès recherche");
        logger.info("" + c);

    }

    public static void testerListeClient() {

        Service service = new Service();
        List<Client> listClient = service.findAllClient();

        logger.info("Succès lister");
        logger.info("" + listClient.toString());

    }

    public static void testerAuthentificationClient() {
        Service service = new Service();
        Client c = service.getClientByAuthentication("kekes@gmail.com", "password");

        logger.info("Succès authentification");
        logger.info("" + c.toString());

    }


    public static void initialiserClients() {

        EntityTransaction tx = null;
        EntityManagerFactory emf = null;
        EntityManager em = null;

        Client kesly = new Client("Kesly", "Gassant", "Kesly.Gassant@insa.lyon.fr", "azerty");
        Client niels = new Client("Niels", "De Barbanson", "Niels.Barbanson@insa.lyon.fr", "azerty");

        try {

            emf = Persistence.createEntityManagerFactory("maBase");
            em = emf.createEntityManager();
            tx = em.getTransaction();

            tx.begin();

            em.persist(kesly);
            em.persist(niels);

            tx.commit();

            logger.info("succès création Client : " + kesly);

        } catch (Exception ex) {

            logger.info("echec création Client : " + ex);

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            System.out.println("Something went wrong.");
        } finally {
            System.out.println("The 'try catch' is finished.");
            if (em != null) {
                em.close();
            }

        }

    }

    public static void testerDemarrerConsultation(){
        Service service = new Service();
        Consultation consultation = service.findConsultationById(9L);


        // @TODO: ajouter un employé à la consult
        Client c1 = service.findClientById(1L);
        c1.addConsultation(consultation);
        service.demarrerConsultation(consultation);
    }
    
    public static void testerTerminerConsultation() {

        Service service = new Service();
        Consultation consultation = service.findConsultationById(8L);
        service.terminerConsultation(consultation);
    }

    public static void testerConsulterHistorique() {
        Service service = new Service();
        Client c1 = service.findClientById(1L);
        List<Consultation> historique;
        historique = c1.getConsultations();

        System.out.println("Historique des consultations de " + c1.getPrenom() + " : \n");

        int index = 0;
        for (Consultation consult : historique) {
            System.out.println("Consultation numéro : " + (index++) + "\n");
            // @TODO: ajouter médium
//            System.out.println("Avec le médium: " + consult.getMedium().toString() + "\n");
            System.out.println("de  : " + consult.getDateHeureDebut().toString() + "\n");
            System.out.println("à : " + consult.getDateHeureFin().toString() + "\n");
            System.out.println("Commentaire : " + consult.getCommentaire() + "\n");
        }
    }

    
    public static void testerAfficherStatistiques() {
        Service service = new Service();
        Statistiques statistique = service.afficherStatistiques();
        String nomMedium = null;
        
        System.out.println("###########################################");
        System.out.println("            Statistiques:                 \n");
        
        
        System.out.println("Nombres de Consults par medium\n");
        for(Long idMedium: statistique.getNbConsultationParEmploye().keySet()){
            nomMedium=service.findMediumById(idMedium).getDenomination();
            System.out.println("Le médium "+ nomMedium + " a réalisé " + statistique.getNbConsultationParEmploye().get(idMedium)+ " consultations\n" );
        }


        System.out.println("###########################################");

        
    }
}
