package crist.bci.account;

public class compte {
    private static int nextAccount = 200205;
    private static int nextID = 0;
    private double solde;
    private int numCompte;
    private int id;
    private String password;
    private String username;

    public compte(double solde, String username, String password) {
        this.solde = solde;
        this.id = nextID++;
        this.numCompte=nextAccount++;
        this.password = password;
        this.username = username;
    }

    public double getSolde() {
        return solde;
    }

    public int getNumCompte() {
        return numCompte;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername(){
        return username;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setNumCompte(int numCompte) {
        this.numCompte = numCompte;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void depot(double montant){
        this.solde += montant;
    }

    public void retrait(double montant){
        if(this.solde >= montant){
            this.solde -= montant;
        }else {
            System.out.println("Solde insuffisant");
        }
    }

}
