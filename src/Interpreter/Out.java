package Interpreter;

import Tokenizer.TokenUtil;
import Tokenizer.Tokenizer;

public class Out implements INode {
    Tokenizer tok;
    private IdList idList;
    public Out(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        printer.print("write");
        idList.print();
        printer.print(";");
    }

    public void parse() {
        if(tok.getToken() != TokenUtil.WRITE_NUM) {
            TokenUtil.unexpectedError(tok,"write");
        }
        tok.skipToken();
        idList = new IdList(tok, false);
        idList.parse();
        if(tok.getToken() != TokenUtil.SEMICOLON_NUM) {
            TokenUtil.unexpectedError(tok,"semicolon");
        }
        tok.skipToken();
    }

    public void execute() {
        idList.write();
    }

}
