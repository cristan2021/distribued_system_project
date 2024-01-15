package crist.bci.utils;

public class Operation {
    private String type;
    private  double montant;
    private String message;
    private int UID;

    public Operation(String type, double montant, int UID) {
        this.type = type;
        this.montant = montant;
        this.UID = UID;
    }

    public String getType() {
        return type;
    }

     public void setType(String type) {
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID){
        this.UID = UID;
    }
    
     @Override
    public String toString() {
        return "Operation [type=" + type + ", montant=" + montant + ", message=" + message + "]";
    }
}
