/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.metier.service;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.metier.modele.Consultation;
import fr.insalyon.dasi.td1.dao.JpaUtil;
import fr.insalyon.dasi.td1.dao.ClientDao;
import fr.insalyon.dasi.td1.dao.ConsultationDao;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;

/**
 *
 * @author keslygassant
 */
public class Service {
    
    public Client findClientById(Long id){
        ClientDao clientDao = new ClientDao();
        
        Client client = null;
        try {
            JpaUtil.creerContextePersistance();
            
            client = clientDao.findById(id);
            
            Logger.getAnonymousLogger().log(Level.INFO, "Succès " + client.toString());
            
        } catch(Exception exception)
        {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur: impossible de trouver le cliebnt ayant l'id" + id.toString());
        } finally {
            JpaUtil.fermerContextePersistance();    
        }
        
        return client;
        
    }
    
    public List<Client> findAllClient(){
        ClientDao clientDao = new ClientDao();
        
        List<Client> clients = null;
        try {
            JpaUtil.creerContextePersistance();
            
            clients = clientDao.findAll();
            
            Logger.getAnonymousLogger().log(Level.INFO, "Succès " + clients.toString() );
            
        } catch(Exception exception)
        {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur dans findAllClient", exception);
        } finally {
            JpaUtil.fermerContextePersistance();    
        }
        
        return clients;
        
    }
    
    public Client getClientByAuthentication(String mail, String motDePasse)
    {
        ClientDao clientDao = new ClientDao();
        Client client = null;
        
        try {
            JpaUtil.creerContextePersistance();
            client = clientDao.authenticate(mail, motDePasse);
        } catch(Exception exception)
        {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during authenticate " + exception);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return client;
    }
    
    public void inscriptionClient(Client client)
    {
        ClientDao clientDao = new ClientDao();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            clientDao.create(client);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "Mail inscription envoyé: " + client.toString());
            
        } catch(Exception exception)
        {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during inscription " + exception);
            
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
    }
    
    
    public List<Consultation> findAllConsultationByClient(Client client){
        
        ConsultationDao consultationDao = new ConsultationDao();
        List<Consultation> consultations = null;
        
        try {
            JpaUtil.creerContextePersistance();
            
            consultations = consultationDao.findAllByClientId(client.getId());
        } catch(Exception exception)
        {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during findAllConsultationByClient " + exception);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return consultations;
        
    }
    
    
    public void createConsultation(Consultation consultation){
        
        ConsultationDao consultationDao = new ConsultationDao();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            consultationDao.create(consultation);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "Success: createConsultation " + consultation.toString());
            
        } catch(Exception exception)
        {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during createConsultation " + exception);
            
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
    }
    
}
