package Interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    private static Reader INSTANCE;
    private Scanner scanner;
    private Reader() {
    }
    public static Reader instance() {
        if(INSTANCE == null) {
            INSTANCE = new Reader();
        }
        return INSTANCE;
    }
    public void initializeReader(String dataFile) {
        try {
            scanner = new Scanner(new File(dataFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeReader() {
        scanner.close();
    }
    public int getNextInt() {
        if(scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        else {
            System.out.println("\n" + "Error: attempted to read " +
                    "an integer from the data file, but reached end of file.");
            System.exit(1);
        }
        return -1;
    }
}
