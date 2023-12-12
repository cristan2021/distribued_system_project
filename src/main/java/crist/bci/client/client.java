package crist.bci.client;

import crist.bci.account.compte;
import crist.bci.database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class client {

    private static int nextId = 1; // Variable statique pour générer des ID uniques
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private compte account;

    public client(String nom, String prenom,String mail, int numCompte) {
        this.id = nextId++; // Attribuer l'ID actuel et incrémenter pour le prochain client
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.account = new compte(0,  this.mail, this.prenom);
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public compte getCompte(){
        return account;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public void insertClient() {
        Database db = new Database();
        Connection conn = db.getConnection();
        String sql = "INSERT INTO clients (id, nom, prenom, mail) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, this.id);
            pstmt.setString(2, this.nom);
            pstmt.setString(3, this.prenom);
            pstmt.setString(4, this.mail);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Client getClient(int id) {
        Database db = new Database();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM clients WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String mail = rs.getString("mail");
                    return new Client(id, nom, prenom, mail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    

}