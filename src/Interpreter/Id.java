package Interpreter;

import Tokenizer.Tokenizer;
import Tokenizer.TokenUtil;

public class Id implements INode {
    public String name;
    private Tokenizer tok;

    Id(String name, Tokenizer tok) { //		name = n; declared = false; initialized = false; }
        this.name = name;
        this.tok = tok;
    }

    //static Interpreter.Id*

    public void parse() {
        // current token should be an id
        if(tok.getToken() != TokenUtil.IDENTIFIER_NUM) {
            TokenUtil.unexpectedError(tok, "identifier");
        }
        if(tok.ids.containsKey(tok.idName())) {
                System.out.println("Error: Attempted to declare an id that was already declared.");
                System.exit(1);
        }
        // not initialized so set value to null

        tok.ids.put(tok.idName(), null);
        tok.skipToken();
    }
    public void parseTwo() {
        // initialization is an execution issue so just check if the variable is declared
        if(!tok.ids.containsKey(tok.idName())) {
            System.out.println("Error: Attempted to access a variable that was not declared.");
            System.exit(1);
        }
        tok.skipToken();
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        printer.print(name);
    }
    public void execute() {
        
    }

    int evaluate() {
        Integer idVal = tok.ids.get(name);
        if(idVal == null) {
            System.out.println("Attempted to access the value of an identifier that was uninitialized.");
            System.exit(1);
        }
        return idVal; // check initialized first?
    }
}