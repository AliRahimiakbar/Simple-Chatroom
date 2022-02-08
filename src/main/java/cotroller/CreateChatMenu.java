package cotroller;

import model.User;
import model.chats.Channel;
import model.chats.Group;
import model.chats.PrivateChat;

import java.util.ArrayList;

public class CreateChatMenu {

    private User user;
    private ArrayList<User> users;

    public CreateChatMenu(String username) {
        setUser(User.getUserByUsername(username));
        this.users = new ArrayList<>();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isUserExist(String username) {
        return User.getUserByUsername(username) != null;
    }

    public void addUser(String username) {
        users.add(User.getUserByUsername(username));
    }

    public ArrayList<String> giveUsers(String username) {
        ArrayList<User> allUser = User.getAllUsers();
        ArrayList<String> usersWeWant = new ArrayList<>();
        for (User user : allUser) {
            if (user.getUsername().length() != username.length() || user.getUsername().equals(this.user.getUsername()))
                continue;
            else if (findDifference(user.getUsername(), username) == 1)
                usersWeWant.add(user.getUsername());
        }
        return usersWeWant;
    }

    public int findDifference(String username1, String username2) {
        int difference = 0;
        for (int i = 0; i < username1.length(); i++) {
            if (username1.charAt(i) != username2.charAt(i)) {
                difference += 1;
            }
        }
        return difference;
    }

    public void createGroup(String name) {
        Group group = new Group(this.user, name, this.users);
        for (User user1 : users) {
            user1.addGroup(group);
        }
        this.user.addGroup(group);
        ChatMenu chatMenu=new ChatMenu(group,this.user);
    }

    public void createChanel(String name) {
        Channel channel = new Channel(this.user,name,this.users);
        for (User user1 : users) {
            user1.addChannel(channel);
        }
        this.user.addChannel(channel);
        ChatMenu chatMenu=new ChatMenu(channel,this.user);
    }

    public void createPrivateChat(){
        PrivateChat privateChat= new PrivateChat(this.user,this.users.get(0));
        this.user.addPrivate(privateChat);
        this.users.get(0).addPrivate(privateChat);
        ChatMenu chatMenu=new ChatMenu(privateChat,this.user);
    }



}
