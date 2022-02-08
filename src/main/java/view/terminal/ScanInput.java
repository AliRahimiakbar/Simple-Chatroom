package view.terminal;
import java.util.Scanner;

public class ScanInput {
    private static Scanner scanner=new Scanner(System.in);
    public static String scanInput(){
        return scanner.nextLine();
    }
}
