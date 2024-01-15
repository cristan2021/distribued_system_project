package crist.bci.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crist.bci.Compte.Compte;
import crist.bci.database.Database;

public class ModelCompte {
    private Database db;
    
    public ModelCompte(Database db) {
        this.db = db;
    }
        public int getCompteNextId() throws SQLException {
            int result = 0;
            String sql = "SELECT MAX(id) AS last_id FROM comptes";

            try (PreparedStatement ps = db.getConnection().prepareStatement(sql);
                 ResultSet resultSet = ps.executeQuery()) {

                if (resultSet.next()) {
                    result = resultSet.getInt("last_id");
                }
            } catch (SQLException e) {
                e.printStackTrace(); 
            }

            return result+1;
        }

            public int getCompteNextNum() throws SQLException {
            int result = 0;
            String sql = "SELECT MAX(numCompte) AS last FROM comptes";

            try (PreparedStatement ps = db.getConnection().prepareStatement(sql);
                 ResultSet resultSet = ps.executeQuery()) {

                if (resultSet.next()) {
                    result = resultSet.getInt("last");
                }
            } catch (SQLException e) {
                e.printStackTrace(); 
            }

            return result+1;
        }

        public void insertCompte(Compte compte) throws SQLException {
            String sql = "INSERT INTO comptes (id, solde, numCompte, uid) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
                ps.setInt(1, compte.getId());
                ps.setDouble(2, compte.getSolde());
                ps.setInt(3, compte.getNumCompte());
                ps.setInt(4, compte.getUid());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void depot(int numCompte, double amount) throws SQLException {
            Database db = new Database();
            String sql = "UPDATE comptes SET solde = solde + ? WHERE numCompte = ?";

            try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
                ps.setDouble(1, amount);
                ps.setInt(2, numCompte);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void retrait(int numCompte, double amount) throws SQLException {
            Database db = new Database();
            String sql = "UPDATE comptes SET solde = solde - ? WHERE numCompte = ?";

            try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
                ps.setDouble(1, amount);
                ps.setInt(2, numCompte);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public double seeSolde(int numCompte) throws SQLException {
            double result = 0.0;
            String sql = "SELECT solde FROM comptes WHERE numCompte = ?";

            try (PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
                ps.setInt(1, numCompte);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    result = resultSet.getDouble("solde");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return result;
        }

        public Compte getCompte(int id){
            Compte compte = null;
            ResultSet resultSet = null;
            String sql = "SELECT * FROM comptes WHERE uid = ?";

            try ( PreparedStatement ps = db.getConnection().prepareStatement(sql)) {
                ps.setInt(1, id);
                resultSet = ps.executeQuery();

                if (resultSet.next()) {
                    compte = new Compte(resultSet.getInt("id"));
                        compte.setSolde(resultSet.getDouble("solde"));
                        compte.setNumCompte(resultSet.getInt("numCompte"));
                        compte.setUid(resultSet.getInt("uid"));
                   
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return compte;
        }

        public static void main(String[] args) throws SQLException {
            Database db = new Database();
            ModelCompte modelCompte = new ModelCompte(db);      
            System.out.println(modelCompte.getCompte(2));
        }
}