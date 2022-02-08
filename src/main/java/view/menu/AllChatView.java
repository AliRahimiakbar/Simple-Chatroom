package view.menu;


import cotroller.AllChatMenu;
import view.terminal.ScanInput;
import view.terminal.TerminalOutput;

import java.util.ArrayList;

public class AllChatView {

    private String currentUser;
    private AllChatMenu allChatMenu;

    public AllChatView(String username) {
        setCurrentUser(username);
        allChatMenu = new AllChatMenu(username);
        run();
    }

    public void run() {
        String input;
        while (true) {
            allChatMenu.sortChats();
            ArrayList<String> strings = allChatMenu.getChats();
            showChats(strings);
            TerminalOutput.output("Insert Exit if you want go!");
            input= ScanInput.scanInput();
            if (input.equals("Exit"))
                break;
            else {
                try{
                    int id=Integer.parseInt(input);
                    allChatMenu.toChat(id-1);
                }catch (NumberFormatException e){
                    TerminalOutput.output("Bad command!");
                }

            }
        }
    }

    public void showChats(ArrayList<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            TerminalOutput.output((i+1)+". "+strings.get(i));
        }
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
