package crist.bci.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crist.bci.banquier.Banquier;
import crist.bci.client.Client;
import crist.bci.database.Database;

public class Connexion {
    private Database db;

    public Connexion(Database db) {
         this.db = db;
    }

    public boolean verifyMail(String mail){
        String sql = "SELECT * FROM clients WHERE mail = ?";
        try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)){
            ps.setString(1, mail);
            ResultSet resultSet = ps.executeQuery();
            boolean result = resultSet.next();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

     public boolean verifyMailb(String mail){
        String sql = "SELECT * FROM banquier WHERE mail = ?";
        try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)){
            ps.setString(1, mail);
            ResultSet resultSet = ps.executeQuery();
            boolean result = resultSet.next();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyPassword(String email, String password){
        String sql = "SELECT * FROM clients WHERE mail=? AND password = ?";
        try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)){
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            boolean result = resultSet.next();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean verifyPasswordb(String email, String password){
        String sql = "SELECT * FROM banquier WHERE mail=? AND password = ?";
        try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)){
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            boolean result = resultSet.next();
            ps.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Client loginClient(String mail, String password) {

      
     Client client = null;


     String sql = "SELECT * FROM clients WHERE mail = ? AND password = ?";
         if(!verifyMail(mail) && !verifyPassword(mail, password)){
            throw new IllegalArgumentException("Le mail ou le mot de passe n'existe pas");
        }else{
                try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
                ps.setString(1, mail);
                ps.setString(2, password);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    client = new Client(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("mail"), resultSet.getString("address"));
                    System.out.println(client.getId());
                }
                resultSet.close();
                ps.close();
           } catch (SQLException e) {
                e.printStackTrace();
    
            }
        }
         return client;
        
   }

   public Banquier loginBanquier(String mail, String password) {

      
    Banquier banquier = null;


    String sql = "SELECT * FROM banquier WHERE mail = ? AND password = ?";
        if(!verifyMailb(mail) && !verifyPasswordb(mail, password)){
           throw new IllegalArgumentException("Le mail ou le mot de passe n'existe pas");
       }else{
               try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
               ps.setString(1, mail);
               ps.setString(2, password);
               ResultSet resultSet = ps.executeQuery();
               if (resultSet.next()) {
                   banquier = new Banquier(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("mail"), resultSet.getString("address"), resultSet.getString("password"), db);
                   System.out.println(banquier.getId());
               }
               resultSet.close();
               ps.close();
          } catch (SQLException e) {
               e.printStackTrace();
   
           }
        }
        return banquier;
    }

   public static void main(String[] args) throws SQLException {
       Database db = new Database();
       Connexion con = new Connexion(db);
        Client client=con.loginClient("finda.gedeon@gmail.com", "yag");
        System.out.println(client);
  }
}
