package Interpreter;

import Tokenizer.Tokenizer;
import Tokenizer.TokenUtil;


public class StatementSequence implements INode {
    public int altNo;
    private Tokenizer tok;
    private Statement stmt;
    private StatementSequence stmtSeq;
    // altNo 0: <stmt>
    // altNo 1: <stmt> <stmtseq>
    public StatementSequence(Tokenizer tok) {
        this.tok = tok;
    }

    public void print() {
        stmt.print();
        if(altNo == 1) {
            stmtSeq.print();
        }
    }

    public void parse() {
        // first token should be assign / if / loop / in / out but statement will deal with that
        stmt = new Statement(tok);
        stmt.parse();

        // a statement is always terminated by end or else
        int token = tok.getToken();
        if(token == TokenUtil.END_NUM || token == TokenUtil.ELSE_NUM) {
            altNo = 0;
        }
        // attempt to parse another statement sequence if not terminated by end or else
        else {
            altNo = 1;
            stmtSeq = new StatementSequence(tok);
            stmtSeq.parse();
        }
    }

    public void execute() {
        stmt.execute();
        if(altNo == 1) {
            stmtSeq.execute();
        }
    }
}
