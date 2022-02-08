package model.chats;

import model.Message;
import model.User;

import java.util.ArrayList;

public class Channel extends Chat {
    private String name;
    private ArrayList<User> allUser = new ArrayList<>();

    public Channel(User owner, String name, ArrayList<User> users){
        super(owner);
        setName(name);
        setAllUser(users);
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllUser(ArrayList<User> allUser) {
        this.allUser = allUser;
    }

    public void addMessage(Message message){
        allMessage.add(message);
    }

    public String getName() {
        return name;
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
