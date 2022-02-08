package view.menu;


import view.terminal.ScanInput;
import view.terminal.TerminalOutput;

public class WelcomeView {
    public void run() {
        TerminalOutput.output("Welcome to Chatroom!!");
        String inputData;
        while (true) {
            TerminalOutput.output("Please chose one option(Just enter Number)\n1.SignIn\n2.Login\n3.Exit");
            inputData =ScanInput.scanInput();
            if (inputData.equals("1")) {
                signIn();
            } else if (inputData.equals("2")) {
                login();
            } else if (inputData.equals("3")) {
                TerminalOutput.output("Goodbye!");
                break;
            } else {
                TerminalOutput.output("Bad command!\nTry again");
            }
        }
    }
    private void signIn(){
        RegisterView registerView=new RegisterView();
    }
    private void login(){
        LoginView loginView=new LoginView();
    }
}



