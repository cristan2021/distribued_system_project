package crist.bci.actor;

import java.sql.SQLException;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import crist.bci.banquier.Banquier;
import crist.bci.client.Client;
import crist.bci.database.Database;
import crist.bci.models.ModelCompte;
import crist.bci.utils.Message;
import crist.bci.utils.Operation;

public class BankerActor extends AbstractActor {

    private ActorRef bank;
    private Database db;
    private LoggingAdapter log = getContext().getSystem().log();
    // private Database db;
    private ModelCompte modelCompte;

    public BankerActor(ActorRef bank) {
        this.bank = bank;
        try {
            this.db = new Database();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.modelCompte = new ModelCompte(db);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, s -> {
                    log.info("message reçu dans banquier: " + s);
                    bank.tell("oui je suis la", getSelf());
                })
                .match(Banquier.class, banquier -> {
                    System.out.println("Bonjour "+ banquier);
                })
                .match(Operation.class, operation -> {

                    if (operation.getType().equals("depot")) {
                        
                        modelCompte.depot(modelCompte.getCompte(operation.getUID()).getNumCompte(),
                                operation.getMontant());
                        bank.tell(new Message("depot de " + operation.getMontant() + " effectué", "depot"), getSelf());
    
                    } else if (operation.getType().equals("retrait")) {
                        if (modelCompte.getCompte(operation.getUID()).getSolde() < operation.getMontant()) {
                            bank.tell(new Message("solde insuffisant transaction refusé", "retrait"), getSelf());
                            return;
                        }
                        modelCompte.retrait(modelCompte.getCompte(operation.getUID()).getNumCompte(),
                                operation.getMontant());
                        bank.tell(new Message("retrait de " + operation.getMontant() + " effectué", "retrait"),
                                getSelf());
                    } else {
                        System.out.println("operation inconnue");
                        bank.tell(new Message("operation inconnue", "inconnue"), getSelf());
                    }
                })
                .match(Message.class, message -> {
                    System.out.println("message reçu: " + message.getMessage());
                })
                .matchAny(o -> System.out.println(" message inconnu"))
                .build();
    }

    public static Props props(ActorRef bank) {
        return Props.create(BankerActor.class, () -> new BankerActor(bank));
    }

}