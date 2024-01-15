package crist.bci.utils;

public class Message{

    private String message;
    private String type;

    public Message(String message,String type){
        this.message = message;
        this.type = type;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }
}
