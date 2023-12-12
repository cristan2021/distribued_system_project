package crist.bci.bank;

import java.util.ArrayList;

import crist.bci.account.compte;
import crist.bci.client.client;
public class bank {

    private ArrayList<client> clients;
    private ArrayList<compte> comptes;
    private int number_client;

    public bank(){
        this.clients = new ArrayList<client>();
        this.comptes = new ArrayList<compte>();
        this.number_client = 0;
    }

    public void add_client(String nom, String prenom, int numCompte){
        this.clients.add(new client(nom, prenom, numCompte));
        this.number_client++;
    }

    

    public void remove_client(int id){
        this.clients.remove(id);
    }

    public void set_nom_client(int id, String nom){
        this.clients.get(id).setNom(nom);
    }

    public void set_prenom_client(int id, String prenom){
        this.clients.get(id).setPrenom(prenom);
    }


    /*public void set_number_account_client(int id, int number_account){
        this.clients.get(id).setnumCompte(number_account);
    }*/


    public int get_number_client(){
        return this.number_client;
    }


    public String get_nom_client(int id){
        return this.clients.get(id).getNom();
    }




    
}
