package crist.bci.actor;

import crist.bci.banquier.*;



public class CreateActorsBanker {

    private Banquier banquier;
        public CreateActorsBanker(Banquier banquier) {
            this.banquier = banquier;
        }   

        public Banquier getBanquier() {
            return banquier;
        }
}
