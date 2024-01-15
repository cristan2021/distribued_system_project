package crist.bci.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crist.bci.database.Database;
import crist.bci.client.Client;

public class ModelClient {
    private Database db;
    private Connection connection;

    public ModelClient(Database db) {
        this.db = db;
        this.connection = db.getConnection();
    }
    //donne l'id suivant de la table client
    public int getClientNextId() {
        int lastId = 0;
        ResultSet resultSet = null;
        String sql = "SELECT MAX(id) AS last_id FROM clients";

        try( PreparedStatement ps = connection.prepareStatement(sql)) {
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                lastId = resultSet.getInt("last_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return lastId+1;
    }

    public void insertClient(Client client){
         String sql = "INSERT INTO clients (id, nom, prenom, mail, address, password) VALUES (?, ?, ?, ?, ?, ?)";

        try( PreparedStatement ps = connection.prepareStatement(sql)){ 
            ps.setInt(1, client.getId());
            ps.setString(2, client.getNom());
            ps.setString(3, client.getPrenom());
            ps.setString(4, client.getMail());
            ps.setString(5, client.getAddress());
            ps.setString(6, client.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion du client");
            e.printStackTrace();
        } 
    }

    public Client getClientByMail(String mail) {
        Client client = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM clients WHERE mail = ?";

        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, mail);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                client = new Client(
                    resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("mail"),
                    resultSet.getString("address"),
                    resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }


 public boolean connexion(String mail, String password) throws SQLException {
     boolean result = false;
     String sql = "SELECT * FROM clients WHERE mail = ? AND password = ?";

     try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
         ps.setString(1, mail);
         ps.setString(2, password);
         ResultSet resultSet = ps.executeQuery();
         if (resultSet.next()) {
             result = true;
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }

     return result;
 }
}