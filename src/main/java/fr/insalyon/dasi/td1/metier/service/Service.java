/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.td1.metier.service;

import fr.insalyon.dasi.td1.metier.modele.Client;
import fr.insalyon.dasi.td1.dao.JpaUtil;
import fr.insalyon.dasi.td1.dao.ClientDao;
import fr.insalyon.dasi.td1.metier.modele.ProfilAstral;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;
import util.AstroTest;
import util.Message;

/**
 *
 * @author keslygassant
 */
public class Service {
    
    private static final String ADRESSEMAILPREDICTIF = "contact@predict.if";
    
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
    
    public void inscriptionClient(Client client) throws IOException
    {
        ClientDao clientDao = new ClientDao();
        AstroTest astroTest = new AstroTest();
        
        List<String> stringProfilAstral;
        stringProfilAstral = astroTest.getProfil(client.getPrenom(),client.getDateNaissance());
        ProfilAstral profilAstral = new ProfilAstral(stringProfilAstral.get(0),stringProfilAstral.get(1),stringProfilAstral.get(2),stringProfilAstral.get(3));
      
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
            mailWriter.println("nous vous confirmons votre inscription au service PREDICT’IF. Rendez-\n"
                    + "vous vite sur notre site pour consulter votre profil astrologique et profiter des dons\n"
                    + "incroyables de nos mediums");
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
    
    
            
            
            
}
