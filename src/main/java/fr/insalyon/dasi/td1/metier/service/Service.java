/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.metier.service;

import fr.insalyon.dasi.td1.dao.*;
import fr.insalyon.dasi.td1.metier.modele.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import util.AstroTest;
import util.Message;

/**
 * @author keslygassant
 */
public class Service {

    private static final String ADRESSEMAILPREDICTIF = "contact@predict.if";

    private static Logger logger = Logger.getAnonymousLogger();


    public Client findClientById(Long id) {
        ClientDao clientDao = new ClientDao();

        Client client = null;
        try {
            JpaUtil.creerContextePersistance();

            client = clientDao.findById(id);

            Logger.getAnonymousLogger().log(Level.INFO, "Succès findClientById" + client.toString());

        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur: impossible de trouver le client ayant l'id" + id.toString());
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return client;

    }

    public Employe findEmployeById(Long id) {
        EmployeDao employeDao = new EmployeDao();

        Employe employe = null;
        try {
            JpaUtil.creerContextePersistance();

            employe = employeDao.findById(id);

            Logger.getAnonymousLogger().log(Level.INFO, "Succès findEmployeById" + employe.toString());

        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur: impossible de trouver le client ayant l'id" + id.toString());
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return employe;

    }

    public List<Client> findAllClient() {
        ClientDao clientDao = new ClientDao();

        List<Client> clients = null;
        try {
            JpaUtil.creerContextePersistance();

            clients = clientDao.findAll();

            Logger.getAnonymousLogger().log(Level.INFO, "Succès " + clients.toString());

        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur dans findAllClient", exception);
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return clients;

    }

    public Client getClientByAuthentication(String mail, String motDePasse) {
        ClientDao clientDao = new ClientDao();
        Client client = null;

        try {
            JpaUtil.creerContextePersistance();
            client = clientDao.authenticate(mail, motDePasse);
        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during authenticate " + exception);
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return client;
    }

    public Employe getEmployeByAuthentification(String mail, String motDePasse) {
        EmployeDao employeDao = new EmployeDao();
        Employe employe = null;

        try {
            JpaUtil.creerContextePersistance();
            employe = employeDao.authenticate(mail, motDePasse);
        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during authenticate Employe" + exception);
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return employe;
    }

    public void inscriptionClient(Client client) throws IOException {
        ClientDao clientDao = new ClientDao();
        AstroTest astroApi = new AstroTest();

        List<String> stringProfilAstral;
        stringProfilAstral = astroApi.getProfil(client.getPrenom(), client.getDateNaissance());
        ProfilAstral profilAstral = new ProfilAstral(stringProfilAstral.get(0), stringProfilAstral.get(1), stringProfilAstral.get(2), stringProfilAstral.get(3));

        try {
            client.setProfilAstral(profilAstral);
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            clientDao.create(client);
            JpaUtil.validerTransaction();

            Message message = new Message();
            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);


            mailWriter.print("Bonjour ");
            mailWriter.print(client.getPrenom());
            mailWriter.println(",\n\n");
            mailWriter.println("Nous vous confirmons votre inscription au service PREDICT’IF.\nRendez-"
                    + "vous vite sur notre site pour consulter votre profil astrologique et profiter des dons\n"
                    + "incroyables de nos mediums.\n\nCordialement, l'equipe Predict'IF.");
            Message.envoyerMail(ADRESSEMAILPREDICTIF,
                    client.getMail(),
                    "Bienvenue chez PREDICT’IF",
                    corps.toString()
            );

        } catch (Exception exception) {
            Message message = new Message();
            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);

            mailWriter.print("Bonjour ");
            mailWriter.print(client.getPrenom());
            mailWriter.println(",\n\n");
            mailWriter.println("votre inscription au service PREDICT’IF a malencontreusement échoué...\n"
                    + "Merci de recommencer ultérieurement.");
            Message.envoyerMail(ADRESSEMAILPREDICTIF,
                    client.getMail(),
                    "Echec de l’inscription chez PREDICT’IF",
                    corps.toString()
            );

            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during inscription " + exception);

        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }


    public List<Consultation> findAllConsultationByClient(Client client) {

        ConsultationDao consultationDao = new ConsultationDao();
        List<Consultation> consultations = null;

        try {
            JpaUtil.creerContextePersistance();   
            consultations = consultationDao.findAllByClientId(client.getId());
        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during findAllConsultationByClient " + exception);
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return consultations;

    }


    public Medium findMediumById(Long id) {
        MediumDao mediumDao = new MediumDao();

        Medium medium = null;
        try {
            JpaUtil.creerContextePersistance();

            medium = mediumDao.findById(id);

            Logger.getAnonymousLogger().log(Level.INFO, "Succès: medium trouve par id" + medium.toString());

        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur: impossible de trouver le medium ayant l'id" + id.toString());
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return medium;

    }


    public List<Medium> findAllMedium() {
        MediumDao mediumDao = new MediumDao();

        List<Medium> mediums = null;
        try {
            JpaUtil.creerContextePersistance();

            mediums = mediumDao.findAll();

            Logger.getAnonymousLogger().log(Level.INFO, "Succès " + mediums.toString());

        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur dans findAllMedium", exception);
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return mediums;

    }


    public Consultation findConsultationById(Long id) {

        ConsultationDao consultationDao = new ConsultationDao();

        Consultation consultation = null;
        try {
            JpaUtil.creerContextePersistance();

            consultation = consultationDao.findById(id);

            Logger.getAnonymousLogger().log(Level.INFO, "Succès: consultation trouve par id" + consultation.toString());

        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur: impossible de trouver le consultation ayant l'id" + id.toString());
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return consultation;

    }

    private Employe findGoodEmploye(String genre) {

        EmployeDao employeDao = new EmployeDao();

        List<Employe> employeList = null;
        Employe goodEmploye = null;
        try {

            employeList = employeDao.findAll();

            for (Employe employe : employeList) {
                if (employe.getGenre().equals(genre) && employe.isEstDisponible()) {
                    goodEmploye = employe;
                    break;
                }
            }

            Logger.getAnonymousLogger().log(Level.INFO, "Succès: good employee trouvé" + goodEmploye.toString());

        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur: impossible de trouver un employe" + genre);
        } finally {
        }

        return goodEmploye;
    }


    public Consultation demandeConsultation(Client client, Medium medium) {

        ConsultationDao consultationDao = new ConsultationDao();
        ClientDao clientDao = new ClientDao();
        MediumDao mediumDao = new MediumDao();
        EmployeDao employeDao = new EmployeDao();


        Consultation consultation = null;
        Employe employe = null;
        try {
            JpaUtil.creerContextePersistance();

            JpaUtil.ouvrirTransaction();

            // create consultation

            consultation = new Consultation();
            consultation.setDateHeureDemande(new Date());
            consultation.setDateHeureDebut(null);
            consultation.setDateHeureFin(null);

            consultationDao.create(consultation);

            client.addConsultation(consultation);
            medium.addConsultation(consultation);

            employe = this.findGoodEmploye(medium.getGenre());

            if (employe != null) {
                employe.addConsultation(consultation);
                employe.setEstDisponible(false);
                employeDao.update(employe);
            } else {
                // error message no employe found
            }

            clientDao.update(client);
            mediumDao.update(medium);

            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "Succès: consultation créée" + consultation.toString());

        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur: impossible de faire la demande de consultation ayant l'id" + client.toString()+exception.toString());
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return consultation;

    }

    public List<String> obtenirPredictions(Client client, int amour, int sante, int travail) throws IOException {

        AstroTest astroApi = new AstroTest();
        ProfilAstral profilAstral = client.getProfilAstral();
        return astroApi.getPredictions(profilAstral.getCouleurPorteBonheur(), profilAstral.getAnimalTotem(), amour, sante, travail);

    }

    public boolean demarrerConsultation(Consultation consultation) {
        Date now = Calendar.getInstance().getTime();
        ConsultationDao consultationDao = new ConsultationDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            // @TODO: vérifier qu'il y a bien un employé d'associé à la consult
            if (consultation.getClient() != null) {
                consultation.setDateHeureDebut(now);
                // consultationDao.create(consultation);
                consultationDao.update(consultation);
                JpaUtil.validerTransaction();
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            return false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public boolean terminerConsultation(Consultation consultation) {
        Date now = Calendar.getInstance().getTime();
        ConsultationDao consultationDao = new ConsultationDao();
        EmployeDao employeDao = new EmployeDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            // @TODO: vérifier qu'il y a bien un employé d'associé à la consult
            if (consultation.getClient() != null) {
                consultation.setDateHeureFin(now);

                Employe employe = consultation.getEmploye();

                employe.setEstDisponible(true);

                employeDao.update(employe);

                consultationDao.update(consultation);
                JpaUtil.validerTransaction();
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            return false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }


    public void createMedium(Medium medium) {

        MediumDao mediumDao = new MediumDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            mediumDao.create(medium);

            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "Success: createMedium " + medium.toString());

        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during createMedium " + exception);

        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

    public Statistiques afficherStatistiques() {
        MediumDao mediumDao = new MediumDao();
        List<Medium> mediums = null;
        Statistiques statistiques = new Statistiques();
        Integer nbConsults = 0;
        LinkedHashMap<Medium,Integer> nbConsultsParEmploye = new LinkedHashMap<Medium,Integer>();

        try {
            JpaUtil.creerContextePersistance();
            mediums = mediumDao.findAll();
            List<Consultation> consults;

            for( Medium medium : mediums){
                consults= medium.getConsultations();
                nbConsults=medium.getConsultations().size();
                nbConsultsParEmploye.put(medium, nbConsults);
            }
            statistiques.setNbConsultationParEmploye(nbConsultsParEmploye);

            EmployeDao EmployeDao = new EmployeDao();

            List <Employe> employes = EmployeDao.findAll();
            LinkedHashMap<Employe,Integer> repartitionParEmploye = new LinkedHashMap<>();

            for (Employe employe : employes) {

                repartitionParEmploye.put(employe, 0);
                List <Consultation>  consultations = employe.getConsultations();

                List<Long> listClient = new ArrayList<Long>();

                for (Consultation consultation : consultations) {
                    if (!listClient.contains(consultation.getClient().getId())) {
                        listClient.add(consultation.getClient().getId());
                    }
                }

                repartitionParEmploye.put(employe, listClient.size());
            }

            statistiques.setClientsParEmploye(repartitionParEmploye);


            Map<Medium, Integer> top5;
            top5=mediumDao.getNombreConsultationParMedium(5);
            
            statistiques.setTop5Mediums((LinkedHashMap<Medium, Integer>) top5);
           
            
            
            
        } catch (Exception exception) {
            logger.severe("Error during authenticate " + exception);
        } finally {
            JpaUtil.fermerContextePersistance();
        }


        return statistiques;


    }


    public boolean initialisationSysteme() {

        MediumDao mediumDao = new MediumDao();
        EmployeDao employeDao = new EmployeDao();
        boolean methodSuccess = true;

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            List<Medium> mediumList = new ArrayList<Medium>();
            List<Employe> employeList = new ArrayList<Employe>();

            mediumList.add(new Spirite("Boule de cristal", "Gwenaëlle", "F", " Spécialiste des grandes conversations au-delà de TOUTES les frontières"));
            mediumList.add(new Spirite("Marc de café, boule de cristal, oreilles de lapin", "Professeur Tran", "H", "Votre avenir est devant vous : regardons-le ensemble !"));


            mediumList.add(new Cartomancien("Mme Irma", "F", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides."));
            mediumList.add(new Cartomancien(" Endora", "F", "Mes cartes répondront à toutes vos questions personnelles"));

            mediumList.add(new Astrologue("École Normale Supérieure d’Astrologie (ENS-Astro)", "2006", "Serena", "F", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre\n" +
                    "passé."));

            mediumList.add(new Astrologue("Institut des Nouveaux Savoirs Astrologiques", "2010", "Mr M", "H", " Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!"));


            for (Medium medium : mediumList) {
                mediumDao.create(medium);
            }

            employeList.add(new Employe("camille.berger@astroif.fr", "Berger", "Camille", "12345", "0667234565", "F", true));
            employeList.add(new Employe("pierreDupont@astroif.fr", "Dupont", "Pierre", "12345", "0665136497", "H", true));
            employeList.add(new Employe("simone.du-jardin@astroif.fr", "Du jardin", "Simone", "12345", "0614455669", "F", true));
            employeList.add(new Employe("brice.martin@astroif.fr", "Martin", "Brice", "motdepasse", "0614455669", "H", true));
            employeList.add(new Employe("alin.bouvier@astroif.fr", "Bouvier", "Alain", "12345", "0614455669", "H", true));
            employeList.add(new Employe("herve.libercier@astroif.fr", "Libercier", "herve", "12345", "0614455669", "H", true));
            employeList.add(new Employe("beatrice.moureau@astroif.fr", "Moureau", "Beatrice", "12345", "0614455669", "F", true));
            

            for (Employe employe : employeList) {
                employeDao.create(employe);
            }

            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "Success: initialisation ");

            methodSuccess = true;

        } catch (Exception exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during createMedium " + exception);
            methodSuccess = false;

        } finally {
            JpaUtil.fermerContextePersistance();
            return methodSuccess;
        }

    }
}
