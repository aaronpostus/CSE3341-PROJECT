package Interpreter;

import Tokenizer.TokenUtil;
import Tokenizer.Tokenizer;

public class Comparison implements INode {
    private Operation op1;
    private Operation op2;
    private ComparativeOperation compOp;
    private Tokenizer tok;
    public Comparison(Tokenizer tok) {
        this.tok = tok;
    }

    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        printer.print("(");
        op1.print();
        compOp.print();
        op2.print();
        printer.print(")");
    }

    public void parse() {
        if(tok.getToken() != TokenUtil.OPEN_PARENTHESIS_NUM) {
            TokenUtil.unexpectedError(tok, "open parenthesis");
        }
        tok.skipToken();
        op1 = new Operation(tok);
        op1.parse();
        compOp = new ComparativeOperation(tok);
        compOp.parse();
        op2 = new Operation(tok);
        op2.parse();
        if(tok.getToken() != TokenUtil.CLOSED_PARENTHESIS_NUM) {
            TokenUtil.unexpectedError(tok, "closed parenthesis");
        }
        tok.skipToken();
    }

    public void execute() {
        // empty
    }
    public boolean evaluate() {
        switch (compOp.altNo) {
            case 0:
                return op1.evaluate() != op2.evaluate();
            case 1:
                return op1.evaluate() == op2.evaluate();
            case 2:
                return op1.evaluate() < op2.evaluate();
            case 3:
                return op1.evaluate() > op2.evaluate();
            case 4:
                return op1.evaluate() <= op2.evaluate();
            case 5:
                return op1.evaluate() >= op2.evaluate();
            default:
                return false;
        }
    }
}
