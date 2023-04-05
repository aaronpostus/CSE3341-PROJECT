package Interpreter;

import Tokenizer.Tokenizer;
import Tokenizer.TokenUtil;

public class Factor implements INode {
    private Tokenizer tok;
    public int altNo;
    private Operation op;
    private Factor fac;
    public Factor(Tokenizer tok) {
        this.tok = tok;
    }

    public void print() {
        op.print();
        if(altNo == 1) {
            PrettyPrinter printer = PrettyPrinter.instance();
            printer.print("*");
            fac.print();
        }
    }

    public void parse() {
        op = new Operation(tok);
        op.parse();
        altNo = 0;
        if(tok.getToken() == TokenUtil.TIMES_NUM) {
            altNo = 1;
            tok.skipToken();
            fac = new Factor(tok);
            fac.parse();
        }
    }

    public void execute() {
        // empty body
    }
    public int evaluate() {
        switch (altNo) {
            case 0:
                return op.evaluate();
            case 1:
                return op.evaluate() * fac.evaluate();
        }
        return -1;
    }
}
