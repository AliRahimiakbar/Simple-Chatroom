package model.chats;

import model.Message;
import model.User;

import java.util.ArrayList;

public class Group extends Chat {
    private ArrayList<User> allUsers = new ArrayList<>();
    private String name;

    public Group(User owner, String name, ArrayList<User> allUsers){
        super(owner);
        setName(name);
        setAllUsers(allUsers);
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }


    public void addMessage(Message message){
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
