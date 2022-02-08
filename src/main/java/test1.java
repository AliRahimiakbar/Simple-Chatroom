import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import view.menu.RegisterView;
import view.menu.WelcomeView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class test1 {
    private static PrintStream printStream = System.out;
    private static InputStream inputStream = System.in;
    private static ByteArrayOutputStream outputStream;

    @BeforeAll
    static void change() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterAll
    static void change2() {
        System.setOut(printStream);
        System.setIn(inputStream);
    }

    @Test
    void getPass() {
        System.setIn(new ByteArrayInputStream("ali\nmamali\n123\n123".getBytes()));
        new RegisterView();
        Assertions.assertEquals("Enter your fullName\n" +
                "Enter your username\n" +
                "Enter your password\n" +
                "Confirm your password\n" +
                "Successfully Registered!\n", outputStream.toString().replaceAll("\r", ""));
    }
}
class test{
    private static PrintStream printStream = System.out;
    private static InputStream inputStream = System.in;
    private static WelcomeView welcomeView = new WelcomeView();
    private static ByteArrayOutputStream outputStream;
    @BeforeAll
    static void change(){
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    @AfterAll
    static void change2(){
        System.setOut(printStream);
        System.setIn(inputStream);
    }
    @Test
    void check(){
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream("3".getBytes());
        System.setIn(inputStream1);
        welcomeView.run();
        Assertions.assertEquals("Welcome to Telegram!!\n" +
                "Please chose one option(Just enter Number)\n" +
                "1.SignIn\n" +
                "2.Login\n" +
                "3.Exit\n" +
                "Goodbye!\n", outputStream.toString().replaceAll("\r",""));
    }
}

