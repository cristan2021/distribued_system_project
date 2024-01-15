package crist.bci.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ActorSystem;


public class MyActorSys  {
    
    private ActorRef bank;

    private ActorSystem system;
    
    public MyActorSys() {
       system = ActorSystem.create("BankSystem");
    this.bank = system.actorOf(Props.create(BankActor.class), "bankActor");    }
   

    
    public ActorRef getBank() {
        return this.bank;
    }
 
}
