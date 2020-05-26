/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.metier.service;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.dao.JpaUtil;
import fr.insalyon.dasi.td1.dao.ClientDao;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;

/**
 *
 * @author keslygassant
 */
public class Service {
    
    public Client recruter(Client client){
        ClientDao clientDao = new ClientDao();

        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            clientDao.create(client);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "Succès " + client.toString());
        } catch(Exception exception)
        {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur" + client.toString());
            JpaUtil.annulerTransaction();
            client = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return client;
        
    }
    
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
    
    public Client inscriptionClient(Client client)
    {
        ClientDao clientDao = new ClientDao();
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            clientDao.create(client);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "Succès inscription : " + client.toString());
        } catch(Exception exception)
        {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error during authenticate " + exception);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return client;
    }
    
}
