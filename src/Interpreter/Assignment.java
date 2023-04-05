package Interpreter;

import Tokenizer.TokenUtil;
import Tokenizer.Tokenizer;

public class Assignment implements INode {
    private Tokenizer tok;
    private Id id;
    private Expression expression;
    public Assignment(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        id.print();
        printer.print("=");
        expression.print();
        printer.print(";");
    }
    public void parse() {
        // first element is an id
        id = new Id(tok.idName(), tok);
        id.parseTwo();
        // second element should be an equals
        if(tok.getToken() != TokenUtil.EQUALS_NUM) {
            TokenUtil.unexpectedError(tok, "equals (assign)");
        }
        tok.skipToken();
        // third element should be an expression
        expression = new Expression(tok);
        expression.parse();
        // fourth element should be a semicolon
        if(tok.getToken() != TokenUtil.SEMICOLON_NUM) {
            TokenUtil.unexpectedError(tok, "semicolon");
        }
        tok.skipToken();
    }

    public void execute() {
        // we parsed this and created an id, so we know it's in the list (no check necessary)
        tok.ids.put(id.name, expression.evaluate());
    }
}
