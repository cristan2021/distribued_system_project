package crist.bci.actor;
import crist.bci.client.Client;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import crist.bci.utils.Message;
import crist.bci.utils.Operation;

public class ClientActor extends AbstractActor{
    private ActorRef bank;
    private LoggingAdapter log = getContext().getSystem().log();


    public ClientActor(ActorRef bank) {
        this.bank=bank;

    }
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, s -> {
                   log.info("message reçu dans client: " + s);
                    bank.tell("oui je suis la", getSelf());
                })
                .match(Client.class, client -> {
                    System.out.println("Bonjour "+ client);
                })

                .match(Operation.class, operation -> {
                    if(operation.getType().equals("depot")){
                        System.out.println("depot de "+operation.getMontant()+" effectué");
                        bank.tell(new Message("depot de "+operation.getMontant()+" effectué","depot"),getSelf());
                    }
                    else if(operation.getType().equals("retrait")){
                        System.out.println("retrait de "+operation.getMontant()+" effectué");
                        bank.tell(new Message("retrait de "+operation.getMontant()+" effectué","retrait"),getSelf());
                    }
                    else{
                        System.out.println("operation inconnue");
                        bank.tell(new Message("operation inconnue","inconnue"),getSelf());
                    }
                })
                .match(Message.class,message -> {
                    System.out.println("message reçu: " + message.getMessage());
                })
                

                .matchAny(o -> System.out.println("received unknown message"))
                .build();
    }

    public static Props props(ActorRef bank) {
        return Props.create(ClientActor.class,()->new ClientActor(bank));
    }

   /* public static void main(String[] args) {
        // Create an ActorSystem
        ActorSystem system = ActorSystem.create("BankSystem");


        // Create a ClientActor
        ActorRef clientActor = system.actorOf(ClientActor.props(), "clientActor");

        // Send a deposit operation to the ClientActor
        Operation operation = new Operation("depot", 100, "123"); // Initialize your operation here
        clientActor.tell(operation, ActorRef.noSender());
    }*/
}