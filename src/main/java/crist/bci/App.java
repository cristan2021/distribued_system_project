package crist.bci;

import java.sql.SQLException;
import java.util.Scanner;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import crist.bci.Compte.Compte;
import crist.bci.actor.BankActor;
import crist.bci.actor.CreateActorsBanker;
import crist.bci.actor.CreateActorsClient;
import crist.bci.banquier.Banquier;
import crist.bci.client.Client;
import crist.bci.database.Database;
import crist.bci.models.Connexion;
import crist.bci.models.ModelCompte;
import crist.bci.utils.Operation;
import crist.bci.utils.Utils;

public class App {
    static Client client;
    static Scanner scanner = new Scanner(System.in);
    static boolean isConnected = true;
    static boolean actif = true;
    static Client currentClient = null;
    static Banquier currentBanquier;
    static Compte compte;

    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        Connexion con = new Connexion(db);
        ModelCompte modelCompte = new ModelCompte(db);
        ActorSystem system = ActorSystem.create("BankSystem");
        ActorRef bankActor = system.actorOf(BankActor.props(), "bankActor");

        sysconnexion("client", bankActor, con);
        sysconnexion("banquier", bankActor, con);
        while (true) {
            Utils.menuClient();
            System.out.println("Veuillez entrer un chiffre entre 1 et 4.");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    Utils.menuClient();
                    System.out.println("Votre solde est de : " +
                            modelCompte.getCompte(currentClient.getId()).getSolde() + "€");

                    break;
                case 2:

                    double amount = 0;
                    System.out.println("Veuillez entrer le montant à déposer :");
                    while (!scanner.hasNextDouble() || (amount = scanner.nextDouble()) <= 0) {
                        System.out.println("Veuillez entrer un montant valide");
                        scanner.nextLine(); // this is important!
                    }
                    bankActor.tell(new Operation("depot", amount, currentClient.getId()), ActorRef.noSender());

                    break;
                case 3:
                    double amount2 = 0;
                    System.out.println("Veuillez entrer le montant à retirer :");
                    while (!scanner.hasNextDouble() || (amount2 = scanner.nextDouble()) <= 0) {
                        System.out.println("Veuillez entrer un montant valide");
                        scanner.nextLine(); // this is important!
                    }
                    bankActor.tell(new Operation("retrait", amount2, currentClient.getId()), ActorRef.noSender());
                    break;
                case 4:
                System.out.println("Au revoir " + currentClient.getNom() + "" +
                currentClient.getPrenom() + " au plaisir de vous revoir!");
                system.terminate();
                //isConnected = false;
                break;

                default:
                    Utils.menuClient();
                    System.out.println("veuillez faire un choix entre les options 1 et 4.");

                    break;
            }
        }
    }

    public static void sysconnexion(String type, ActorRef bankActor, Connexion con) {
        System.out.println("connexion du " + type);
        System.out.println("Veuillez entrer le nom d'utilisateur " + type);
        String username = scanner.nextLine();
        if (Utils.isEmailValid(username)) {

            System.out.println("Veuillez entrer le mot de passe:");
            String password = scanner.nextLine();
            if (type.equals("client")) {
                currentClient = con.loginClient(username, password);
                if (currentClient != null) {
                    bankActor.tell(new CreateActorsClient(currentClient), ActorRef.noSender());
                } else {
                    System.out.println("l'email ou le mot de passe n'existe pas");
                }
            } else if (type.equals("banquier")) {
                currentBanquier = con.loginBanquier(username, password);
                if (currentBanquier != null) {
                    bankActor.tell(new CreateActorsBanker(currentBanquier), ActorRef.noSender());
                } else {
                    System.out.println("l'email ou le mot de passe n'existe pas");
                }
            }
        }

    }
}