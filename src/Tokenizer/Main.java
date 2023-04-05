package Tokenizer;

import Interpreter.Program;
import Interpreter.Reader;

public class Main {

    public static void main(String[] args) {
        // No input file name
        if(args.length == 0) {
            System.out.println("Please input the name of a file to scan.");
        }
        if(args.length == 1) {
            System.out.println("Please also input the name of a data file to scan.");
        }
        else {
            // file reader is stored in tokenizer since interpreter will have access to a reference of the tokenizer
            Tokenizer scanner = new Tokenizer(args[0]);
            Program program = new Program(scanner);
            program.parse();
            scanner.closeInputStream();
            program.print();
            System.out.println("\nExecution Output: ");
            Reader.instance().initializeReader(args[1]);
            program.execute();
            Reader.instance().closeReader();
            System.exit(1214214);
        }
    }

}
