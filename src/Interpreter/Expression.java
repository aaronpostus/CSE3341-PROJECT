package Interpreter;

import Tokenizer.Tokenizer;
import Tokenizer.TokenUtil;

public class Expression implements INode {
    private Tokenizer tok;
    public int altNo;
    private Factor fac;
    private Expression exp;

    public Expression(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        fac.print();
        if(altNo == 1) {
            printer.print("+");
            exp.print();
        }
        else if (altNo == 2) {
            printer.print("-");
            exp.print();
        }
    }

    public void parse() {
        fac = new Factor(tok);
        fac.parse();
        switch (tok.getToken()) {
            case TokenUtil.PLUS_NUM:
                altNo = 1;
                break;
            case TokenUtil.MINUS_NUM:
                altNo = 2;
                break;
            // no need to skip here
            default:
                altNo = 0;
                break;
        }

        if(altNo != 0) {
            // skip + or -
            tok.skipToken();
            exp = new Expression(tok);
            exp.parse();
        }
    }

    public void execute() {
        // empty body
    }
    public int evaluate() {
        switch (altNo) {
            case 0:
                return fac.evaluate();
            case 1:
                return fac.evaluate() + exp.evaluate();
            case 2:
                return fac.evaluate() - exp.evaluate();
        }
        return -1;
    }
}
