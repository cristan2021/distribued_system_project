package crist.bci.utils;

public class message {
    private String message;
    private double montant;

    public message(String message, double montant){
        this.message = message;
        this.montant = 0;
    }

    public String getMessage(){
        return this.message;
    }
    public double getMontant(){
        return this.montant;
    }
}
