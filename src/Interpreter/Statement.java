package Interpreter;

import Tokenizer.TokenUtil;
import Tokenizer.Tokenizer;

public class Statement implements INode {

    private Tokenizer tok;
    private INode alternative;
    public int altNo;
    public Statement(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        printer.println("");
        alternative.print();
    }

    public void parse() {
        switch (tok.getToken()) {
            case TokenUtil.IDENTIFIER_NUM -> {
                alternative = new Assignment(tok);
                altNo = 0;
            }
            case TokenUtil.IF_NUM -> {
                alternative = new If(tok);
                altNo = 1;
            }
            case TokenUtil.WHILE_NUM -> {
                alternative = new Loop(tok);
                altNo = 2;
            }
            case TokenUtil.READ_NUM -> {
                alternative = new In(tok);
                altNo = 3;
            }
            case TokenUtil.WRITE_NUM -> {
                alternative = new Out(tok);
                altNo = 4;
            }
            default -> {
                TokenUtil.unexpectedError(tok, "statement");
            }
        }
        alternative.parse();
    }

    public void execute() {
        alternative.execute();
    }
}
