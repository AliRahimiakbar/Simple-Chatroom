package cotroller;

import model.Message;
import model.User;
import model.chats.Channel;
import model.chats.Chat;
import model.chats.Group;
import model.chats.PrivateChat;
import view.terminal.ScanInput;
import view.terminal.TerminalOutput;

import javax.swing.table.TableRowSorter;
import java.time.LocalTime;
import java.util.ArrayList;

public class ChatMenu {

    private Chat chat;
    private User currentUser;

    public ChatMenu(Chat chat, User user){
        setChat(chat);
        setCurrentUser(user);
        if (chat instanceof Group)
            runGroup();
        else if (chat instanceof Channel)
            runChannel();
        else
            runPrivate();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void runGroup(){
        Group group=(Group) chat;
        label:
        while (true){
            ArrayList<Message> messages=chat.getAllMessage();
            for (Message message : messages) {
                TerminalOutput.output(message.toString());
            }
            TerminalOutput.output("1.send message\n2.remove message\n3.edit message\n4.Exit");
            String input = ScanInput.scanInput();
            switch (input) {
                case "1":
                    sendMessageForGroup(group);
                    break;
                case "2":
                    removeMessageForGroup(messages, group);
                    break;
                case "3":
                    editMessageForGroup(messages);
                    break;
                case "4":
                    break label;
                default:
                    TerminalOutput.output("Bad Command");
                    break;
            }
        }
    }


    public void runChannel(){
        Channel channel=(Channel) chat;
        label:
        while (true){
            ArrayList<Message> messages=chat.getAllMessage();
            for (Message message : messages) {
                TerminalOutput.output(message.toString());
            }
            if (currentUser.getUsername().equals(chat.getOwner().getUsername())) {
                TerminalOutput.output("1.send message\n2.remove message\n3.edit message\n4.Exit");
                String input = ScanInput.scanInput();
                switch (input) {
                    case "1":
                        sendMessageForChannel(channel);
                        break;
                    case "2":
                        removeMessageForChannel(messages, channel);
                        break;
                    case "3":
                        editMessageForChannel(messages);
                        break;
                    case "4":
                        break label;
                    default:
                        TerminalOutput.output("Bad Command");
                        break;
                }
            }else {
                TerminalOutput.output("1.Exit");
                String input = ScanInput.scanInput();
                if (input.equals("1"))
                    break label;
            }
        }
    }

    public void runPrivate(){
        PrivateChat privateChat = (PrivateChat) chat;
        label:
        while (true){
            ArrayList<Message> messages=chat.getAllMessage();
            for (Message message : messages) {
                TerminalOutput.output(message.toString());
            }
            TerminalOutput.output("1.send message\n2.remove message\n3.edit message\n4.Exit");
            String input = ScanInput.scanInput();
            switch (input) {
                case "1":
                    sendMessageForPrivate(privateChat);
                    break;
                case "2":
                    removeMessageForPrivate(messages, privateChat);
                    break;
                case "3":
                    editMessageForPrivate(messages);
                    break;
                case "4":
                    break label;
                default:
                    TerminalOutput.output("Bad Command");
                    break;
            }
        }

    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void editMessageForPrivate(ArrayList<Message> messages){
        TerminalOutput.output("which id?");
        String input=ScanInput.scanInput();
        int id = Integer.parseInt(input);
        for (Message message : messages) {
            if (message.getId()==id){
                if (message.getUser().getUsername().equals(this.currentUser.getUsername())){
                    TerminalOutput.output("put new text");
                    message.setText(ScanInput.scanInput());
                    TerminalOutput.output("Edited");
                    return;
                }
                TerminalOutput.output("You dont have access!");
                return;
            }
        }
    }

    public void removeMessageForPrivate(ArrayList<Message> messages,PrivateChat privateChat){
        TerminalOutput.output("which id?");
        String input=ScanInput.scanInput();
        int id = Integer.parseInt(input);
        for (Message message : messages) {
            if (message.getId()==id){
                if (message.getUser().getUsername().equals(this.currentUser.getUsername())){
                    TerminalOutput.output("Removed");
                    privateChat.removeMessage(id);
                    return;
                }
                TerminalOutput.output("You dont have access!");
                return;
            }
        }
        TerminalOutput.output("Not Found This id");
    }

    public void sendMessageForPrivate(PrivateChat privateChat){
        TerminalOutput.output("insert text");
        String text = ScanInput.scanInput();
        TerminalOutput.output("Online Sender?\n1.Yes\n2.No");
        String inputData = ScanInput.scanInput();
        boolean onlineSender;
        onlineSender= inputData.equals("1");
        Message message=new Message(currentUser,text, LocalTime.now(),onlineSender);
        privateChat.addMessage(message);
    }

    public void editMessageForChannel(ArrayList<Message> messages){
        TerminalOutput.output("which id?");
        String input=ScanInput.scanInput();
        int id = Integer.parseInt(input);
        for (Message message : messages) {
            if (message.getId()==id){
                TerminalOutput.output("put new text");
                message.setText(ScanInput.scanInput());
                TerminalOutput.output("Edited");
                return;
            }
        }
    }

    public void removeMessageForChannel(ArrayList<Message> messages,Channel channel){
        TerminalOutput.output("which id?");
        String input=ScanInput.scanInput();
        int id = Integer.parseInt(input);
        for (Message message : messages) {
            if (message.getId()==id){
                 TerminalOutput.output("Removed");
                 channel.removeMessage(id);
                 return;
            }
        }
        TerminalOutput.output("Not Found This id");
    }

    public void sendMessageForChannel(Channel channel){
        TerminalOutput.output("insert text");
        String text = ScanInput.scanInput();
        Message message=new Message(currentUser,text, LocalTime.now(),false);
        channel.addMessage(message);
    }

    public void editMessageForGroup(ArrayList<Message> messages){
        TerminalOutput.output("which id?");
        String input=ScanInput.scanInput();
        int id = Integer.parseInt(input);
        for (Message message : messages) {
            if (message.getId()==id){
                if (message.getUser().getUsername().equals(this.currentUser.getUsername())
                        || currentUser.getUsername().equals(chat.getOwner().getUsername())){
                    TerminalOutput.output("put new text");
                    message.setText(ScanInput.scanInput());
                    TerminalOutput.output("Edited");
                    return;
                }
                TerminalOutput.output("You dont have access!");
                return;
            }
        }

    }


    public void removeMessageForGroup(ArrayList<Message> messages, Group group){
        TerminalOutput.output("which id?");
        String input=ScanInput.scanInput();
        int id = Integer.parseInt(input);
        for (Message message : messages) {
            if (message.getId()==id){
                if (message.getUser().getUsername().equals(this.currentUser.getUsername())
                        || currentUser.getUsername().equals(chat.getOwner().getUsername())){
                    TerminalOutput.output("Removed");
                    group.removeMessage(id);
                    return;
                }
                TerminalOutput.output("You dont have access!");
                return;
            }
        }
        TerminalOutput.output("Not Found This id");
    }


    public void sendMessageForGroup(Group group){
        TerminalOutput.output("insert text");
        String text = ScanInput.scanInput();
        Message message=new Message(currentUser,text, LocalTime.now(),false);
        group.addMessage(message);
    }
}
