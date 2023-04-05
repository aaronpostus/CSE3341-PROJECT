package Interpreter;

import Tokenizer.Tokenizer;
import Tokenizer.TokenUtil;

public class Loop implements INode {
    Tokenizer tok;
    private Condition cond;
    private StatementSequence statementSequence;
    public Loop(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        printer.print("while");
        cond.print();
        printer.print("loop");
        int thenIndent = printer.getIndentNumber();
        printer.indent();
        statementSequence.print();
        printer.setIndentNumber(thenIndent);
        printer.println("end");
        printer.print(";");
    }

    public void parse() {
        if(tok.getToken() != TokenUtil.WHILE_NUM) {
            TokenUtil.unexpectedError(tok, "while");
        }
        tok.skipToken();
        cond = new Condition(tok);
        cond.parse();
        if(tok.getToken() != TokenUtil.LOOP_NUM) {
            TokenUtil.unexpectedError(tok, "loop");
        }
        tok.skipToken();
        statementSequence = new StatementSequence(tok);
        statementSequence.parse();
        if(tok.getToken() != TokenUtil.END_NUM) {
            TokenUtil.unexpectedError(tok, "end");
        }
        // skip end token
        tok.skipToken();
        if(tok.getToken() != TokenUtil.SEMICOLON_NUM) {
            TokenUtil.unexpectedError(tok, "semicolon");
        }
        // skip semicolon
        tok.skipToken();
    }

    public void execute() {
        while(cond.evaluate()) {
            statementSequence.execute();
        }
    }
}
