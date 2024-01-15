package crist.bci.Compte;

import crist.bci.database.Database;

public class Compte {
    private int id;
    private double solde;
    private int numCompte;
    private int uid;
  

    public Compte(int id,double solde, int numCompte,int uid) {
        this.id = id;
        this.solde = solde;
        this.numCompte = numCompte;
        this.uid = uid;
    }

    public Compte(int uid){
        this.id = 0;
        this.uid = uid;
        this.solde = 0;
        this.numCompte = 0;
    }




    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde){
        this.solde = solde;
    }

    public int getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(int numCompte){
        this.numCompte = numCompte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid){
        this.uid = uid;
    }





    public String toString() {
        return "Compte{"+
            "numCompte='" + this.getNumCompte() + '\'' +
            ",solde='" + this.getSolde()+ '\'' +
            "id='" + this.getId() + '\'' +
            ",uid='" + this.getUid() + '\'' +
            '}';
    }
  

}
