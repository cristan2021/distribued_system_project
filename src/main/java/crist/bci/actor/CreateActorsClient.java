package crist.bci.actor;

import crist.bci.client.Client;

public class CreateActorsClient {

    private Client client;

    public CreateActorsClient(Client client) {
        this.client = client;
        
    }

    public Client getClient() {
        return client;
    }


}
