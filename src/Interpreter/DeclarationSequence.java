package Interpreter;

import Tokenizer.Tokenizer;
import Tokenizer.TokenUtil;

public class DeclarationSequence implements INode {
    public int altNo;
    private Tokenizer tok;
    private Declaration decl;
    private DeclarationSequence declSeq;
    // altNo 0: <decl>
    // altNo 1: <decl> <declseq>
    public DeclarationSequence(Tokenizer tok) {
        this.tok = tok;
    }

    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        decl.print();
        if(altNo == 1) {
            declSeq.print();
        }
    }

    public void parse() {
        // first token should be int, but declaration will deal with that
        decl = new Declaration(tok);
        decl.parse();

        // current token is begin unless there is another statement sequence
        if(tok.getToken() != TokenUtil.BEGIN_NUM) {
            declSeq = new DeclarationSequence(tok);
            declSeq.parse();
            altNo = 1;
        }
        else {
            altNo = 0;
        }
    }

    public void execute() {

    }
}
