package crist.bci.utils;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String address;
     private String password;

    public Utilisateur(int id, String nom, String prenom, String mail, String address, String password){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.address = address;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

  

    public String getNom() {
        return nom;
    }


    public void setNom(String nom){
        this.nom = nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

 

    public String getMail(){
        return mail;
    }

      public void setMail(String mail){
        this.mail = mail;
    }

  

     public String getPassword(){
        return password;
    }

     public void setPassword(String password){
        this.password = password;
    }

    public String getAddress(){
        return address;
    }


    public void setAddress(String address){
        this.address = address;
    }


}
