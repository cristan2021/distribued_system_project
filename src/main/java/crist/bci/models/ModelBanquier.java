package crist.bci.models;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import crist.bci.banquier.Banquier;
import crist.bci.database.Database;

public class ModelBanquier {
    private Database db;

    public ModelBanquier(Database db) {
        this.db = db;
    }

    //donne l'id suivant de la table banquier
    public int getBanquierNextId() {
        int lastId = 0;
        ResultSet resultSet = null;
        String sql = "SELECT MAX(id) AS last_id FROM banquier";

        try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)){
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                lastId = resultSet.getInt("last_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return lastId+1;
    } 

    public void insertBanquier(Banquier banquier) {
        String sql = "INSERT INTO banquier (id, nom, prenom, mail, address, password) VALUES (?, ?, ?, ?, ?, ?)";

        try( PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
            ps.setInt(1, banquier.getId());
            ps.setString(2, banquier.getNom());
            ps.setString(3, banquier.getPrenom());
            ps.setString(4, banquier.getMail());
            ps.setString(5, banquier.getAddress());
            ps.setString(6, banquier.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}