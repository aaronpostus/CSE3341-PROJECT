package Interpreter;

import Tokenizer.TokenUtil;
import Tokenizer.Tokenizer;

public class If implements INode {
    Tokenizer tok;
    public int altNo;
    private Condition cond;
    private StatementSequence ss1;
    private StatementSequence ss2;
    public If(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        printer.print("if");
        cond.print();
        printer.print("then");
        int thenIndent = printer.getIndentNumber();
        printer.indent();
        ss1.print();
        if(altNo == 1) {
            printer.setIndentNumber(thenIndent);
            printer.println("else");
            printer.indent();
            ss2.print();
        }
        printer.setIndentNumber(thenIndent);
        printer.println("end");
        printer.print(";");
    }

    public void parse() {
        if(tok.getToken() != TokenUtil.IF_NUM) {
            TokenUtil.unexpectedError(tok, "if");
        }
        // skip if
        tok.skipToken();
        cond = new Condition(tok);
        cond.parse();
        if(tok.getToken() != TokenUtil.THEN_NUM) {
            TokenUtil.unexpectedError(tok, "then");
        }
        // skip then
        tok.skipToken();
        ss1 = new StatementSequence(tok);
        ss1.parse();
        switch (tok.getToken()) {
            case TokenUtil.END_NUM:
                altNo = 0;
                break;
            case TokenUtil.ELSE_NUM:
                altNo = 1;
                tok.skipToken();
                ss2 = new StatementSequence(tok);
                ss2.parse();
                break;
            default:
                TokenUtil.unexpectedError(tok, "end or else");
        }
        // skip past end and semicolon tokens
        if(tok.getToken() != TokenUtil.END_NUM) {
            TokenUtil.unexpectedError(tok, "end");
        }
        tok.skipToken();
        if(tok.getToken() != TokenUtil.SEMICOLON_NUM) {
            TokenUtil.unexpectedError(tok, "semicolon");
        }
        tok.skipToken();
    }

    public void execute() {
        if(cond.evaluate()) {
            ss1.execute();
        }
        else if (altNo == 1) {
            ss2.execute();
        }
    }
}
