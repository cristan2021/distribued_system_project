package crist.bci.banque;

import java.util.Scanner;

import crist.bci.banquier.Banquier;
import crist.bci.client.Client;
import crist.bci.models.ModelBanquier;
import crist.bci.models.ModelClient;
import crist.bci.utils.Utils;
import crist.bci.database.Database;



public class Banque {

    private Database db;

    public Banque(Database db){
        this.db = db;
    }


    public void createBanquier(Banquier banquier) {
        if (!Utils.isEmailValid(banquier.getMail())) {
            System.out.println("L'adresse e-mail n'est pas valide.");
            return; // Retourne immédiatement si l'e-mail n'est pas valide
        }

        ModelBanquier modelBanquier = new ModelBanquier(db);
        banquier.setId(modelBanquier.getBanquierNextId()); // Définit l'ID sur l'objet banquier existant
        modelBanquier.insertBanquier(banquier); // Insère l'objet banquier existant
    
        
    }



   
}


    


  

