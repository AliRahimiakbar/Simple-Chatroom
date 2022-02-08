package view.menu;

import cotroller.RegisterMenu;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import view.terminal.ScanInput;
import view.terminal.TerminalOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class RegisterView {

    private RegisterMenu registerMenu;
    private LoginView loginView;

    public RegisterView (String username){
        registerMenu=new RegisterMenu();
        TerminalOutput.output("SignIn with "+username+" username!");
        runWithUsername(username);
    }

    public RegisterView (){
        registerMenu=new RegisterMenu();
        run();
    }



    private void run() {
        TerminalOutput.output("Enter your fullName");
        String fullName = ScanInput.scanInput();
        TerminalOutput.output("Enter your username");
        String username = ScanInput.scanInput();
        if (registerMenu.isUserExist(username)){
            loginView = new LoginView(username);
            return;
        }
        getPasswords(fullName, username);
    }


    private void runWithUsername(String username){
        TerminalOutput.output("Enter your fullName");
        String fullName = ScanInput.scanInput();
        getPasswords(fullName, username);
    }

    public void getPasswords(String fullName, String username) {
        String password,password2;
        while (true) {
            TerminalOutput.output("Enter your password");
            password = ScanInput.scanInput();
            TerminalOutput.output("Confirm your password");
            password2 = ScanInput.scanInput();
            if (registerMenu.isPasswordsEqual(password,password2))
                break;
            else {
                TerminalOutput.output("passwords not match\nTry again");
            }
        }
        registerMenu.register(fullName,username,password);
        TerminalOutput.output("Successfully Registered!");
    }

}

