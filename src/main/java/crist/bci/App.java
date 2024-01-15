package crist.bci;

import java.sql.SQLException;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.CompletionStage;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import crist.bci.Compte.Compte;
import crist.bci.actor.BankActor;
import crist.bci.actor.BankerActor;
import crist.bci.actor.ClientActor;
import crist.bci.actor.CreateActorsBanker;
import crist.bci.actor.CreateActorsClient;
import crist.bci.banque.Banque;
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
      
    }

    public static void sysconnexion(String type, ActorRef bankActor, Connexion con) {
        System.out.println("connexion du " + type);
        System.out.println("Veuillez entrer le nom d'utilisateur " + type);
        String username = scanner.nextLine();
        if (Utils.isEmailValid(username)) {

            System.out.println("Veuillez entrer le mot de passe du banquier:");
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