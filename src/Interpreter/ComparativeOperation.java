package Interpreter;

import Tokenizer.Tokenizer;
import Tokenizer.TokenUtil;

public class ComparativeOperation implements INode {
    private Tokenizer tok;
    public int altNo;
    public ComparativeOperation(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        switch (altNo) {
            case 0 -> printer.print("!=");
            case 1 -> printer.print("==");
            case 2 -> printer.print("<");
            case 3 -> printer.print(">");
            case 4 -> printer.print("<=");
            case 5 -> printer.print(">=");
        }
    }

    public void parse() {
        switch (tok.getToken()) {
            case TokenUtil.NOT_EQUALS_NUM -> altNo = 0;
            case TokenUtil.DOUBLE_EQUALS_NUM -> altNo = 1;
            case TokenUtil.LESS_THAN_NUM -> altNo = 2;
            case TokenUtil.GREATER_THAN_NUM -> altNo = 3;
            case TokenUtil.LESS_THAN_OR_EQUAL_NUM -> altNo = 4;
            case TokenUtil.GREATER_THAN_OR_EQUAL_NUM -> altNo = 5;
            default -> TokenUtil.unexpectedError(tok, "!=, ==, <, >, <=, >=");
        }
        tok.skipToken();
    }

    public void execute() {
        // empty
    }
}
