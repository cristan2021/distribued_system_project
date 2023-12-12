package crist.bci.actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class BankerActor extends AbstractActor{
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, s -> {
                    System.out.println("message reçu: " + s);
                })
                .matchAny(o -> System.out.println("received unknown message"))
                .build();
    }
}