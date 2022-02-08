package model.chats;

import model.Message;
import model.User;

import java.time.LocalTime;
import java.util.ArrayList;

public class Chat {
    public User owner;
    public ArrayList<Message> allMessage=new ArrayList<>();
    public int id;
    public static int counter=0;

    public Chat(User owner){
        setOwner(owner);
        id=counter;
        counter+=1;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Message> getAllMessage() {
        return allMessage;
    }

    public Message getLastMessage(){
        if (allMessage.size()==0){
            return null;
        }
        return allMessage.get(allMessage.size()-1);
    }
}
