package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private User user;
    private String text;
    private LocalTime localTime;
    private int id;
    private DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm");
    private static int counter=0;
    private boolean onlineSender;
    private boolean isSeen;

    public Message(User user,String text,LocalTime localTime,boolean onlineSender){
        setUser(user);
        setText(text);
        setOnlineSender(onlineSender);
        setSeen(false);
        if (!onlineSender){
           setLocalTime(localTime);
        }
        else {
            setLocalTime(null);
        }
        id=counter;
        counter+=1;
    }


    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOnlineSender(boolean onlineSender) {
        this.onlineSender = onlineSender;
    }


    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getId() {
        return id;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public boolean isOnlineSender() {
        return onlineSender;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        if (onlineSender && !this.isSeen){
            return user.getUsername()+
                    "\tid: "+id+
                    "\ntime: OnlineSender"+
                    "\ntext: "+text;
        }
        return user.getUsername()+
                "\tid: "+id+
                "\ntime: "+localTime.format(formatter)+
                "\ntext: "+text;
    }
}
