package Interpreter;

import Tokenizer.TokenUtil;
import Tokenizer.Tokenizer;

public class Operation implements INode {
    private Tokenizer tok;
    public int altNo;
    private int integer;
    private Id id;
    private Expression exp;
    public Operation(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        switch (altNo) {
            case 0 -> printer.print(integer + "");
            case 1 -> id.print();
            case 2 -> {
                printer.print("(");
                exp.print();
                printer.print(")");
            }
        }
    }

    public void parse() {
        switch (tok.getToken()) {
            case TokenUtil.INTEGER_NUM:
                integer = tok.intVal();
                altNo = 0;
                tok.skipToken();
                break;
            case TokenUtil.IDENTIFIER_NUM:
                id = new Id(tok.idName(), tok);
                id.parseTwo();
                altNo = 1;
                break;
            case TokenUtil.OPEN_PARENTHESIS_NUM:
                altNo = 2;
                tok.skipToken();
                exp = new Expression(tok);
                exp.parse();
                if(tok.getToken() != TokenUtil.CLOSED_PARENTHESIS_NUM) {
                    TokenUtil.unexpectedError(tok, "closed parenthesis");
                }
                tok.skipToken();
                break;
        }
    }
    public void execute() {
        // empty
    }
    public int evaluate() {
        switch (altNo) {
            case 0:
                return integer;
            case 1:
                return id.evaluate();
            case 2:
                return exp.evaluate();
        }
        return -1;
    }
}
