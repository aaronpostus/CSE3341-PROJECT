package Interpreter;

import Tokenizer.TokenUtil;
import Tokenizer.Tokenizer;

public class Declaration implements INode {
    Tokenizer tok;
    IdList idList;
    public Declaration(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        printer.print("int");
        idList.print();
        printer.print(";");
    }

    public void parse() {
        // first token should be int
        if(tok.getToken() != TokenUtil.INT_NUM) {
            TokenUtil.unexpectedError(tok, "int");
        }
        // advance to first id in idlist
        tok.skipToken();
        // create id list, and initialize these ids instead of accessing them
        idList = new IdList(tok, true);
        idList.parse();

        // last token should be semicolon
        if(tok.getToken() != TokenUtil.SEMICOLON_NUM) {
            TokenUtil.unexpectedError(tok, "semicolon");

        }
        tok.skipToken();
    }

    public void execute() {
        // empty
    }
}
