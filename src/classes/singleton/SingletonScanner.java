package classes.singleton;

import java.util.Scanner;

public class SingletonScanner {
    private static SingletonScanner instance = null;
    private Scanner scanner;

    private SingletonScanner() {
        System.out.println("SingletonScanner constructed");
        scanner = new Scanner(System.in);
    }

    public static SingletonScanner getInstance() {
        if(instance == null){
            instance = new SingletonScanner();
        }
        return instance;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
