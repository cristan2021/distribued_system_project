package crist.bci;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.Props;
import crist.bci.actor.BankActor;

public class App 
{
    public static void main( String[] args )
    {
        ActorSystem system = ActorSystem.create("system");
        ActorRef actor = system.actorOf(Props.create(BankActor.class), "actor");
        actor.tell("Hello World!", ActorRef.noSender());
        system.terminate();
    }
}
