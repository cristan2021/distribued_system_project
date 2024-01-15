package crist.bci.banquier;


import java.sql.SQLException;
import crist.bci.client.Client;
import crist.bci.database.Database;
import crist.bci.utils.Utilisateur;
import crist.bci.models.ModelClient;
import crist.bci.models.ModelCompte;
import crist.bci.utils.Utils;

public class Banquier extends Utilisateur {
    private Database db;
    private ModelClient modelClient;
    private ModelCompte modelCompte;

    public Banquier(int id, String nom, String prenom, String mail, String address, String password, Database db) {
        super(id, nom, prenom, mail, address, password);
        this.db = db;
        this.modelClient = new ModelClient(this.db);
        this.modelCompte = new ModelCompte(this.db);
    }

    public void createClient(Client client) {
        if (!Utils.isEmailValid(client.getMail())) {
            System.out.println("L'adresse e-mail n'est pas valide.");
        } 

        if (client.getNom().isEmpty() || client.getPrenom().isEmpty() || client.getAddress().isEmpty()) {
            System.out.println("Les informations du client ne sont pas complètes.");
        }
        else{
              client.setId(modelClient.getClientNextId()); // Définit l'ID sur l'objet client existant
        modelClient.insertClient(client);
        System.out.println("Le client a été créé avec succès.");
        }
      
    }

    

    public void createAccount(Client client)  {
        
        try {
            client.getCompte().setId(modelCompte.getCompteNextId());
            client.getCompte().setNumCompte(modelCompte.getCompteNextNum());
            client.getCompte().setUid(client.getId());
            modelCompte.insertCompte(client.getCompte());
            System.out.println("Le compte de MR/MME"+client.getNom()+"a été créé avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du compte");
        }
        
    }
     
        @Override
        public String toString() {
            return "Banquier{" +
                ", nom='" + this.getNom()+ '\'' +
                ", prenom='" + this.getPrenom() + '\'' +
                ", mail='" + this.getNom() + '\'' +
                ", adresse=" + this.getAddress() +
                '}';
        }

       /* public static void main(String[] args) {
            Database db = new Database();
            Banquier banquier = new Banquier(0, "nom", "prenom", "mail@test.com", "address", "password", db);
            Client client = new Client(0, "client1", "client1", "client1@mail.com", "3 rue edgard faure", "client1");
            banquier.createClient(client);
          

            
        }*/

}
