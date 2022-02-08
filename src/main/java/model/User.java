package model;

import model.chats.Channel;
import model.chats.Chat;
import model.chats.Group;
import model.chats.PrivateChat;

import java.util.ArrayList;

public class User {
    private String fullName;
    private String username;
    private String password;
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Channel> channels = new ArrayList<>();
    private ArrayList<PrivateChat> privateChat = new ArrayList<>();
    private ArrayList<Chat> chats = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String fullName, String username, String password) {
        setFullName(fullName);
        setUsername(username);
        setPassword(password);
        allUsers.add(this);
    }


    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public void addGroup(Group group) {
        groups.add(group);
        chats.add(group);
    }

    public void addChannel(Channel channel){
        this.channels.add(channel);
        chats.add(channel);
    }

    public void addPrivate(PrivateChat privateChat){
        this.privateChat.add(privateChat);
        chats.add(privateChat);
    }

    public ArrayList<Chat> getChats(){
        return chats;
    }


    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static User getUserByUsername(String username) {
        for (User allUser : allUsers) {
            if (allUser.getUsername().equals(username)) {
                return allUser;
            }
        }
        return null;

    }


}
