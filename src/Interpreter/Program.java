package Interpreter;

import Tokenizer.Tokenizer;
import Tokenizer.TokenUtil;

public class Program implements INode {
    private DeclarationSequence declSeq;
    private StatementSequence stmtSeq;
    Tokenizer tok;
    public Program(Tokenizer tok) {
        this.tok = tok;
    }

    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        printer.println("program");
        declSeq.print();
        printer.indent();
        int indent = printer.getIndentNumber();
        printer.println("begin");
        printer.indent();
        stmtSeq.print();
        printer.setIndentNumber(indent);
        printer.println("end");
    }

    public void parse() {
        if(tok.getToken() != TokenUtil.PROGRAM_NUM) {
            TokenUtil.unexpectedError(tok, "program");
        }
        tok.skipToken();
        declSeq = new DeclarationSequence(tok);
        declSeq.parse();
        // declaration sequence will advance to the begin token?
        if(tok.getToken() != TokenUtil.BEGIN_NUM) {
            TokenUtil.unexpectedError(tok, "begin");
        }
        tok.skipToken();
        stmtSeq = new StatementSequence(tok);
        stmtSeq.parse();
        // declaration sequence will advance to the end token?
        if(tok.getToken() != TokenUtil.END_NUM) {
            TokenUtil.unexpectedError(tok, "end");
        }
    }

    public void execute() {
        declSeq.execute();
        stmtSeq.execute();
    }
}
