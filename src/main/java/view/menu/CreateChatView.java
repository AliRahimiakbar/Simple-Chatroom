package view.menu;

import cotroller.CreateChatMenu;
import view.terminal.ScanInput;
import view.terminal.TerminalOutput;
import java.util.ArrayList;

public class CreateChatView {

    private String currentUser;
    private CreateChatMenu createChatMenu;

    public CreateChatView(String username) {
        setCurrentUser(username);
        createChatMenu = new CreateChatMenu(username);
        run();
    }

    private void run() {
        String input;
        label:
        while (true) {
            TerminalOutput.output("1.Group\n2.Chanel\n3.Privacy");
            input = ScanInput.scanInput();
            switch (input) {
                case "1":
                    createGroup();
                    break label;
                case "2":
                    createChanel();
                    break label;
                case "3":
                    createPrivateChat();
                    break label;
                default:
                    TerminalOutput.output("Bad Command!");
                    break;
            }

        }
    }


    private void createPrivateChat(){
        TerminalOutput.output("Insert username you wants to add!");
        String input;
        ArrayList<String> usersWeWant = new ArrayList<>();
        while (true){
            input=ScanInput.scanInput();
            if (createChatMenu.isUserExist(input)) {
                TerminalOutput.output(input + " added!");
                createChatMenu.addUser(input);
                break;

            } else if (isDigit(input)) {
                int number = Integer.parseInt(input);
                if (usersWeWant.size() < number) {
                    TerminalOutput.output("Wrong number\nTry Again");
                } else {
                    TerminalOutput.output(usersWeWant.get(number - 1) + " added!");
                    createChatMenu.addUser(usersWeWant.get(number - 1));
                    break;
                }
            }else {
                TerminalOutput.output("User is not Exist");
                usersWeWant = createChatMenu.giveUsers(input);
                for (int i = 1; i <= usersWeWant.size(); i++) {
                    TerminalOutput.output(i + ". " + usersWeWant.get(i));
                }
            }
        }
        createChatMenu.createPrivateChat();
    }

    private void createChanel(){
        getUsers();
        TerminalOutput.output("Insert a name for channel");
        String input=ScanInput.scanInput();
        createChatMenu.createChanel(input);

    }

    private void createGroup() {
        getUsers();
        TerminalOutput.output("Insert a name for group");
        String input=ScanInput.scanInput();
        createChatMenu.createGroup(input);
    }

    private void getUsers() {
        TerminalOutput.output("Insert username you wants to add!\nif you want to end this write 'End'");
        String input;
        int flag = 0;
        ArrayList<String> usersWeWant = new ArrayList<>();
        while (true) {
            input = ScanInput.scanInput();
            if (input.equals("End")) {
                break;
            } else if (flag == 0) {
                if (createChatMenu.isUserExist(input)) {
                    TerminalOutput.output(input + " added!");
                    createChatMenu.addUser(input);
                } else {
                    flag = 1;
                    TerminalOutput.output("User is not Exist");
                    usersWeWant = createChatMenu.giveUsers(input);
                    for (int i = 1; i <= usersWeWant.size(); i++) {
                        TerminalOutput.output(i + ". " + usersWeWant.get(i-1));
                    }
                }
            } else if (flag == 1) {
                if (createChatMenu.isUserExist(input)) {
                    TerminalOutput.output(input + " added!");
                    createChatMenu.addUser(input);
                    flag = 0;
                    usersWeWant=null;
                } else if (isDigit(input)) {
                    int number = Integer.parseInt(input);
                    if (usersWeWant.size() < number) {
                        TerminalOutput.output("Wrong number\nTry Again");
                        flag = 1;
                    } else {
                        TerminalOutput.output(usersWeWant.get(number - 1) + " added!");
                        createChatMenu.addUser(usersWeWant.get(number - 1));
                        usersWeWant = null;
                        flag=0;
                    }
                }else {
                    TerminalOutput.output("User is not Exist");
                    usersWeWant = createChatMenu.giveUsers(input);
                    for (int i = 1; i <= usersWeWant.size(); i++) {
                        TerminalOutput.output(i + ". " + usersWeWant.get(i));
                    }
                }
            }
        }
    }

    private boolean isDigit(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
