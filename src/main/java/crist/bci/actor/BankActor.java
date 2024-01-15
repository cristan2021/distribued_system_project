package crist.bci.actor;

import crist.bci.banquier.Banquier;
import crist.bci.client.Client;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import crist.bci.utils.Message;
import crist.bci.utils.Operation;

public class BankActor extends AbstractActor {

    private ActorRef banker;
    private ActorRef client;
    private ActorRef bank;
    private LoggingAdapter log = getContext().getSystem().log();

    public BankActor() {
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, s -> {
                    if (s.startsWith("oui")) {
                        System.out.println("message reçu du client: " + s);
                        this.getBanker().tell("oui le client est la", getSelf());
                    } else if (s.startsWith("merci pour ton accueil"))
                        System.out.println("message reçu du banquier: " + s);
                    this.getClient().tell("est ce que tu es la", getSelf());
                })
                .match(CreateActorsClient.class, createActorsClient -> {
                    createClientActor(createActorsClient.getClient());
                })
                .match(CreateActorsBanker.class, createActorsBanker -> {
                    createBankerActor(createActorsBanker.getBanquier());
                })
                .match(Operation.class, operation -> {

                    if (operation.getType().equals("depot")) {
                        banker.tell(operation, getSelf());
                    } else if (operation.getType().equals("retrait")) {
                        banker.tell(operation, getSelf());
                    } else {
                        System.out.println("operation inconnue");
                        banker.tell(new Message("operation inconnue", "inconnue"), getSelf());
                    }
                })
                .match(Message.class, message -> {
                    if (getSender().equals(banker)) {
                        if (message.getType().equals("depot")) {
                            client.tell(message, getSelf());
                        } else if (message.getType().equals("retrait")) {
                            client.tell(message, getSelf());
                        } else {
                            System.out.println("operation inconnue");
                            client.tell(new Message("operation inconnue", "inconnue"), getSelf());
                        }
                    }
                })
                .matchAny(o -> System.out.println("message inconnu"))
                .build();
    }

    public static Props props() {
        return Props.create(BankActor.class, BankActor::new);
    }

    public void createActors() {
        this.banker = getContext().actorOf(BankerActor.props(getSelf()), "bankerActor");
        this.client = getContext().actorOf(ClientActor.props(getSelf()), "clientActor");
    }

    public void createClientActor(Client client) {
        this.client = getContext().actorOf(ClientActor.props(getSelf()), "clientActor");
        log.info("client created, bienvenu au client ");
        this.getClient().tell(client, bank);
    }

    public void createBankerActor(Banquier banquier) {
        this.banker = getContext().actorOf(BankerActor.props(getSelf()), "bankerActor");
        log.info("banquier created, bienvenu au banquier");
        this.getBanker().tell(banquier, bank);
    }
    
    public ActorRef getBanker() {
        return banker;
    }

    public ActorRef getClient() {
        return client;
    }

}
