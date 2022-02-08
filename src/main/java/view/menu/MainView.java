package view.menu;

import view.terminal.ScanInput;
import view.terminal.TerminalOutput;

public class MainView {

    private String currentUser;

    public MainView(String username) {
        setCurrentUser(username);
        TerminalOutput.output("Hello " + username);
        run();
    }

    private void run() {
        String input;
        label:
        while (true) {
            TerminalOutput.output("1.All Chats\n2.Create chat\n3.Exit");
            input = ScanInput.scanInput();
            switch (input) {
                case "1":
                    AllChatView allChatView = new AllChatView(currentUser);
                    break;
                case "2":
                    CreateChatView createChatView = new CreateChatView(currentUser);
                    break;
                case "3":
                    TerminalOutput.output("Bye");
                    break label;
                default:
                    TerminalOutput.output("Bad Command!");
                    break;
            }

        }
    }


    private void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
