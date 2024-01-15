package crist.bci.client;

import java.sql.SQLException;

import javax.xml.crypto.Data;

import crist.bci.Compte.Compte;
import crist.bci.database.Database;
import crist.bci.models.ModelClient;
import crist.bci.models.ModelCompte;
import crist.bci.utils.Utilisateur;




public class Client extends Utilisateur {

  
    private Compte account;
    private Database db;
    private ModelCompte modelCompte;
    private ModelClient modelClient;
    
    public Client(int id, String nom, String prenom, String mail, String address, String password) {
        super(id, nom, prenom, mail, address, password);
        this.account = new Compte(0, 0, 0, 0);
          try {
            this.db = new Database();
            this.modelCompte = new ModelCompte(this.db);
            this.modelClient = new ModelClient(this.db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client(int id, String nom, String prenom, String mail, String address) {
        super(id, nom, prenom, mail, address, "");
        try {
            this.db = new Database();
            this.modelCompte = new ModelCompte(this.db);
            this.modelClient = new ModelClient(this.db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
      
    }


    public Compte getCompte(){
        return account;
    }

    public void setCompte(Compte account){
        this.account = account;
    }


    public void depot(Client client, double amount) throws SQLException{
        modelCompte.depot(modelCompte.getCompte(client.getId()).getNumCompte(), amount);
    }

    public void retrait(Client client, double amount) throws SQLException{
        modelCompte.retrait(modelCompte.getCompte(client.getId()).getNumCompte(), amount);
    }

    

  


    @Override
    public String toString() {
        return 
            " nom='" + this.getNom()+ '\'' +
            ", prenom='" + this.getPrenom() + '\'' +
            ", mail='" + this.getNom() + '\'' +
            ", adresse=" + this.getAddress() 
            ;
    }

}