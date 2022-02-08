package model.chats;

import model.Message;
import model.User;

import java.util.ArrayList;


public class PrivateChat extends Chat {
    private User otherUser;

    public PrivateChat(User owner, User otherUser){
        super(owner);
        setOtherUser(otherUser);
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }

    public void addMessage(Message message){
        allMessage.add(message);
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void changeMessages(int i){
        Message message=allMessage.get(i);
        allMessage.remove(i);
        allMessage.add(message);
    }

    public void removeMessage(int id){
        for (int i = 0; i < allMessage.size(); i++) {
            if (allMessage.get(i).getId()==id) {
                allMessage.remove(i);
                return;
            }
        }
    }
}
