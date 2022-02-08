package view.menu;

import cotroller.LoginMenu;
import view.terminal.ScanInput;
import view.terminal.TerminalOutput;

public class LoginView {

    private LoginMenu loginMenu;
    private RegisterView registerView;

    public LoginView(String username) {
        loginMenu = new LoginMenu();
        TerminalOutput.output("Login with " + username + " username");
        runWithUsername(username);
    }

    public LoginView() {
        loginMenu = new LoginMenu();
        run();
    }

    private void runWithUsername(String username) {
        if (!isLoggedIn(username)){
            TerminalOutput.output("Wrong password\nToo many try!!");
        }
        MainView mainView=new MainView(username);
    }


    private void run() {
        TerminalOutput.output("Enter your username");
        String username = ScanInput.scanInput();
        if (!loginMenu.isUserExist(username)) {
            registerView = new RegisterView(username);
            return;
        }
        if (!isLoggedIn(username)){
            TerminalOutput.output("Wrong password\nToo many try!!");
        }
        MainView mainView=new MainView(username);
    }

    private boolean isLoggedIn(String username) {
        boolean passwordRight = false;
        for (int i = 0; i < 3; i++) {
            TerminalOutput.output("Enter your password");
            String password = ScanInput.scanInput();
            if (loginMenu.isPasswordMatch(username, password)) {
                passwordRight = true;
                break;
            }
            if (i != 2)
                TerminalOutput.output("Wrong password\nTry again");
        }
        return passwordRight;
    }

}
