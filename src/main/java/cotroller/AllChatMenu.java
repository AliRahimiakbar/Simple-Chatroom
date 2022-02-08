package cotroller;

import model.Message;
import model.User;
import model.chats.Channel;
import model.chats.Chat;
import model.chats.Group;
import model.chats.PrivateChat;

import java.time.LocalTime;
import java.util.ArrayList;

public class AllChatMenu {

    private User user;
    private ArrayList<Chat> chats = new ArrayList<>();

    public AllChatMenu(String username) {
        setUser(User.getUserByUsername(username));
        chats = user.getChats();
        sortChats();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<String> getChats() {
        ArrayList<String> strings = new ArrayList<>();
        for (Chat chat : chats) {
            if (chat instanceof PrivateChat) {
                PrivateChat privateChat = (PrivateChat) chat;
                if (user.getUsername().equals(privateChat.getOtherUser().getUsername())) {
                    strings.add(chat.getOwner().getUsername() + "\tPrivateChat");
                } else {
                    strings.add(privateChat.getOtherUser().getUsername() + "\tPrivateChat");
                }
            } else if (chat instanceof Channel) {
                Channel channel = (Channel) chat;
                strings.add(channel.getName() + "\tChannel");
            } else {
                Group group = (Group) chat;
                strings.add(group.getName() + "\tGroup");
            }
        }
        return strings;
    }

    public void toChat(int i) {
        ChatMenu chatMenu = new ChatMenu(chats.get(i), user);
    }

    public void sortChats() {
        this.chats = user.getChats();
        if (chats.size()==1){
            if (chats.get(0) instanceof PrivateChat) {
                getTimeFromPrivate((PrivateChat) chats.get(0));
            }
            return;
        }
        for (int i = 0; i < chats.size(); i++) {
            for (int j = 0; j < chats.size() - 1; j++) {
                LocalTime time1, time2;
                if (chats.get(j) instanceof PrivateChat) {
                    time1 = getTimeFromPrivate((PrivateChat) chats.get(j));
                } else {
                    time1 = getTime((chats.get(j)).getLastMessage());
                }
                if (chats.get(j + 1) instanceof PrivateChat) {
                    time2 = getTimeFromPrivate((PrivateChat) chats.get(j + 1));
                } else {
                    time2 = getTime((chats.get(j + 1)).getLastMessage());
                }
                if (time1.isBefore(time2)) {
                    Chat chat = chats.get(j);
                    chats.set(j, chats.get(j + 1));
                    chats.set(j + 1, chat);
                }
            }
        }
    }

    public LocalTime getTime(Message message) {
        if (message==null){
            return LocalTime.MIN;
        }
        return message.getLocalTime();
    }

    public LocalTime getTimeFromPrivate(PrivateChat privateChat) {

        ArrayList<Message> messages = privateChat.getAllMessage();
        LocalTime time = LocalTime.MIN;
        int flag = 0;
        for (int i = messages.size() - 1; i >= 0; i--) {
            if ((messages.get(i).getUser().getUsername().equals(user.getUsername()))
                    || messages.get(i).isSeen()) {
                flag = i+1;
                break;
            }
        }
        for (int i = flag; i < messages.size(); i++) {
            if (messages.get(i).isOnlineSender()
                    && !messages.get(i).getUser().getUsername().equals(user.getUsername())) {
                time = LocalTime.now();
                messages.get(i).setLocalTime(LocalTime.now());
                messages.get(i).setSeen(true);
                privateChat.changeMessages(i);
            } else if (time.isBefore(messages.get(i).getLocalTime())) {
                time = messages.get(i).getLocalTime();
            }
        }
        return time;
    }
}
